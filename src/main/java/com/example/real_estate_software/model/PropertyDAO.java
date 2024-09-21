package com.example.real_estate_software.model;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;

public class PropertyDAO {
    private Connection connection;

    //call owner as an argument then pass its ID

    public PropertyDAO(){
        connection = DatabaseConnection.getInstance();
    }
    public List<Property> getAllProperties(Owner owner) {
        List<Property> properties = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM properties WHERE owner_Id = ?");
            statement.setInt(1, owner.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("property_Id");
                String address = resultSet.getString("address");
                boolean tenanted = resultSet.getBoolean("tenanted");
                int num_Tenants = resultSet.getInt("number_Tenants");
                int num_Beds = resultSet.getInt("num_Beds");
                int num_Baths = resultSet.getInt("num_Baths");
                int num_Carspaces = resultSet.getInt("num_Carspaces");
                int weekly_Rent = resultSet.getInt("weekly_Rent");
                int weekly_Utilities = resultSet.getInt("weekly_Utilities");
                boolean connection = resultSet.getBoolean("connection");

                Property property = new Property(address, num_Beds, num_Baths, num_Carspaces, tenanted, num_Tenants, weekly_Rent, weekly_Utilities, connection);
                property.setId(id);
                properties.add(property);
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching properties: " + ex);
        }
        return properties;
    }

    public void create_Table_Property() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS properties ("
                    + "property_Id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "owner_Id INTEGER, "
                    + "address VARCHAR NOT NULL, "
                    + "tenanted BOOLEAN NOT NULL, "
                    + "number_Tenants INTEGER NOT NULL, "
                    + "num_Beds INTEGER NOT NULL, "
                    + "num_Baths INTEGER NOT NULL, "
                    + "num_Carspaces INTEGER NOT NULL, "
                    + "weekly_Rent INTEGER NOT NULL, "
                    + "weekly_Utilities INTEGER, "
                    + "connection BOOLEAN NOT NULL, "
                    + "FOREIGN KEY (owner_Id) REFERENCES owners(owner_Id) ON DELETE CASCADE"
                    + ")";
            statement.execute(query);
        } catch (SQLException ex) {
            System.err.println("Error creating properties table: " + ex.getMessage());
        }
    }

    public void insert_New_Property(Property property, Owner owner) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO properties (address, tenanted, number_Tenants, num_Beds, num_Baths, num_Carspaces, weekly_Rent, weekly_Utilities, owner_Id, connection) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, property.getAddress());
            statement.setBoolean(2, property.getTenanted());
            statement.setInt(3, property.getNum_Tenants());
            statement.setInt(4, property.getNum_Beds());
            statement.setInt(5, property.getNum_Bath());
            statement.setInt(6, property.getNum_Car());
            statement.setInt(7, property.getRent());
            statement.setInt(8, property.getUtilities());
            statement.setInt(9, owner.getId());  // Ensure correct owner ID is being inserted
            statement.setBoolean(10, property.getConnection());

            // Execute the statement
            statement.executeUpdate();

            // Retrieve the generated property ID and set it in the property object
            ResultSet generated_Keys = statement.getGeneratedKeys();
            if (generated_Keys.next()) {
                property.setId(generated_Keys.getInt(1));
            }

            // Debug output
            System.out.println("Inserted property for owner ID " + owner.getId() + ": " + property.getAddress());

        } catch (SQLException ex) {
            System.err.println("Error inserting property: " + ex);
        }
    }


    public void update_Property(Property property) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE properties SET"
                    + "address = ?, tenanted = ?, number_Tenants = ?, num_Beds = ?, num_Baths = ?, num_Carspaces = ?, weekly_Rent = ?, weekly_Utilities = ?, connection = ?, WHERE id = ?");
            statement.setString(1, property.getAddress());
            statement.setBoolean(2, property.getTenanted());
            statement.setInt(3, property.getNum_Tenants());
            statement.setInt(4, property.getNum_Beds());
            statement.setInt(5, property.getNum_Bath());
            statement.setInt(6, property.getNum_Car());
            statement.setInt(7, property.getRent());
            statement.setInt(8, property.getUtilities());
            statement.setBoolean(9, property.getConnection());
            statement.setInt(10, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
    public void insertDefaultTestProperties(Owner owner) {
        try {
            // Add a few test properties for the given owner
            Property property1 = new Property("123 Main St", 3, 2, 1, true, 2, 1200, 150);
            insert_New_Property(property1, owner);

            Property property2 = new Property("456 Elm St", 4, 3, 2, false, 0, 1500, 200);
            insert_New_Property(property2, owner);

            Property property3 = new Property("789 Oak St", 2, 1, 1, true, 1, 1000, 100);
            insert_New_Property(property3, owner);

        } catch (Exception ex) {
            System.err.println("Error inserting test properties: " + ex);
        }
    }

    public void delete_Property(Owner property) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM properties WHERE id = ?");
            statement.setInt(1, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public Property get_Property (boolean connected,Owner owner){
        try{
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM properties WHERE connection = ? AND owner_Id = ?");
           statement.setBoolean(1, connected);
           statement.setInt(2, owner.getId());
           ResultSet resultSet = statement.executeQuery(); //execute the search query
           if (resultSet.next()){ //keep returning results till null
               int id = resultSet.getInt("property_Id");
               String address = resultSet.getString("address");
               boolean tenanted = resultSet.getBoolean("tenanted");
               int num_Tenants = resultSet.getInt("number_Tenants");
               int num_Beds = resultSet.getInt("num_Beds");
               int num_Baths = resultSet.getInt("num_Baths");
               int num_Carspaces = resultSet.getInt("num_Carspaces");
               int weekly_Rent = resultSet.getInt("weekly_Rent");
               int weekly_Utilities = resultSet.getInt("weekly_Utilities");
               boolean connection = resultSet.getBoolean("connection"); //needs to be incroporated into other methods
               Property property = new Property(address, num_Beds, num_Baths, num_Carspaces, tenanted, num_Tenants, weekly_Rent, weekly_Utilities, connection);
               property.setId(id);
               return property;
           }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        return null;
    }

    public void close(){
        try{
            connection.close();
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
    }


}
