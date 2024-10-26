package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import javafx.scene.layout.HBox;

/**
 * The Tenant Controller Class is for the functionality of adding a Tenant to the associated Property
 * Class is used primarily for the user input on adding information and images to the associated tenant profile
 */
public class AddTenantController {
    public Button backButton;
    public Button addImageButton;
    public Button saveButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
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
    private final OwnerDAO ownerDAO;
    private final PropertyDAO propertyDAO;
    private final TenantDAO tenantDAO;

    public AddTenantController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
        tenantDAO = new TenantDAO();
    }

    /**
     * Button action to revert back to the Property Dashboard page
     */
    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
    }

    @FXML
    protected void onSaveClick() throws IOException {
        if(!emptyFields()) {
            addTenant();
            clearFields();
        }
    }

    /**
     * Button action to add an image associated with the tenant (Work in progress)
     */
    @FXML
    protected void onAddImageClick() throws IOException {

    }

    private void addTenant() {
        Property selectedProperty = propertyDAO.get_Property(true);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        tenantDAO.insertNew(new Tenant(firstName, lastName, email, phoneNumber), selectedProperty);
        selectedProperty.setNum_Tenants(selectedProperty.getNum_Tenants() + 1);
        propertyDAO.update_Property(selectedProperty);
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();
    }

    private boolean emptyFields() {
        boolean emptyFirstName = firstNameField.getText().trim().isEmpty();
        boolean emptyLastName = lastNameField.getText().trim().isEmpty();
        boolean emptyEmail = emailField.getText().trim().isEmpty();
        boolean emptyPhoneNumber = phoneNumberField.getText().trim().isEmpty();
        return emptyFirstName || emptyLastName || emptyEmail || emptyPhoneNumber;
    }

    //Create button functionality for saving
    @FXML
    protected void handleSettingsClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());

        stage.setScene(scene);
    }

    @FXML
    protected void handleSignOutClick() throws IOException {
        Owner currentOwner = getCurrentOwner();
        currentOwner.setSignedIn(false);
        ownerDAO.update(currentOwner);
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
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());

        stage.setScene(scene);
    }

    @FXML
    protected void handleAddPropertyClick() throws IOException {
        Stage stage = (Stage) addPropertyButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());

        stage.setScene(scene);
    }

    private Owner getCurrentOwner() {
        return ownerDAO.getAllBool(true);
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


}
