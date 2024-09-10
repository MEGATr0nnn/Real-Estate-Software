package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlitePropertyDAO {
    private Connection connection;

    public SqlitePropertyDAO(){
        connection = SqliteConnection.getInstance();
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
