package com.example.real_estate_software.model;

public class Tenant extends User {
    private int phoneNumber;
    private boolean assignedToProp;

    public Tenant(String firstName, String lastName, String email, int phoneNumber) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        assignedToProp = false;
    }

    public Tenant(String firstName, String lastName, String email, int phoneNumber, boolean assignedToProp) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.assignedToProp = assignedToProp;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getAssignedToProp() {
        return assignedToProp;
    }

    public void setAssignedToProp(boolean assignedToProp) {
        this.assignedToProp = assignedToProp;
    }
}
