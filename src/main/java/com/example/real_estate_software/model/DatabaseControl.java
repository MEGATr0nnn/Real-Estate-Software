package com.example.real_estate_software.model;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a Generic Type class that is used to handle any DB commands.
 * It provides methods to execute SQL queries, handle exceptions and retrieve data in a flexible and reusable format.
 *
 * This class uses generics to allow it to handle different types of entities, enabling reusability.
 *
 * Key Features
 * - Executes parameterized SQL queries to prevent SQL injection attacks.
 * - Uses reflection to map SQL result sets to Java objects.
 * - Supports generic fetch methods to retrieve data for different entity types.
 * - Simplifies database interaction by centralizing repetitive tasks.
 *
 * @param <T> The type of entity that this class will handle (Owner, Tenant, Property etc.). This entity must remain consistent for the entire application of the class.
 *
 * @author Harrison Mega
 * @version 1.2
 *
 * */
public class DatabaseControl<T> {
    private Connection connection;

    /**
     * This method is designed to execute simple parameterless SQL queries.
     *
     * @param query SQL query in string format.
     */
    public void executeParamQuery(String query) {
        try {
            connection = DatabaseConnection.getInstance();

            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is designed to execute more complex queries with parameters.
     *
     * @param query      SQL query in string format.
     * @param parameters List of parameters in Object format ie Object[] = {param 1,param 2...,param i};.
     */
    public int executeParamQuery(String query, Object[] parameters) {
        try {
            connection = DatabaseConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    //============================================================================================
    //                                          OWNER BLOC
    //============================================================================================
    public List<Owner> executeFetchAllOwner() {
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

                Owner owner = new Owner(firstName, lastName, email, password, signedIn);
                owner.setId(id); // Assuming you have a setId method in the Owner class
                owners.add(owner);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return owners;
    }

    //============================================================================================
    //                                          TENANT BLOC
    //============================================================================================

    public List<Tenant> executeFetchAllTenant() {
        List<Tenant> tenants = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tenants"; // Make sure to adjust the table name if different
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                int phoneNumber = resultSet.getInt("phoneNumber");

                Tenant tenant = new Tenant(firstName, lastName, email, phoneNumber);
                tenant.setId(id);
                tenants.add(tenant);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tenants;
    }

    public Tenant executeFetchAllPropertyTenant(Property property) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tenants WHERE property_id = ?";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                int phoneNumber = resultSet.getInt("phoneNumber");
                boolean assignedToProp = resultSet.getBoolean("assignedToProp");
                int propertyId = resultSet.getInt("propertyId");

                Tenant tenant = new Tenant(firstName, lastName, email, phoneNumber, assignedToProp, propertyId);
                tenant.setId(id);
                tenant.setPropertyId(property.getId());
                return tenant;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
