package com.example.real_estate_software.model;

import java.sql.*;
import java.util.List;

/**
 * Property DAO for DB property data
 *
 * @author Harrison Mega, Steven Hujbert
 * @version 1
 *
 */
public class PropertyDAO {
    private DatabaseControl<Property> connect;

    public PropertyDAO(){
        connect = new DatabaseControl<Property>();
        create_Table_Property();
    }

    /**
    Creates table in DB
     **/
    public void create_Table_Property() {
        String query = "CREATE TABLE IF NOT EXISTS properties ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "ownerId INTEGER NOT NULL,"
                + "address VARCHAR NOT NULL,"
                + "num_Tenants INTEGER NOT NULL,"
                + "num_Beds INTEGER NOT NULL,"
                + "num_Baths INTEGER NOT NULL,"
                + "num_Cars INTEGER NOT NULL,"
                + "has_tenants BOOLEAN NOT NULL,"
                + "is_Selected BOOLEAN NOT NULL,"
                + "FOREIGN KEY (ownerId) REFERENCES owners(id)"
                + ")";
        connect.executeQuery(query);
    }

    /**
     inserts new comments into DB
     **/
    public void insert_New_Property(Property property, Owner owner) {
        String query = ("INSERT INTO properties "
                + "(ownerId, address, num_Tenants, num_Beds, num_Baths, num_Cars, has_Tenants, is_Selected) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        Object[] params = {
                owner.getId(),
                property.getAddress(),
                property.getNum_Tenants(),
                property.getNum_Beds(),
                property.getNum_Baths(),
                property.getNum_Cars(),
                property.getHas_Tenants(),
                property.getIs_Selected()
        };
        property.setId(connect.executeQuery(query, params));
    }

    /**
     Updates DB from current property ie property A1 is now updated to be property A1.1 with new data
     **/
    public void update_Property(Property property) {
        String query = ("UPDATE properties SET "
                + "address = ?, num_Tenants = ?, num_Beds = ?, num_Baths = ?, num_Cars = ?, has_Tenants = ?, is_Selected = ? WHERE id = ?");
        Object[] params = {
                property.getAddress(),
                property.getNum_Tenants(),
                property.getNum_Beds(),
                property.getNum_Baths(),
                property.getNum_Cars(),
                property.getHas_Tenants(),
                property.getIs_Selected(),
                property.getId()
        };
        connect.executeQuery(query, params);
    }

    /**
     Deletes property info from DB
     **/
    public void delete_Property(Property property) {
        String query = ("DELETE FROM properties WHERE id = ?");
        Object[] params = {
                property.getId()
        };
        connect.executeQuery(query, params);
    }

    /**
     * Gets data of a specific property from the id of the property and the owners id
     * @param is_Selected A SQL table field that indicates if the property is selected by the user or not.
     */
    public Property get_Property (boolean is_Selected) {
        return connect.executeFetchProperty(is_Selected);
    }

    /**
     Returns a list of properties based of the id of the current owner, this means that your only getting the
     data of the properties associated with your user
     **/
    public List<Property> get_OwnerProperties(Owner owner) {
        return connect.executeFetchAllOwnerProperties(owner);
    }
}
