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

/**
 * Abstract class that contains navigation methods that are used by other controllers
 */
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

    /**
     * Button action for when the Owner wants to go to the Main Dashboard
     * Upon pressing the button, the user will be directed to the Main Dashboard
     * @throws IOException
     */
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

    /**
     * Button action for when the Owner wants to go to the Edit Property Page
     * Upon pressing the button, the user will be directed to the Edit Property Page
     * @throws IOException
     */
    @FXML
    protected void handleEditClick() throws IOException {
        deselectProperty();
        Stage stage = (Stage) editButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button action for when the Owner wants to go to the Edit Account Page
     * Upon pressing the button, the user will be directed to the Edit Account Page
     * @throws IOException
     */
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

    /**
     * Button action for when the Owner wants to go to the Property Charts Page
     * Upon pressing the button, the user will be directed to the Property Charts Page
     * @throws IOException
     */
    @FXML
    protected void handleViewStatsClick() throws IOException {
        deselectProperty();
        Stage stage = (Stage) viewStatsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Charts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button action for when the Owner wants to go to Sign Out of the application
     * Upon pressing the button, the user will be signed out and directed to the Sign-In Page
     * @throws IOException
     */
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

    /**
     * Makes sure that property is not selected when leaving Property Dashboard
     */
    public void deselectProperty() {
        Property selectedProperty = propertyDAO.get_Property(true);
        if(selectedProperty != null) {
            selectedProperty.setIs_Selected(false);
            propertyDAO.update_Property(selectedProperty);
        }
    }

    /**
     * Makes sure Owner is still not signed in when returning to Authentication Page
     */
    public void signOutOwner() {
        Owner currentOwner = ownerDAO.getAllBool(true);
        if(currentOwner != null) {
            currentOwner.setSignedIn(false);
            ownerDAO.update(currentOwner);
        }
    }

}
