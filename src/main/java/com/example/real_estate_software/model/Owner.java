package com.example.real_estate_software.model;


/**
 * The Owner class is a class used to create and access User objects of type Owner
 *
 * @author Harrison Mega, Steven Hujbert
 * @version 1.4
 *
 * */
public class Owner extends User{
    private String password;
    private boolean signedIn;

    /**
     * The Owner Constructor Override #1
     *
     * @param firstName Owners First Name
     * @param lastName Owners Last Name
     * @param email Owners Email
     * @param password Owners Password
     *
     * */
    public Owner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email);
        this.password = password;
        signedIn = true;
    }

    /**
     * The Owner Constructor Override #2
     *
     * @param firstName Owners First Name
     * @param lastName Owners Last Name
     * @param  email Owners Email
     * @param password Owners Password
     * @param signedIn Boolean that changes depending on if the Owner is signed in to the DB or not
     *
     * */
    public Owner(String firstName, String lastName, String email, String password, boolean signedIn) {
        super(firstName, lastName, email);
        this.password = password;
        this.signedIn = signedIn;
    }

    //Getters and Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
