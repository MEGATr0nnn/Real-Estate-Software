package com.example.real_estate_software.model;

import java.sql.Connection;

public class SqliteOwnerDAO {
    private Connection connection;

    public SqliteOwnerDAO() {
        connection = SqliteConnection.getInstance();
    }
}
