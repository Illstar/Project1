package com.revature.models.DTOs;

//This DTO will only send user id, username, firstName, and role to the front end
//so we don't have to risk sending the user's password over HTTP
public class OutgoingUserDTO {

    private int userId;
    private String username;

    private String firstName;
    private String lastName;
    private String role;

    //no args constructor
    public OutgoingUserDTO() {
    }



    //all args constructor
    public OutgoingUserDTO(int userId, String username, String firstName, String lastName, String role) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }


    //getter/setter and toString() methods
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "OutgoingUserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
