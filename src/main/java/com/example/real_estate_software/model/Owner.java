package com.example.real_estate_software.model;


/**
 * The Owner class is a class used to create and access User objects of type Owner. It extends the abstract class
 * User, inheriting its methods and basic User Object information ie first Name.
 *
 *
 * The Owner class provides two constructor:
 *
 * 1. Constructor #1:
 *  - Initializes an Owner with a first name, last name, email, and password.
 *  - The Owner is marked as signed in by default.
 *
 * 2. Constructor #2:
 *  - Initializes an Owner with a first name, last name, email, password, and a signed-in status.
 *  - Allows more control over the sign-in status, which is useful for scenarios where the sign-in
 *    state needs to be explicitly set (e.g., loading data from a database).
 *
 *
 * @author Harrison Mega, Steven Hujbert
 * @version 1.4
 *
 */
public class Owner extends User{
    private String password;
    private boolean signedIn;

    /**
     * The Owner Constructor
     *
     * @param firstName Owners First Name
     * @param lastName Owners Last Name
     * @param email Owners Email
     * @param password Owners Password
     *
     */
    public Owner(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email);
        this.password = password;
        signedIn = true;
    }

    /**
     * The Owner Constructor Overload
     *
     * @param firstName Owners First Name
     * @param lastName Owners Last Name
     * @param  email Owners Email
     * @param password Owners Password
     * @param signedIn Boolean that changes depending on if the Owner is signed in to the DB or not
     *
     */
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

    public boolean checkValidEmail() {
        return this.getEmail().contains("@") && this.getEmail().contains(".com");
    }

    public boolean checkCapitalPassword() {
        for (int i = 0; i < this.getPassword().length(); i++) {
            if (Character.isUpperCase(this.getPassword().charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNumberPassowrd() {
        for (int i = 0; i < this.getPassword().length(); i++) {
            if (Character.isDigit(this.getPassword().charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLengthPassword(){
        return this.getPassword().length() >= 8;
    }

    public boolean checkUniqueCharacterPassword(){
        String uniqueChar = ".*[!@#$%^&*()_+{}|:;<>?,./`~]*.";

        for (int i = 0; i < this.getPassword().length(); i++){
            for (int j = 0; j < uniqueChar.length(); j++){
                char c = this.getPassword().charAt(i);
                if (c == uniqueChar.charAt(j)){
                    return true;
                }
            }
        }
        return false;
    }
}

