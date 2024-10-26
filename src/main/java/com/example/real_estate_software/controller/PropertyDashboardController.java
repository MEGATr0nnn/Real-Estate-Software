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
import java.util.Objects;

public class PropertyDashboardController extends AbstractController {
    @FXML
    private Button backButton;
    @FXML
    private Button maintenanceRequest;
    @FXML
    private Button utilities;
    @FXML
    private Button assignRent;
    @FXML
    private Button addTenant;
    @FXML
    private Button removeTenant;
    @FXML
    private Text gasBillText;
    @FXML
    private Text waterBillText;
    @FXML
    private Text electricityBillText;
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

    public PropertyDashboardController() {
        super();
    }

    @FXML
    public void initialize() {
        // Set the current property
        Property currentProperty = getPropertyDAO().get_Property(true);

        // Populate the UI with the property data
        addressText.setText(currentProperty.getAddress());
        totalRentalIncomeText.setText("Total Rental Income: $" + calculateTotalRentalIncome(currentProperty));
        vacantRoomsText.setText("Number of Vacant Rooms: " + calculateVacantRooms(currentProperty));
        carSpotText.setText("Car Spot: " + currentProperty.getNum_Cars());
        bondAmountText.setText("Total Bond Amount: $" + calculateBondAmount(currentProperty));
        propertyValuationText.setText("Property Valuation: $" + calculatePropertyValuation(currentProperty));
        electricityBillText.setText("Electricity Bill: $" + getElectricityBill(currentProperty));
        gasBillText.setText("Gas Bill: $" + getGasBill(currentProperty));
        waterBillText.setText("Water Bill: $" + getWaterBill(currentProperty));
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

    //NEEDS TO BE IMPLEMENTED ONCE DAO IS DONE
    private int getElectricityBill(Property property) {return 0;}

    //NEEDS TO BE IMPLEMENTED ONCE DAO IS DONE
    private int getWaterBill(Property property) {return 0;}

    //NEEDS TO BE IMPLEMENTED ONCE DAO IS DONE
    private int getGasBill(Property property) {return 0;}

    /**
     * Button action for when the user wants to revert back to the Property Dashboard page
     */
    @FXML
    protected void onBackClick() throws IOException {
        Property currentProperty = getPropertyDAO().get_Property(true);
        currentProperty.setIs_Selected(false);
        getPropertyDAO().update_Property(currentProperty);
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button action for when the owner wants to add a new tenant to the associated property
     */
    @FXML
    protected void onTenantClick() throws IOException {
        Stage stage = (Stage) addTenant.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddTenant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button action for when the owner wants to remove a new tenant to the associated property
     */
    @FXML
    protected void onRemoveTenantClick() throws IOException {
        Stage stage = (Stage) removeTenant.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RemoveTenant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button action for when the owner wants to assign rent to the tenants associated with the property
     */
    @FXML
    protected void onRentClick() throws IOException {
        Stage stage = (Stage) assignRent.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Rent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onUtilitiesClick() throws IOException {
        Stage stage = (Stage) utilities.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Utilities.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onPropertyMaintenanceClick() throws IOException {
        // Correctly specify the location of the FXML file for the stats page
        Stage stage = (Stage) maintenanceRequest.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyMaintenance.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Get the current stage and set the new scene
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}


