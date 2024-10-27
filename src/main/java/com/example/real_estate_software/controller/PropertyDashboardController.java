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

/**
 * Class that is used for the Property Dashboard
 */
public class PropertyDashboardController extends AbstractController {
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
        return getTenantDAO().getAllType(property).size() * property.getRent();
    }

    private int calculateVacantRooms(Property property) {
        return property.getNum_Beds() - getTenantDAO().getAllType(property).size();
    }

    private int calculatePropertyValuation(Property property) {
        return property.getRent() * 52 * 10;
    }

    private int getWaterBill(Property property) {return getUtilitiesDAO().getUtilities(property).getWaterUtilities();}

    private int getElectricityBill(Property property) {return getUtilitiesDAO().getUtilities(property).getElectricityUtilities();}

    private int getGasBill(Property property) {return getUtilitiesDAO().getUtilities(property).getGasUtilities();}

    private int calculateBondAmount(Property property) {
        return property.getRent() * 4;
    }

    /**
     * Button action for when the owner wants to add a new tenant to the associated property
     * Upon pressing the button, the user is directed to the Add Tenant Page
     * @throws IOException
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
     * Upon pressing the button, the user is directed to the Remove Tenant Page
     * @throws IOException
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
     * Upon pressing the button, the user is directed to the Rent Page
     * @throws IOException
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

    /**
     * Button action the directs the user to the Utilities Page
     * @throws IOException
     */
    @FXML
    protected void onUtilitiesClick() throws IOException {
        Stage stage = (Stage) utilities.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Utilities.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button action that directs the user to the Property Maintenance Page
     * @throws IOException
     */
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


