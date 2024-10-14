package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO implements IUserDAO<Owner> {
    private Connection connection;

    public OwnerDAO() {
        connection = DatabaseConnection.getInstance();
        createTable();
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS owners ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "signedIn BOOLEAN NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertNew(Owner owner) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO owners (firstName, lastName, email, password, signedIn) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getLastName());
            statement.setString(3, owner.getEmail());
            statement.setString(4, owner.getPassword());
            statement.setBoolean(5, owner.getSignedIn());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                owner.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Owner owner) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE owners SET firstName = ?, lastName = ?, email = ?, password = ?, signedIn = ? WHERE id = ?");
            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getLastName());
            statement.setString(3, owner.getEmail());
            statement.setString(4, owner.getPassword());
            statement.setBoolean(5, owner.getSignedIn());
            statement.setInt(6, owner.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Owner owner) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM owners WHERE id = ?");
            statement.setInt(1, owner.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Owner getOwner(boolean signedIn) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM owners WHERE signedIn = ?");
            statement.setBoolean(1, signedIn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                signedIn = resultSet.getBoolean("signedIn");
                Owner owner = new Owner(firstName, lastName, email, password, signedIn);
                owner.setId(id);
                return owner;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Owner> getAll(Owner owner) {
        List<Owner> owners = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM owners";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                boolean signedIn = resultSet.getBoolean("signedIn");
                owner = new Owner(firstName, lastName, email, password, signedIn);
                owner.setId(id);
                owners.add(owner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return owners;
    }
}
