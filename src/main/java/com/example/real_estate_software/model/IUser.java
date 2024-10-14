package com.example.real_estate_software.model;

//interface for any User based classes, not for unique objects like property
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
