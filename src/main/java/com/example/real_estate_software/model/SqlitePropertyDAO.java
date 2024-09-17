package com.example.real_estate_software.model;

import java.sql.Connection;
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
                    + "weekly_Rent VARCHAR NOT NULL"
                    + "weekly_Utilities VARCHAR"
                    + ")";
            statement.execute(query);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void insert_New_Property() {
        //temp will fill out
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
