package com.example.real_estate_software.model;


import java.lang.reflect.Field;
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

    public <T> List<T> executeFetchAll(String query, Object[] parameters, Class<T> uniqueClass){
        List<T> results = new ArrayList<>();

        try{
            connection = DatabaseConnection.getInstance();

            try(PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < parameters.length; i++) {
                    statement.setObject(i + 1, parameters[i]);
                }
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    T entity = uniqueClass.getDeclaredConstructor().newInstance(); //create new instance of entity ie owner

                    //map names of columns from result set
                    for(Field field : uniqueClass.getDeclaredFields()){
                        field.setAccessible(true); //allow access to private fields
                        field.set(entity, resultSet.getObject(field.getName()));//set value based on name of SQL column
                    }
                    results.add(entity);
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return results;
    }
}
