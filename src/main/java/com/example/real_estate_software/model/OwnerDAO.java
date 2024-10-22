package com.example.real_estate_software.model;

import java.util.List;

/**
 * This is the DAO that links the Owner object to the Owner table in the DB. It provides methods for creating, reading
 * updating and deleting (CRUD operations) Owner Objects.
 *
 * The Owner DAO ensures that all DB operations related to Owner Objects are encapsulated appropriately, making it
 * easier to manage data persistence and retrieval without directly exposing SQL. It also allows for easy updating of
 * queries to allow for further future changes to be easily made to the system.
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
        connect.executeQuery(query);
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
        owner.setId(connect.executeQuery(query, params));
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
        connect.executeQuery(query, params);
    }

    @Override
    public void delete(Owner owner) {
        String query = "DELETE FROM owners WHERE id = ?";
        Object[] params = {
                owner.getId()
        };
        connect.executeQuery(query, params);
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
