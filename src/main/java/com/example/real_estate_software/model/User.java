package com.example.real_estate_software.model;

/**
 * Abstract class that contains fields that are shared by other Objects of type User (ie: Owners, Tenants)
 *
 * @author Harrison Mega, Steven Hujbert
 * @version 1
 *
 */
public abstract class User implements IUser {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * User constructor
     *
     * @param firstName Users First Name
     * @param lastName Users Last Name
     * @param  email Users Email
     *
     * */
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Used to check if email contain valid characters
     * @return boolean
     */
    public boolean checkValidEmail() {
        return email.contains("@") && email.contains(".com");
    }

}
