package com.revature.controllers;

import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
//Approving our Frontend to talk to this Controller
//we're ALSO saying that we're going to allow session data to be passed back and forth
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    //Autowire User Service
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody IncomingUserDTO userDTO){

        //Try to register the user
        try{
            userService.registerUser(userDTO);
            //If this all works, send back a 201 CREATED plus a confirmation message
            return ResponseEntity.status(201).body(userDTO.getUsername() + " registered successfully!");
        }catch(IllegalArgumentException e){
            //If there's an error, send back a 400 BAD REQUEST plus the error message
            return ResponseEntity.status(400).body(e.getMessage());
        }
        //TODO: We'll have checks for DB issues here as well


    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody IncomingUserDTO userDTO, HttpSession session){

        //Get the User object from the service (which talks to the DB)
        Optional<User> optionalUser = userService.loginUser(userDTO);

        //If login fails (which will return an empty Optional) tell the user they failed
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(401).body("Login Failed!");
        }

        //If login succeeds, add the user to the session and send back a 200 OK
        User u = optionalUser.get();

        //Storing the user info in our session
        session.setAttribute("userId", u.getUserId());
        session.setAttribute("username", u.getUsername()); //probably won't use this

        //TODO: Add role to session
        //Hypothetical Role save to session
        //session.setAttribute(“role”, u.getRole());

        //Finally send back the 200 (OK) as well as an OutgoingUserDTO
        return ResponseEntity.ok(new OutgoingUserDTO(u.getUserId(), u.getUsername(), u.getFirstName(), u.getLastName(), u.getRole()));

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody IncomingUserDTO userDTO, HttpSession session){

        /*
        System.out.println("Logout function is being called in UserController.");
        //Get the User object from the service (which talks to the DB)
        Optional<User> optionalUser = userService.logoutUser(userDTO);

        System.out.println("Optional User is: " + optionalUser);

        //If login fails (which will return an empty Optional) tell the user they failed
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(401).body("Logout Failed!");
        }


        //If login succeeds, add the user to the session and send back a 200 OK
        User u = optionalUser.get();


        //Hypothetical Role save to session
        //session.setAttribute(“role”, u.getRole());

        //Finally send back the 200 (OK) as well as an OutgoingUserDTO
        //return ResponseEntity.ok(u.getUsername() + " has been logged out!");
        */

        //This works for now without doing anything to the user
        //killing our session
        session.invalidate();

        //This works for now without doing anything to the user
        return ResponseEntity.ok("You have been logged out!");

    }

    //get all Users
    @GetMapping
    public ResponseEntity<?> getAllReimbursements(HttpSession session){

        //TODO: Add a check for the role of the user to make sure they are "Manager" (currently done on frontend)

        //Login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("You must be logged in to access records!");
        }

        //Get the userId from the session
        //int userId = (int) session.getAttribute("userId");


        return ResponseEntity.ok(userService.getAllUsers());
    }

    //delete user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId, HttpSession session){

        //Login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("You must be logged in to access records!");
        }

        //make sure that the userId is greater than 0
        if(userId <= 0){
            return ResponseEntity.badRequest().body("Invalid userId");
        }

        //check for self-deletion
        if(session.getAttribute("userId").equals( userId)){
            return ResponseEntity.status(401).body("You cannot remove your own account!");
        }

        try{
            String s = userService.deleteUser(userId);

            if (s.equals("User deleted successfully!")){
                return ResponseEntity.ok(s);
            }
            else if(s.equals("User not found!")){
                return ResponseEntity.badRequest().body(s);
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.badRequest().body("Something went wrong!");
    }
}
