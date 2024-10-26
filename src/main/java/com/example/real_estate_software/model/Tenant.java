package com.example.real_estate_software.model;

import javafx.scene.image.Image;

/**
 * The Tenant class is a class used to create and access User objects of type Tenant
 *
 * @author Steven Hujbert, Harrison Mega
 * @version 1
 *
 */
public class Tenant extends User {
    private String phoneNumber;
    private boolean assignedToProp;

    /**
     * Tenant Constructor Override #1
     *
     * @param firstName Tenants First Name
     * @param lastName Tenants Last Name
     * @param email Tenants Email
     * @param phoneNumber Tenants Phone Number
     *
     */
    public Tenant(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        assignedToProp = true;
    }

    /**
     * Tenant Constructor Override #2
     *
     * @param firstName Tenants First Name
     * @param lastName Tenants Last Name
     * @param email Tenants Email
     * @param phoneNumber Tenants Phone Number
     * @param assignedToProp Boolean that tracks if the Tenant has been assigned to a property in to the DB or not
     *
     */
    public Tenant(String firstName, String lastName, String email, String phoneNumber, boolean assignedToProp) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.assignedToProp = assignedToProp;
    }

    //Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getAssignedToProp() {
        return assignedToProp;
    }

    public void setAssignedToProp(boolean assignedToProp) {
        this.assignedToProp = assignedToProp;
    }

}
