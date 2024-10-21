package com.example.real_estate_software.model;

/**
 * The Tenant class is a class used to create and access User objects of type Tenant
 *
 * @author Steven Hujbert, Harrison Mega
 * @version 1
 *
 * */
public class Tenant extends User {
    private int phoneNumber;
    private boolean assignedToProp;
    private int propertyId;

    /**
     * Tenant Constructor Override #1
     *
     * @param firstName Tenants First Name
     * @param lastName Tenants Last Name
     * @param email Tenants Email
     * @param phoneNumber Tenants Phone Number
     *
     * */
    public Tenant(String firstName, String lastName, String email, int phoneNumber) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        assignedToProp = false;
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
     * */
    public Tenant(String firstName, String lastName, String email, int phoneNumber, boolean assignedToProp, int propertyId) {
        super(firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.assignedToProp = assignedToProp;
        this.propertyId = propertyId;
    }

    //Getters and Setters
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

    public int getPropertyId(){
        return propertyId;
    }

    public void setPropertyId(int propertyId){
        this.propertyId = propertyId;
    }
}
