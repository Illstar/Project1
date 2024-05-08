package com.revature.models.DTOs;

//This DTO will take in a Reimbursement with just an int for userId
//save us the hassle of trying to send an entire user from the front end

public class IncomingReimbursementDTO {

    private double amount;
    private String status;
    private String description;
    private int userId;

    //no args constructor
    public IncomingReimbursementDTO() {
    }

    //all args constructor
    public IncomingReimbursementDTO(double amount, String status, String description, int userId) {
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.userId = userId;
    }

    //getter/setter and toString() methods
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "IncomingReimbursementDTO{" +
                "amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
