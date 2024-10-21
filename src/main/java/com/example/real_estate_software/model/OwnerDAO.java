package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the DAO that links the Owner object to the Owner table in the DB
 *
 * @author Harrison Mega, Steven Hujbert
 * @version 1.8
 *
 * */
public class OwnerDAO implements IUserDAO<Owner> {
    private DatabaseControl<Owner> connect;

    public OwnerDAO() {
        connect = new DatabaseControl<Owner>();
        createTable();
    }

    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS owners ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "firstName VARCHAR NOT NULL,"
                + "lastName VARCHAR NOT NULL,"
                + "email VARCHAR NOT NULL,"
                + "password VARCHAR NOT NULL,"
                + "signedIn BOOLEAN NOT NULL"
                + ")";
        connect.executeParamQuery(query);
    }

    @Override
    public void insertNew(Owner owner) {
        String query = "INSERT INTO owners (firstName, lastName, email, password, signedIn) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
                owner.getFirstName(),
                owner.getLastName(),
                owner.getEmail(),
                owner.getPassword(),
                owner.getSignedIn()
        };
        owner.setId(connect.executeParamQuery(query, params));
    }

    @Override
    public void update(Owner owner) {
        String query = "UPDATE owners SET firstName = ?, lastName = ?, email = ?, password = ?, signedIn = ? WHERE id = ?";
        Object[] params = {
                owner.getFirstName(),
                owner.getLastName(),
                owner.getEmail(),
                owner.getPassword(),
                owner.getSignedIn(),
                owner.getId()
        };
        connect.executeParamQuery(query, params);
    }

    @Override
    public void delete(Owner owner) {
        String query = "DELETE FROM owners WHERE id = ?";
        Object[] params = {
                owner.getId()
        };
        connect.executeParamQuery(query, params);
    }

    @Override
    public List<Owner>getAll(){
        return connect.executeFetchAllOwner();
    }

    @Override
    public Owner getAllBool(boolean signedIn) {
       return connect.executeFetchOwner(signedIn);
    }
}
