package com.example.real_estate_software.model;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public class TenantDAO implements IUserDAO<Tenant>{
    private DatabaseControl<Tenant> connect;

    public TenantDAO(){
        createTable();
    }

    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS tenant ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "firstName VARCHAR NOT NULL,"
                + "lastName VARCHAR NOT NULL,"
                + "email VARCHAR NOT NULL,"
                + "phoneNumber INTEGER NOT NULL"
                + "property_id INTEGER FOREIGN KEY"
                + ")";
        connect.executeQuery(query);
    }

    @Override
    public void insertNew(Tenant tenant) {
        String query = "INSERT INTO tenant (firstName, lastName, email, phoneNumber, property_id) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail(),
                tenant.getPhoneNumber()
                //need a way to get property id's via the instance the owner is logged in as to assign tenants to properties
        };
        tenant.setId(connect.executeQuery(query, params));
    }

    @Override
    public void delete(Tenant tenant) {
        String query = "DELETE FROM tenant WHERE id = ?";
        Object[] params = {
                tenant.getId()
        };
        connect.executeQuery(query, params);
    }

    @Override
    public void update(Tenant tenant) {
        String query = "UPDATE tenant SET firstName = ?, lastName = ?, email = ?, property_id WHERE id = ?";
        Object[] params = {
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail(),
                tenant.getId()
        };
        connect.executeQuery(query, params);
    }

    @Override
    public List<Tenant> getAll(Tenant tenant) {
        return null;
    }
}
