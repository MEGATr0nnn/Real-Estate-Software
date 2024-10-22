package com.example.real_estate_software.model;

/**
 * Interface for any Objects of type User
 *
 * @author Steven Hujbert
 * @version 1
 *
 * */
public interface IUser {

    public int getId();

    public void setId(int id);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getEmail();

    public void setEmail(String email);
}
