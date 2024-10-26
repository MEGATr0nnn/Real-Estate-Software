package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public abstract class AbstractController {
    @FXML
    private Button homeButton;
    @FXML
    private Button editButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button viewStatsButton;
    @FXML
    private Button signOutButton;
    private OwnerDAO ownerDAO;
    private PropertyDAO propertyDAO;
    private TenantDAO tenantDAO;

    public AbstractController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
        tenantDAO = new TenantDAO();
    }

    @FXML
    protected void handleHomeClick() throws IOException {
        deselectProperty();
        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleEditClick() throws IOException {
        // Load the EditProperty.fxml when the edit button is clicked
        deselectProperty();
        Stage stage = (Stage) editButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleSettingsClick() throws IOException {
        deselectProperty();
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleViewStatsClick() throws IOException {
        // Load the Charts.fxml when the stats button is clicked
        deselectProperty();
        Stage stage = (Stage) viewStatsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Charts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleSignOutClick() throws IOException {
        deselectProperty();
        signOutOwner();
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public OwnerDAO getOwnerDAO() {
        return ownerDAO;
    }

    public PropertyDAO getPropertyDAO() {
        return propertyDAO;
    }

    public TenantDAO getTenantDAO() {
        return tenantDAO;
    }

    public void deselectProperty() {
        Property selectedProperty = propertyDAO.get_Property(true);
        if(selectedProperty != null) {
            selectedProperty.setIs_Selected(false);
            propertyDAO.update_Property(selectedProperty);
        }
    }

    public void signOutOwner() {
        Owner currentOwner = ownerDAO.getAllBool(true);
        if(currentOwner != null) {
            currentOwner.setSignedIn(false);
            ownerDAO.update(currentOwner);
        }
    }

}
