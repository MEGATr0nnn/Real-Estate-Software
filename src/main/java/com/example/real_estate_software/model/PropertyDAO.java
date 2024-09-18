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
                    + "property_Id INTEGER PRIMARY KEY AUTOINCREMENT,"
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

    public void insert_New_Property(Property property) {
        //need to include setting the id of the owner its associated with for the prim key
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO properties "
                    + "(address, tenanted, number_Tenants, num_Beds, num_Baths, num_Carspaces, weekly_Rent, weekly_Utilities) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, property.getAddress());
            statement.setBoolean(2, property.getTenanted());
            statement.setInt(3, property.getNum_Tenants());
            statement.setInt(4, property.getNum_Beds());
            statement.setInt(5, property.getNum_Bath());
            statement.setInt(6, property.getNum_Car());
            statement.setInt(7, property.getRent());
            statement.setInt(8, property.getUtilities());
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

    public void close(){
        try{
            connection.close();
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
    }
}
