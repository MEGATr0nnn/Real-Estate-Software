package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteOwnerDAO {
    private Connection connection;

    public SqliteOwnerDAO() {
        connection = SqliteConnection.getInstance();
        createOwnerTable();
    }

    private void createOwnerTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS owners ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOwner(Owner owner) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO owners (firstName, lastName, email, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getLastName());
            statement.setString(3, owner.getEmail());
            statement.setString(4, owner.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                owner.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOwner(Owner owner) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE owners SET firstName = ?, lastName = ?, email = ?, password = ? WHERE id = ?");
            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getLastName());
            statement.setString(3, owner.getEmail());
            statement.setString(4, owner.getPassword());
            statement.setInt(5, owner.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOwner(Owner owner) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM owners WHERE id = ?");
            statement.setInt(1, owner.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Owner getOwner(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM owners WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Owner owner = new Owner(firstName, lastName, email, password);
                owner.setId(id);
                return owner;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
