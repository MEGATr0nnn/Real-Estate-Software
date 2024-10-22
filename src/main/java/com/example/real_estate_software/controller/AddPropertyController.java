package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import com.example.real_estate_software.model.Property;
import com.example.real_estate_software.model.PropertyDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class AddPropertyController {
    @FXML
    private TextField addressField;
    @FXML
    private TextField tenantField;
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
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    public Button addImageButton;
    public Button saveButton;
    private AnchorPane propertyContainer;
    public Button propertyDashboard;
    @FXML
    private Button cogsButton;
    @FXML
    private Button barChartButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button signOutButton;
    @FXML
    private Button addPropertyButton;
    @FXML
    private GridPane propertyGrid;
    @FXML
    private Button editButton;
    @FXML
    private Button viewStatsButton;
    private OwnerDAO ownerDAO;
    private PropertyDAO propertyDAO;

    public AddPropertyController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
    }

    @FXML
    protected void onAddClick() throws IOException {
        if(!emptyFields()) {
            addProperty();
            clearFields();
        }
    }

    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
        stage.setScene(scene);
    }

    private void addProperty() {
        Owner currentOwner = ownerDAO.getAllBool(true);
        String address = addressField.getText();
        int tenants = Integer.parseInt(tenantField.getText());
        int beds = Integer.parseInt(bedsField.getText());
        int baths = Integer.parseInt(bathsField.getText());
        int cars = Integer.parseInt(carsField.getText());
        int rent = Integer.parseInt(rentField.getText());
        int utilities = Integer.parseInt(utilitiesField.getText());
        propertyDAO.insert_New_Property(new Property(address, tenants, beds, baths, cars, rent, utilities), currentOwner);
    }

    private void clearFields() {
        addressField.clear();
        tenantField.clear();
        bedsField.clear();
        bathsField.clear();
        carsField.clear();
        rentField.clear();
        utilitiesField.clear();
    }

    private boolean emptyFields() {
        boolean emptyAddress = addressField.getText().trim().isEmpty();
        boolean emptyTenant = tenantField.getText().trim().isEmpty();
        boolean emptyBeds = bedsField.getText().trim().isEmpty();
        boolean emptyBaths = bathsField.getText().trim().isEmpty();
        boolean emptyCars = carsField.getText().trim().isEmpty();
        boolean emptyRent = rentField.getText().trim().isEmpty();
        boolean emptyUtilities = utilitiesField.getText().trim().isEmpty();
        return emptyAddress || emptyTenant || emptyBeds || emptyBaths || emptyCars || emptyRent || emptyUtilities;
    }

    @FXML
    protected void handleSettingsClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void handleSignOutClick() throws IOException {
        Owner currentOwner = getCurrentOwner();
        currentOwner.setSignedIn(false);
        ownerDAO.updateOwner(currentOwner);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void handleEditClick() throws IOException {
        // Load the EditProperty.fxml when the edit button is clicked
        Stage stage = (Stage) editButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void handleAddPropertyClick() throws IOException {
        Stage stage = (Stage) addPropertyButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    private Owner getCurrentOwner() {
        return ownerDAO.getOwner(true);
    }

    @FXML
    protected void handleViewStatsClick() throws IOException {
        // Correctly specify the location of the FXML file for the stats page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("charts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) viewStatsButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleSearchClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Search page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handleNotificationsClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Notifications page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handleSettingsTopClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The settings page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handleHelpClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Help page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handlePlusClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Add Property page is not yet implemented.");
        alert.showAndWait();
    }
}
