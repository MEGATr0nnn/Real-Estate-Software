package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlitePropertyDAO {
    private Connection connection;

    public SqlitePropertyDAO(){
        connection = SqliteConnection.getInstance();
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
        //need to include setting the counter for the prim key
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
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void update_Property() {
        //temp
    }

    public void delete_Property() {
        //temp
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
