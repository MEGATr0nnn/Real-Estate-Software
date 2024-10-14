package com.example.real_estate_software.model;

import java.util.List;

public class TenantDAO implements IUserDAO<Tenant>{
    private DatabaseControl<Tenant> connect;

    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS tenant ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "firstName VARCHAR NOT NULL,"
                + "lastName VARCHAR NOT NULL,"
                + "email VARCHAR NOT NULL,"
                + "password VARCHAR NOT NULL,"
                + "signedIn BOOLEAN NOT NULL"
                + ")";
        connect.executeQuery(query);
    }

    @Override
    public void insertNew(Tenant tenant) {
        String query = "INSERT INTO owners (firstName, lastName, email, password, signedIn) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail()
        };
        tenant.setId(connect.executeQuery(query, params));
    }

    @Override
    public void delete(Tenant tenant) {

    }

    @Override
    public void update(Tenant tenant) {

    }

    @Override
    public List<Tenant> getAll(Tenant tenant) {
        return List.of();
    }
}
