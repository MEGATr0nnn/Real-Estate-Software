package com.example.real_estate_software.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * UtilitiesDAO for database utilities data
 */
public class UtilitiesDAO {
    private Connection connection;
    private DatabaseControl<Utilities> connect;

    public UtilitiesDAO() {
        connection = DatabaseConnection.getInstance();
        connect = new DatabaseControl<Utilities>();
        createTable();
    }

    /**
     Creates table in DB
     **/
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS utilities ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "waterUtil INTEGER NOT NULL,"
                + "electricUtil INTEGER NOT NULL,"
                + "gasUtil INTEGER NOT NULL,"
                + "totalUtil INTEGER NOT NULL,"
                + "FOREIGN KEY (id) REFERENCES properties(id)"
                + ")";
        connect.executeQuery(query);
    }

    /**
     inserts new row into DB
     **/
    public void addUtilities(Utilities utilities, Property property) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO utilities "
                    + "(id, waterUtil, electricUtil, gasUtil, totalUtil) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, property.getId());
            statement.setInt(2, utilities.getWaterUtilities());
            statement.setInt(3, utilities.getElectricityUtilities());
            statement.setInt(4, utilities.getGasUtilities());
            statement.setInt(5, utilities.getTotalUtilites());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    /**
     Updates DB from current utilities ie utilities A1 is now updated to be utilities A1.1 with new data
     **/
    public void updateUtilities(Utilities utilities, Property property) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE utilities SET "
                    + "waterUtil = ?, electricUtil = ?, gasUtil = ?, totalUtil = ? WHERE id = ?");
            statement.setInt(1, utilities.getWaterUtilities());
            statement.setInt(2, utilities.getElectricityUtilities());
            statement.setInt(3, utilities.getGasUtilities());
            statement.setInt(4, utilities.getTotalUtilites());
            statement.setInt(5, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    /**
     Deletes utilities info from DB
     **/
    public void deleteUtilities(Property property) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM utilities WHERE id = ?");
            statement.setInt(1, property.getId());
            statement.executeUpdate();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    /**
     Gets data of utilities from the id of the property
     **/
    public Utilities getUtilities (Property property){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilities WHERE id = ?");
            statement.setInt(1, property.getId());
            ResultSet resultSet = statement.executeQuery(); //execute the search query
            if (resultSet.next()){ //keep returning results till null
                int id = resultSet.getInt("id");
                int waterUtil = resultSet.getInt("waterUtil");
                int electricUtil = resultSet.getInt("electricUtil");
                int gasUtil = resultSet.getInt("gasUtil");
                Utilities utilities = new Utilities(waterUtil, electricUtil, gasUtil);
                utilities.setId(id);
                return utilities;
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        return null;
    }
}
