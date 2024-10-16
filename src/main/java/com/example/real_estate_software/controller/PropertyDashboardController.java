package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import com.example.real_estate_software.model.Property;
import com.example.real_estate_software.model.PropertyDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PropertyDashboardController {
    public Button backButton;
    public Button maintenanceRequest;
    public Button utilities;
    public Button assignRent;
    public Button addTenant;
    public Button removeTenant;
    public Text gasBillText;
    public Text waterBillText;
    public Text electricityBillText;
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
    private final OwnerDAO ownerDAO;
    private final PropertyDAO propertyDAO;

    public PropertyDashboardController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
    }

    @FXML
    public void initialize() {
        // Set the current property
        Owner currentOwner = ownerDAO.getOwner(true);
        Property currentProperty = propertyDAO.get_Property(true, currentOwner);

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
        Owner currentOwner = ownerDAO.getOwner(true);
        Property currentProperty = propertyDAO.get_Property(true, currentOwner);
        currentProperty.setIs_Selected(false);
        propertyDAO.update_Property(currentProperty);
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
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
     * Button action for when the owner wants to remove a new tenant to the associated property
     */
    @FXML
    protected void onRemoveTenantClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RemoveTenant.fxml"));
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

    public void onUtilitiesClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Utilities.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}


