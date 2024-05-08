package com.revature.models.DTOs;

//What's a DTO? A Data Transfer Object!
//We don't want to have to specify User ID or a List of Reimbursements for Login/Registration
//DTOs let us store ONLY the relevant information for a given operation

//Big Picture: This will let a user just submit a username and password for login/registration

public class IncomingUserDTO {

    //login information
    private String username;
    private String password;

    //registration fields
    private String firstName;
    private String lastName;
    private String role;



    //private String email (IF THIS EXISTED), et cetera

    //From here, it's just boilerplate code -------------

    //no args constructor
    public IncomingUserDTO() {
    }

    //login constructor
    public IncomingUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //registration constructor
    public IncomingUserDTO(String username, String password, String firstName, String lastName, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    //getter/setter and toString() methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "IncomingUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
