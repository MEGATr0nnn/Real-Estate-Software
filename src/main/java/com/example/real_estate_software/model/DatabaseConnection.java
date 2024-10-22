package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to establish a connection instance to the DB and handle any exceptions thrown by DB connection issues
 *
 * @author Steven Hujbert
 * @version 1
 *
 * */
public class DatabaseConnection {
    private static Connection instance = null;

    /**
     * This method is used to establish a connection to the specified DB string
     * Throws an exception from the Java SQL framework
     * */
    private DatabaseConnection() {
        String url = "jdbc:sqlite:database.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    /**
     * This method is used to get the specific instance of the connection
     * @return instance This class returns the instance in Connection format
     * */
    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }
}