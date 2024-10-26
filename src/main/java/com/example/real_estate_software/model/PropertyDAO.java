package com.example.real_estate_software.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
Property DAO for DB property data (DATABASE MANAGER ABSTRACT CLASS)
 **/
public class PropertyDAO {
    private Connection connection;
    private DatabaseControl<Property> connect;

    public PropertyDAO(){
        connection = DatabaseConnection.getInstance();
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
                + "weekly_Rent INTEGER NOT NULL,"
                + "weekly_Utilities INTEGER NOT NULL,"
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
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO properties "
                    + "(ownerId, address, num_Tenants, num_Beds, num_Baths, num_Cars, weekly_Rent, weekly_Utilities, has_Tenants, is_Selected) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, owner.getId());
            statement.setString(2, property.getAddress());
            statement.setInt(3, property.getNum_Tenants());
            statement.setInt(4, property.getNum_Beds());
            statement.setInt(5, property.getNum_Baths());
            statement.setInt(6, property.getNum_Cars());
            statement.setInt(7, property.getRent());
            statement.setInt(8, property.getUtilities());
            statement.setBoolean(9, property.getHas_Tenants());
            statement.setBoolean(10, property.getIs_Selected());
            statement.executeUpdate();
            ResultSet generated_Keys = statement.getGeneratedKeys();
            if (generated_Keys.next()){
                property.setId(generated_Keys.getInt(1));
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    /**
     Updates DB from current property ie property A1 is now updated to be property A1.1 with new data
     **/
    public void update_Property(Property property) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE properties SET "
                    + "address = ?, num_Tenants = ?, num_Beds = ?, num_Baths = ?, num_Cars = ?, weekly_Rent = ?, weekly_Utilities = ?, has_Tenants = ?, is_Selected = ? WHERE id = ?");
            statement.setString(1, property.getAddress());
            statement.setInt(2, property.getNum_Tenants());
            statement.setInt(3, property.getNum_Beds());
            statement.setInt(4, property.getNum_Baths());
            statement.setInt(5, property.getNum_Cars());
            statement.setInt(6, property.getRent());
            statement.setInt(7, property.getUtilities());
            statement.setBoolean(8, property.getHas_Tenants());
            statement.setBoolean(9, property.getIs_Selected());
            statement.setInt(10, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    /**
     Deletes property info from DB
     **/
    public void delete_Property(Property property) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM properties WHERE id = ?");
            statement.setInt(1, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    /**
     Gets data of a specific property from the id of the property and the owners id
     **/
    public Property get_Property (boolean is_Selected){
        try{
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM properties WHERE is_Selected = ?");
           statement.setBoolean(1, is_Selected);
           ResultSet resultSet = statement.executeQuery(); //execute the search query
           if (resultSet.next()){ //keep returning results till null
               int id = resultSet.getInt("id");
               String address = resultSet.getString("address");
               int num_Tenants = resultSet.getInt("num_Tenants");
               int num_Beds = resultSet.getInt("num_Beds");
               int num_Baths = resultSet.getInt("num_Baths");
               int num_Cars = resultSet.getInt("num_Cars");
               int weekly_Rent = resultSet.getInt("weekly_Rent");
               int weekly_Utilities = resultSet.getInt("weekly_Utilities");
               is_Selected = resultSet.getBoolean("is_Selected");
               Property property = new Property(address, num_Tenants, num_Beds, num_Baths, num_Cars, weekly_Rent, weekly_Utilities, is_Selected);
               property.setId(id);
               return property;
           }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        return null;
    }

    /**
     Returns a list of properties based of the id of the current owner, this means that your only getting the
     data of the properties associated with your user
     **/
    public List<Property> get_OwnerProperties(Owner owner) {
        List<Property> properties = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM properties WHERE ownerId = ?");
            statement.setInt(1, owner.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                int num_Tenants = resultSet.getInt("num_Tenants");
                int num_Beds = resultSet.getInt("num_Beds");
                int num_Baths = resultSet.getInt("num_Baths");
                int num_Cars = resultSet.getInt("num_Cars");
                int weekly_Rent = resultSet.getInt("weekly_Rent");
                int weekly_Utilities = resultSet.getInt("weekly_Utilities");
                boolean is_Selected = resultSet.getBoolean("is_Selected");
                Property property = new Property(address, num_Tenants, num_Beds, num_Baths, num_Cars, weekly_Rent, weekly_Utilities, is_Selected);
                property.setId(id);
                properties.add(property);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        return properties;
    }

}
