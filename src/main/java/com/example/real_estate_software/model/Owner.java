package com.example.real_estate_software.model;

public class Owner extends User {
    private String password;
    private boolean signedIn;


    public Owner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email);
        this.password = password;
        signedIn = true;
    }

    public Owner(String firstName, String lastName, String email, String password, boolean signedIn) {
        super(firstName, lastName, email);
        this.password = password;
        this.signedIn = signedIn;
    }

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
