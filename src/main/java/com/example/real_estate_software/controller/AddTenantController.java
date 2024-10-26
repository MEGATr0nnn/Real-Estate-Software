package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * The Tenant AbstractController Class is for the functionality of adding a Tenant to the associated Property
 * Class is used primarily for the user input on adding information and images to the associated tenant profile
 */
public class AddTenantController extends AbstractController {
    @FXML
    private Button backButton;
    @FXML
    private Button addImageButton;
    @FXML
    private Button addTenantButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;

    public AddTenantController() {
        super();
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
        stage.show();
    }

    @FXML
    protected void onAddTenantClick() throws IOException {
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
        Property selectedProperty = getPropertyDAO().get_Property(true);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        getTenantDAO().insertNew(new Tenant(firstName, lastName, email, phoneNumber), selectedProperty);
        selectedProperty.setNum_Tenants(selectedProperty.getNum_Tenants() + 1);
        getPropertyDAO().update_Property(selectedProperty);
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

}
