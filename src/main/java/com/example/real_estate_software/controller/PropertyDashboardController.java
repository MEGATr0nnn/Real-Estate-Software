package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PropertyDashboardController {
    public Button backButton;
    public Button maintenanceRequest;
    public Button utilities;
    public Button assignRent;
    public Button addTenant;
    @FXML
    private Text addressText;
    @FXML
    private Text totalRentalIncomeText;
    @FXML
    private Text vacantRoomsText;
    @FXML
    private Text carSpotText;
    @FXML
    private Text bondAmountText;
    @FXML
    private Text propertyValuationText;
    @FXML
    private Text welcomeText;


    private Property currentProperty;

    public void setProperty(Property property) {
        // Set the current property
        this.currentProperty = property;

        // Populate the UI with the property data
        addressText.setText(property.getAddress());
        totalRentalIncomeText.setText("Total Rental Income: $" + calculateTotalRentalIncome(property));
        vacantRoomsText.setText("Number of Vacant Rooms: " + calculateVacantRooms(property));
        carSpotText.setText("Car Spot: " + property.getNum_Cars());
        bondAmountText.setText("Total Bond Amount: $" + calculateBondAmount(property));
        propertyValuationText.setText("Property Valuation: $" + calculatePropertyValuation(property));


    }


    private int calculateTotalRentalIncome(Property property) {
        return property.getNum_Tenants() * property.getRent();
    }

    private int calculateVacantRooms(Property property) {
        return property.getNum_Beds() - property.getNum_Tenants();
    }

    private int calculateBondAmount(Property property) {
        return property.getRent() * 4;
    }

    private int calculatePropertyValuation(Property property) {

        return property.getRent() * 52 * 10;
    }


    /**
     * Button action for when the user wants to revert back to the Property Dashboard page
     */
    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button action for when the owner wants to add a new tenant to the associated property
     */
    @FXML
    protected void onTenantClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddTenant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button action for when the owner wants to assign rent to the tenants associated with the property
     */
    public void onRentClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Rent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}


