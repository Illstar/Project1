package com.revature.models.DTOs;

//This DTO will send Reimbursements with only reimbursementId, amount, status, description, and userId
//We DON'T want to send entire user objects
//In order to keep the password safe and avoid ugly recursive issues with the data
//(Reimbursement has a User which has a List<Reimbursement> which each have a User... et cetera)


public class OutgoingReimbursementDTO {

    private int reimbursementId;
    private double amount;
    private String status;
    private String description;
    private int userId;

    //no args constructor
    public OutgoingReimbursementDTO() {
    }

    //no args constructor
    public OutgoingReimbursementDTO(int reimbursementId, double amount, String status, String description, int userId) {
        this.reimbursementId = reimbursementId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.userId = userId;
    }

    //getter/setter and toString() methods
    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

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
        return "OutgoingReimbursementDTO{" +
                "reimbursementId=" + reimbursementId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
