package com.example.real_estate_software.model;


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
 * This class supports the use of generics to allow it to handle different types of entities, enabling reusability.
 *
 * Key Features:
 *  - Executes parameterized SQL queries to prevent SQL injection attacks.
 *  - Uses reflection to map SQL result sets to Java objects.
 *  - Supports generic fetch methods to retrieve data for different entity types.
 *  - Simplifies database interaction by centralizing repetitive tasks.
 *
 * @param <T> The type of entity that this class will handle (Owner, Tenant, Property etc.). This entity must remain consistent for the entire application of the class.
 *
 * @author Harrison Mega
 * @version 1.3
 *
 * */
public class DatabaseControl<T> {
    //============================================================================================
    //                                         GENERIC BLOC
    //============================================================================================
    private Connection connection;

    /**
     * This method is designed to execute simple parameterless SQL queries.
     *
     * @param query SQL query in string format.
     */
    public void executeQuery(String query) {
        try {
            connection = DatabaseConnection.getInstance();

            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is designed to execute more complex queries with parameters. Overloads original method.
     *
     * @param query      SQL query in string format.
     * @param parameters List of parameters in Object format ie Object[] = {param 1,param 2...,param i};.
     */
    public int executeQuery(String query, Object[] parameters) {
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
    /**
     * This method is designed to return a list of all Objects of type Owner from the DB
     *
     * @return A list of all Objects Owner in format List<Owner>OwnerValues</Owner>
     *
     * */
    public List<Owner> executeFetchAllOwner() {
        List<Owner> owners = new ArrayList<>();
        connection = DatabaseConnection.getInstance();
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

    /**
     * This method is designed to return a specific Object Owner where the signedIn value of Owner is true.
     *
     * @return A singular instance of Owner.
     *
     * */
    public Owner executeFetchOwner(boolean signedIn) {
        connection = DatabaseConnection.getInstance();
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

    //============================================================================================
    //                                          TENANT BLOC
    //============================================================================================
    /**
     *
     * */
    public List<Tenant> executeFetchAllTenant() {
        List<Tenant> tenants = new ArrayList<>();
        connection = DatabaseConnection.getInstance();
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
                boolean assignedToProp = resultSet.getBoolean("assignedToProp");

                Tenant tenant = new Tenant(firstName, lastName, email, phoneNumber, assignedToProp);
                tenant.setId(id);
                tenants.add(tenant);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tenants;
    }

    public Tenant executeFetchAllPropertyTenant(Property property) {
        connection = DatabaseConnection.getInstance();
        try {
            String query = "SELECT * FROM tenants WHERE propertyId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, property.getId());
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                int phoneNumber = resultSet.getInt("phoneNumber");
                boolean assignedToProp = resultSet.getBoolean("assignedToProp");

                Tenant tenant = new Tenant(firstName, lastName, email, phoneNumber, assignedToProp);
                tenant.setId(id);
                return tenant;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
