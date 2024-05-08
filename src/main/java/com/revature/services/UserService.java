package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Service classes are used for data processing between controllers and DAOs
//anything we need to do between HTTP and the Database is done here
//Error Handling
//Data Mutations
//Data Validations
//more!



@Service
public class UserService {

    //Service always calls to the DAO
    //autowire the UserDAO as we need to use its methods
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //Register User
    public User registerUser(IncomingUserDTO userDTO) throws IllegalArgumentException{

        //Check the username and password are not empty/null
        if(userDTO.getUsername().isBlank() || userDTO.getUsername() == null){
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if(userDTO.getPassword().isBlank() || userDTO.getPassword() == null){
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        //also check that the username isn't innapropriate
        if(userDTO.getUsername().equals("JavaScript")){
            throw new IllegalArgumentException("Username cannot be JavaScript.");
        }

        //we could definitely check if the DTO is null too,
        //but we're going to assume we've written good code
        if(userDTO == null){
            throw new IllegalArgumentException("This probably won't get thrown if we've written good code.");
        }

        //TODO: We could have made an exception handling service to clean all this up, which is typical

        //If all checks pass, then we can create a new User based off the DTO and send it to the DAO
        User newUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getRole());

        //Save the user to the database and return it at the same time
        return userDAO.save(newUser);
    }

    //This service will facilitate login - get a user from the DAO (or null)
    public Optional<User> loginUser(IncomingUserDTO userDTO) throws IllegalArgumentException {

        //TODO : validity checks

        //if all checks pass, return a User OR null
        //send it to the controller
        return userDAO.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
    }

    //This service will facilitate login - get a user from the DAO (or null)
    public Optional<User> logoutUser(IncomingUserDTO userDTO) throws IllegalArgumentException {

        //TODO : validity checks
        System.out.println("Logout function is being called in UserService.");
        //if all checks pass, return a User OR null
        //send it to the controller
        System.out.println(userDTO.getUsername());
        return userDAO.findByUsername(userDTO.getUsername());
    }

    //get all Users
    public List<OutgoingUserDTO> getAllUsers(){

        //get all Users from the DB
        List<User> allUsers = userDAO.findAll();

        //for every User retrieved, we'll create a new OutgoingPokeDTO
        //and add it to a List to be returned
        List<OutgoingUserDTO> outgoingUsers = new ArrayList<>();

        for(User u : allUsers){
            OutgoingUserDTO outP = new OutgoingUserDTO(u.getUserId(), u.getUsername(), u.getFirstName(), u.getLastName(), u.getRole());
            outgoingUsers.add(outP);
        }

        return outgoingUsers;

    }

    //this method will delete a user by id
    public String deleteUser(int userId) {


        System.out.println("Delete function is being called.");

        //check if the user to delete exists
        Optional<User> optionalUser = userDAO.findById(userId);

        System.out.println(optionalUser.isPresent());

        if(optionalUser.isPresent()){
            userDAO.deleteById(userId);
            return "User deleted successfully!";
        }else{
            return "User not found!";
        }

    }

}
