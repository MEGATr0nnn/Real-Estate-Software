package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.SQLException;

public class PropertyDAO {
    private Connection connection;

    public PropertyDAO(){
        connection = DatabaseConnection.getInstance();
    }

    public void create_Table() {
        //temp
    }

    public void insert() {
        //temp will fill out
    }

    public void update() {
        //temp
    }

    public void delete() {
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
