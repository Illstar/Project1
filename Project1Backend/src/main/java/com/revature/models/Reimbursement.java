package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import com.revature.models.User;

@Component
@Entity
@Table(name="reimbursements")
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementId;

    private double amount;

    private String status;

    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;

    //no args constructor
    public Reimbursement() {
    }

    //create new Reimbursement constructor
    public Reimbursement(double amount, String status, String description, User user) {
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.user = user;
    }

    //all args constructor
    public Reimbursement(int reimbursementId, double amount, String status, String description, User user) {
        this.reimbursementId = reimbursementId;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.user = user;
    }

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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}