package com.example.real_estate_software.controller;

import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.Property;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Class that is used for the Add Property Page
 */
public class AddPropertyController extends AbstractController {
    @FXML
    private TextField addressField;
    @FXML
    private TextField bedsField;
    @FXML
    private TextField bathsField;
    @FXML
    private TextField carsField;
    @FXML
    private TextField rentField;
    @FXML
    private TextField utilitiesField;

    public AddPropertyController() {
        super();
    }

    /**
     * Button action for when the Owner adds a property
     * Upon pressing the button, a property will be added to the Main Dashboard
     */
    @FXML
    protected void onAddPropertyClick() {
        if(!emptyFields()) {
            addProperty();
            clearFields();
        }
    }

    /**
     * Used to add property to the database
     */
    private void addProperty() {
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        String address = addressField.getText();
        int beds = Integer.parseInt(bedsField.getText());
        int baths = Integer.parseInt(bathsField.getText());
        int cars = Integer.parseInt(carsField.getText());
        int rent = Integer.parseInt(rentField.getText());
        int utilities = Integer.parseInt(utilitiesField.getText());
        getPropertyDAO().insert_New_Property(new Property(address, beds, baths, cars, rent, utilities), currentOwner);
    }

    /**
     * Used to clear text fields after adding property
     */
    private void clearFields() {
        addressField.clear();
        bedsField.clear();
        bathsField.clear();
        carsField.clear();
        rentField.clear();
        utilitiesField.clear();
    }

    /**
     * Used to see if any text fields are empty
     * @return boolean
     */
    private boolean emptyFields() {
        boolean emptyAddress = addressField.getText().trim().isEmpty();
        boolean emptyBeds = bedsField.getText().trim().isEmpty();
        boolean emptyBaths = bathsField.getText().trim().isEmpty();
        boolean emptyCars = carsField.getText().trim().isEmpty();
        boolean emptyRent = rentField.getText().trim().isEmpty();
        boolean emptyUtilities = utilitiesField.getText().trim().isEmpty();
        return emptyAddress || emptyBeds || emptyBaths || emptyCars || emptyRent || emptyUtilities;
    }

}
