package com.revature.services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.DTOs.OutgoingReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;
    private UserDAO userDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO, UserDAO userDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.userDAO = userDAO;
    }

    //add a Reimbursement to DB
    public Reimbursement addReimbursement(IncomingReimbursementDTO reimbursementDTO) throws IllegalArgumentException{

        //checking to see if user input of amount is valid or not
        //if not, we don't want to save to the DB
        if(reimbursementDTO.getAmount() <= 0){
            //throw new IllegalArgumentException("Amount must be greater than 0!");
            Reimbursement r = new Reimbursement(0, reimbursementDTO.getStatus(), reimbursementDTO.getDescription(), null);
            return r;
        }

        //We need to get the User by ID and set it with the setter
        Reimbursement r = new Reimbursement(reimbursementDTO.getAmount(), reimbursementDTO.getStatus(), reimbursementDTO.getDescription(), null);

        //Instantiate the appropriate user -- this could be error checked (and should be)
        User u = userDAO.findById(reimbursementDTO.getUserId()).get();

        //TODO: Add a check to see if the user is empty

        //Set the user in the Reimbursement object
        r.setUser(u);

        //now we can save the Reimbursement
        return reimbursementDAO.save(r);

    }

    //updating a Reimbursement status in the DB
    public String updateReimbursement(IncomingReimbursementDTO reimbursementDTO, int reimbursementId) throws IllegalArgumentException{

        /*
        //checking to see if user input of amount is valid or not
        //if not, we don't want to save to the DB
        if(reimbursementDTO.get <= 0){
            //throw new IllegalArgumentException("Amount must be greater than 0!");
            Reimbursement r = new Reimbursement(0, reimbursementDTO.getStatus(), reimbursementDTO.getDescription(), null);
            return r;
        }

        //We need to get the User by ID and set it with the setter
        Reimbursement r = new Reimbursement(reimbursementDTO.getAmount(), reimbursementDTO.getStatus(), reimbursementDTO.getDescription(), null);

        //Instantiate the appropriate user -- this could be error checked (and should be)
        User u = userDAO.findById(reimbursementDTO.getUserId()).get();


        //Set the user in the Reimbursement object
        r.setUser(u);

        reimbursementId

         */
        Optional<Reimbursement> optionalReimbursement = reimbursementDAO.findById(reimbursementId);

        System.out.println("\n");
        System.out.println("IN UPDATE FUNCTION");
        System.out.println("\n");

        System.out.println(reimbursementDTO.getStatus());

        if(optionalReimbursement.isPresent()){
            Reimbursement r = optionalReimbursement.get();
            if(!r.getStatus().equals("PENDING")){
                return "Status must be PENDING to update!";
            }
            r.setStatus(reimbursementDTO.getStatus());
            reimbursementDAO.save(r);
            return "Status updated successfully!";
        }else{
            return "Reimbursement with ID " + reimbursementId + " not found!";
        }


    }

    //get all Reimbursements from the DB (manager level)
    public List<OutgoingReimbursementDTO> getAllReimbursements(){

        //get all Reimbursements from the DB
        List<Reimbursement> allReimbursements = reimbursementDAO.findAll();

        //for every Reimbursement retrieved, we'll create a new OutgoingReimbursementDTO
        //and add it to a List to be returned
        List<OutgoingReimbursementDTO> outgoingReimbursementList = new ArrayList<>();

        for(Reimbursement r : allReimbursements){
            OutgoingReimbursementDTO outR = new OutgoingReimbursementDTO(r.getReimbursementId(), r.getAmount(), r.getStatus(), r.getDescription(), r.getUser().getUserId());
            outgoingReimbursementList.add(outR);
        }

        return outgoingReimbursementList;
    }

    //get all Reimbursements from the DB for a specific user
    public List<OutgoingReimbursementDTO> getAllReimbursementsForUser(int userId){

        //get all Reimbursements from the DB
        List<Reimbursement> allReimbursements = reimbursementDAO.findByUserUserId(userId);

        //for every Reimbursement retrieved, we'll create a new OutgoingReimbursementDTO
        //and add it to a List to be returned
        List<OutgoingReimbursementDTO> outgoingReimbursementList = new ArrayList<>();

        for(Reimbursement r : allReimbursements){
            OutgoingReimbursementDTO outR = new OutgoingReimbursementDTO(r.getReimbursementId(), r.getAmount(), r.getStatus(), r.getDescription(), r.getUser().getUserId());
            outgoingReimbursementList.add(outR);
        }

        return outgoingReimbursementList;
    }


    //delete Reimbursement by ID
    public String deleteReimbursement(int reimbursementId, int userId){

        //Make sure the Reimbursement exists with an optional
        //we'll also use this Reimbursement object in the delete and return
        Optional<Reimbursement> optionalReimbursement = reimbursementDAO.findById(reimbursementId);

        if (optionalReimbursement.isEmpty()){
            throw new NoSuchElementException("Reimbursement not found! Can't delete!");
        }

        //if the Reimbursement is present:
        //extract the Reimbursement from the Optional
        //make sure the Reimbursement being deleted belongs to the user deleting it
        //delete the Reimbursement from the User's List of Reimbursements
        //perform the actual delete
        Reimbursement reimbursement = optionalReimbursement.get();

        if(reimbursement.getUser().getUserId() != userId){
            throw new IllegalArgumentException("You can't delete a Reimbursement that isn't yours!");
        }

        //The Reimbursement won't fully delete from the DB until we delete it from BOTH tables!
        reimbursement.getUser().getReimbursements().remove(reimbursement);
        reimbursementDAO.deleteById(reimbursementId);

        return "Your reimbursement has been deleted!";

    }
}
