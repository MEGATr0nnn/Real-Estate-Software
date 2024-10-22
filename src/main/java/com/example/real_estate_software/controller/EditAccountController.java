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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EditAccountController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button backButton;
    @FXML
    private Button deleteButton;

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


    public EditAccountController() {
        ownerDAO = new OwnerDAO();
    }

    @FXML
    public void initialize() {
        displayOwner();
    }

    @FXML
    protected void onEditClick() {
        Owner currentOwner = ownerDAO.getAllBool(true);
        if(!emptyFields()) {
            currentOwner.setFirstName(firstNameField.getText());
            currentOwner.setLastName(lastNameField.getText());
            currentOwner.setEmail(emailField.getText());
            currentOwner.setPassword(passwordField.getText());
            ownerDAO.update(currentOwner);
        }
        firstNameField.setText(currentOwner.getFirstName());
        lastNameField.setText(currentOwner.getLastName());
        emailField.setText(currentOwner.getEmail());
        passwordField.setText(currentOwner.getPassword());
    }

    @FXML
    protected void onDeleteClick() throws IOException {
        Owner currentOwner = ownerDAO.getAllBool(true);
        ownerDAO.delete(currentOwner);
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void onBackClick () throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
        stage.setScene(scene);
    }

    private void displayOwner() {
        Owner currentOwner = ownerDAO.getAllBool(true);
        firstNameField.setText(currentOwner.getFirstName());
        lastNameField.setText(currentOwner.getLastName());
        emailField.setText(currentOwner.getEmail());
        passwordField.setText(currentOwner.getPassword());
    }

    private boolean emptyFields() {
        boolean emptyFirstName = firstNameField.getText().trim().isEmpty();
        boolean emptyLastName = lastNameField.getText().trim().isEmpty();
        boolean emptyEmail = emailField.getText().trim().isEmpty();
        boolean emptyPassword = passwordField.getText().trim().isEmpty();
        return emptyFirstName || emptyLastName || emptyEmail || emptyPassword;
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
