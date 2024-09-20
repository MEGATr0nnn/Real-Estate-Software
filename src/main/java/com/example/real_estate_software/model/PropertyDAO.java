package com.example.real_estate_software.model;

import java.sql.*;

public class PropertyDAO {
    private Connection connection;

    //call owner as an argument then pass its ID

    public PropertyDAO(){
        connection = DatabaseConnection.getInstance();
    }

    public void create_Table_Property() {
        try{
            //this still needs to be edited so that the tenant primary key is pulled
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS properties ("
                    + "property_Id INTEGER PRIMARY KEY AUTOINCREMENT"
                    + "owner_Id INTEGER FOREIGN KEY"
                    + "address VARCHAR NOT NULL"
                    + "tenanted BOOLEAN NOT NULL"
                    + "number_Tenants INTEGER NOT NULL"
                    + "num_Beds INTEGER NOT NULL"
                    + "num_Baths INTEGER NOT NULL"
                    + "num_Carspaces INTEGER NOT NULL"
                    + "weekly_Rent INTEGER NOT NULL"
                    + "weekly_Utilities INTEGER"
                    + ")";
            statement.execute(query);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void insert_New_Property(Property property, Owner owner) {
        //need to include setting the id of the owner its associated with for the foreign key
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO properties "
                    + "(address, tenanted, number_Tenants, num_Beds, num_Baths, num_Carspaces, weekly_Rent, weekly_Utilities, owner_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, property.getAddress());
            statement.setBoolean(2, property.getTenanted());
            statement.setInt(3, property.getNum_Tenants());
            statement.setInt(4, property.getNum_Beds());
            statement.setInt(5, property.getNum_Bath());
            statement.setInt(6, property.getNum_Car());
            statement.setInt(7, property.getRent());
            statement.setInt(8, property.getUtilities());
            statement.setInt(9, owner.getId());
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
            PreparedStatement statement = connection.prepareStatement("UPDATE properties SET"
                    + "address = ?, tenanted = ?, number_Tenants = ?, num_Beds = ?, num_Baths = ?, num_Carspaces = ?, weekly_Rent = ?, weekly_Utilities = ? WHERE id = ?");
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
