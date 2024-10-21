package com.example.real_estate_software.model;

import java.util.List;

/**
 * This is the DAO that links the Tenant Object to the Tenant Table in the DB
 *
 * @author Harrison Mega
 * @version 1
 *
 * */
public class TenantDAO implements IUserDAODoubleGeneric<Tenant, Property>{
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
                + "phoneNumber INTEGER NOT NULL,"
                + "assignedToProp BOOLEAN NOT NULL,"
                + "propertyId INTEGER NOT NULL"
                + ")";
        connect.executeParamQuery(query);
    }

    @Override
    public void insertNew(Tenant tenant, Property property) {
        String query = "INSERT INTO tenant (firstName, lastName, email, phoneNumber, propertyId) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail(),
                tenant.getPhoneNumber(),
                property.getId()
        };
        tenant.setId(connect.executeParamQuery(query, params));
    }

    @Override
    public void delete(Tenant tenant) {
        String query = "DELETE FROM tenant WHERE id = ?";
        Object[] params = {
                tenant.getId()
        };
        connect.executeParamQuery(query, params);
    }

    @Override
    public void update(Tenant tenant, Property property) {
        String query = "UPDATE tenant SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, assignedToProp = ?, property_id = ? WHERE id = ?";
        Object[] params = {
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail(),
                tenant.getPhoneNumber(),
                tenant.getPropertyId(),
                property.getId(),
                tenant.getId()
        };
        connect.executeParamQuery(query, params);
    }

    @Override
    public List<Tenant> getAll(){
        return connect.executeFetchAllTenant();
    }

        /*
    @Override
    public List<Tenant> getAll(Tenant tenant, Property property) {
        String query = "SELECT * FROM tenants WHERE property_id = ?";
        Object[] params = {
                property.getId()
        };
        return connect.executeFetchAllWithParam(query, params, Tenant.class);
    }
    */

    @Override
    public Tenant getAllBool(boolean bool) {
        return null;
    }




}
