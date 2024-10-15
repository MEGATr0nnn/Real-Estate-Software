package com.example.real_estate_software.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseControl<T> {
    private Connection connection;

    public void executeQuery(String query){
        try{
            connection = DatabaseConnection.getInstance();

            Statement statement = connection.createStatement();
            statement.execute(query);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int executeQuery(String query, Object[] parameters){
        try
        {
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

}
