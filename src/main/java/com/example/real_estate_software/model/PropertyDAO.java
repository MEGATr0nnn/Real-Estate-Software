package com.example.real_estate_software.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {
    private Connection connection;

    //call owner as an argument then pass its ID

    public PropertyDAO(){
        connection = DatabaseConnection.getInstance();
        create_Table_Property();
    }

    public void create_Table_Property() {
        try{
            //this still needs to be edited so that the tenant primary key is pulled
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS properties ("
                    + "property_Id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "owner_Id INTEGER NOT NULL,"
                    + "address VARCHAR NOT NULL,"
                    + "tenanted BOOLEAN NOT NULL,"
                    + "number_Tenants INTEGER NOT NULL,"
                    + "num_Beds INTEGER NOT NULL,"
                    + "num_Baths INTEGER NOT NULL,"
                    + "num_Carspaces INTEGER NOT NULL,"
                    + "weekly_Rent INTEGER NOT NULL,"
                    + "weekly_Utilities INTEGER NOT NULL,"
                    + "FOREIGN KEY (owner_Id) REFERENCES owners(id)"
                    + ")";
            statement.execute(query);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void insert_New_Property(Property property, Owner owner) {
        //need to include setting the id of the owner its associated with for the foreign key
        //fix this to be in the right order for inserting into the table eventually
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO properties "
                    + "(owner_Id, address, tenanted, number_Tenants, num_Beds, num_Baths, num_Carspaces, weekly_Rent, weekly_Utilities) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, owner.getId());
            statement.setString(2, property.getAddress());
            statement.setBoolean(3, property.getTenanted());
            statement.setInt(4, property.getNum_Tenants());
            statement.setInt(5, property.getNum_Beds());
            statement.setInt(6, property.getNum_Bath());
            statement.setInt(7, property.getNum_Car());
            statement.setInt(8, property.getRent());
            statement.setInt(9, property.getUtilities());
            statement.executeUpdate();
            ResultSet generated_Keys = statement.getGeneratedKeys();
            if (generated_Keys.next()){
                property.setId(generated_Keys.getInt(1));
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void update_Property(Property property) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE properties SET "
                    + "address = ?, tenanted = ?, number_Tenants = ?, num_Beds = ?, num_Baths = ?, num_Carspaces = ?, weekly_Rent = ?, weekly_Utilities = ? WHERE property_Id = ?");
            statement.setString(1, property.getAddress());
            statement.setBoolean(2, property.getTenanted());
            statement.setInt(3, property.getNum_Tenants());
            statement.setInt(4, property.getNum_Beds());
            statement.setInt(5, property.getNum_Bath());
            statement.setInt(6, property.getNum_Car());
            statement.setInt(7, property.getRent());
            statement.setInt(8, property.getUtilities());
            statement.setInt(9, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void delete_Property(Property property) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM properties WHERE property_Id = ?");
            statement.setInt(1, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public Property get_Property (Property property, Owner owner){
        try{
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM properties WHERE property_Id = ? AND owner_Id = ?");
           statement.setInt(1, property.getId());
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
               property = new Property(address, tenanted, num_Tenants, num_Beds, num_Baths, num_Carspaces, weekly_Rent, weekly_Utilities);
               property.setId(id);
               return property;
           }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        return null;
    }

    public List<Property> get_AllOwnerProperties(Owner owner) {
        List<Property> properties = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM properties WHERE owner_Id = ?");
            statement.setInt(1, owner.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("property_Id");
                String address = resultSet.getString("address");
                boolean tenanted = resultSet.getBoolean("tenanted");
                int num_Tenants = resultSet.getInt("number_Tenants");
                int num_Beds = resultSet.getInt("num_Beds");
                int num_Baths = resultSet.getInt("num_Baths");
                int num_Carspaces = resultSet.getInt("num_Carspaces");
                int weekly_Rent = resultSet.getInt("weekly_Rent");
                int weekly_Utilities = resultSet.getInt("weekly_Utilities");
                Property property = new Property(address, tenanted, num_Tenants, num_Beds, num_Baths, num_Carspaces, weekly_Rent, weekly_Utilities);
                property.setId(id);
                properties.add(property);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        return properties;
    }
}
