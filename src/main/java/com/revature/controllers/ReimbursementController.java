package com.revature.controllers;

import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReimbursementController {

    private ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    //post mapping for inserting new Reimbursement
    @PostMapping()
    public ResponseEntity<String> addReimbursement(@RequestBody IncomingReimbursementDTO reimbursementDTO, HttpSession session){

        //If the user is not logged in (if the userId is null)
        //send back a 401 UNAUTHORIZED
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("You must be logged in to access records!");
        }

        //Now that we have user info saved in our HTTP session
        //we can attach the stored userId to the Reimbursement DTO
        reimbursementDTO.setUserId((int) session.getAttribute("userId"));
        //why do we need to cast to an int?
        //getAttribute returns an Object
        //we then need to cast it to the specific data type we need

        /* try/catch
        try{
            pokemonService.addPokemon(pokeDTO);
            return ResponseEntity.status(201).body(pokeDTO.getName() + " added successfully!");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        } */

        Reimbursement r = reimbursementService.addReimbursement(reimbursementDTO);

        if (r.getAmount() == 0){
            return ResponseEntity.status(400).body("Amount must be greater than 0!");
        }

        return ResponseEntity.status(201).body(r.getUser().getUsername() + " inserted a new reimbursement!");

    }

    //put mapping for updating Reimbursement status
    @PutMapping("/{reimbursementId}")
    public ResponseEntity<String> updateReimbursement(@PathVariable int reimbursementId, @RequestBody IncomingReimbursementDTO reimbursementDTO, HttpSession session){

        //If the user is not logged in (if the userId is null)
        //send back a 401 UNAUTHORIZED
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("You must be logged in to access records!");
        }

        //Now that we have user info saved in our HTTP session
        //we can attach the stored userId to the Reimbursement DTO
        reimbursementDTO.setUserId((int) session.getAttribute("userId"));
        //why do we need to cast to an int?
        //getAttribute returns an Object
        //we then need to cast it to the specific data type we need

        /* try/catch
        try{
            pokemonService.addPokemon(pokeDTO);
            return ResponseEntity.status(201).body(pokeDTO.getName() + " added successfully!");
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        } */



        String s = reimbursementService.updateReimbursement(reimbursementDTO, reimbursementId);

        if (s.equals("Status updated successfully!")){
            return ResponseEntity.ok(s);
        }
        else if (s.equals("Status must be PENDING to update!")){
            return ResponseEntity.badRequest().body(s);
        }
        else if(s.equals("Reimbursement with ID " + reimbursementId + " not found!")){
            return ResponseEntity.badRequest().body(s);
        }

        return ResponseEntity.badRequest().body("Something went wrong!");

    }



    //get all Reimbursements for a user that is logged in
    @GetMapping
    public ResponseEntity<?> getAllReimbursementsForUser(HttpSession session){

        //Login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("You must be logged in to access records!");
        }

        //Get the userId from the session
        int userId = (int) session.getAttribute("userId");




        //return the list of reimbursements by the user id if not a manager
        return ResponseEntity.ok(reimbursementService.getAllReimbursementsForUser(userId));
    }

    //get all Reimbursements for a user that is logged in
    @GetMapping("/boss")
    public ResponseEntity<?> getAllReimbursements(HttpSession session){

        //Login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("You must be logged in to access records!");
        }

       //return the list of reimbursements by the user id if not a manager
       return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }



    //delete a Reimbursement by ID
    @DeleteMapping("/{reimbursementId}")
    public ResponseEntity<String> deleteReimbursement(@PathVariable int reimbursementId, HttpSession session){

        //Login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("You must be logged in to access records!");
        }

        //Get the userId from the session
        int userId = (int) session.getAttribute("userId");

        //try/catch the service method, returning a confirmation or error message
        try{
            return ResponseEntity.ok(reimbursementService.deleteReimbursement(reimbursementId, userId));
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }


}
