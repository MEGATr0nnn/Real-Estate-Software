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
        connect = new DatabaseControl<Tenant>();
        createTable();
    }

    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS tenants ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "propertyId INTEGER NOT NULL,"
                + "firstName VARCHAR NOT NULL,"
                + "lastName VARCHAR NOT NULL,"
                + "email VARCHAR NOT NULL,"
                + "phoneNumber VARCHAR NOT NULL,"
                + "assignedToProp BOOLEAN NOT NULL,"
                + "FOREIGN KEY (propertyId) REFERENCES properties(id)"
                + ")";
        connect.executeQuery(query);
    }

    @Override
    public void insertNew(Tenant tenant, Property property) {
        String query = "INSERT INTO tenants (propertyId, firstName, lastName, email, phoneNumber, assignedToProp) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] params = {
                property.getId(),
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail(),
                tenant.getPhoneNumber(),
                tenant.getAssignedToProp()
        };
        tenant.setId(connect.executeQuery(query, params));
    }

    @Override
    public void delete(Tenant tenant) {
        String query = "DELETE FROM tenants WHERE id = ?";
        Object[] params = {
                tenant.getId()
        };
        connect.executeQuery(query, params);
    }

    @Override
    public void update(Tenant tenant) {
        String query = "UPDATE tenants SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, assignedToProp = ? WHERE id = ?";
        Object[] params = {
                tenant.getFirstName(),
                tenant.getLastName(),
                tenant.getEmail(),
                tenant.getPhoneNumber(),
                tenant.getAssignedToProp(),
                tenant.getId()
        };
        connect.executeQuery(query, params);
    }

    @Override
    public List<Tenant> getAll(){
        return connect.executeFetchAllTenant();
    }

    @Override
    public Tenant getAllBool(Property property) {
        return connect.executeFetchAllPropertyTenant(property);
    }

}
