package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class AddPropertyController extends AbstractController {
    @FXML
    private TextField addressField;
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
    private Button backButton;

    public AddPropertyController() {
        super();
    }

    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAddPropertyClick() throws IOException {
        if(!emptyFields()) {
            addProperty();
            clearFields();
        }
    }

    private void addProperty() {
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        String address = addressField.getText();
        int beds = Integer.parseInt(bedsField.getText());
        int baths = Integer.parseInt(bathsField.getText());
        int cars = Integer.parseInt(carsField.getText());
        int rent = Integer.parseInt(rentField.getText());
        int utilities = Integer.parseInt(utilitiesField.getText());
        getPropertyDAO().insert_New_Property(new Property(address, beds, baths, cars, rent, utilities), currentOwner);
    }

    private void clearFields() {
        addressField.clear();
        bedsField.clear();
        bathsField.clear();
        carsField.clear();
        rentField.clear();
        utilitiesField.clear();
    }

    private boolean emptyFields() {
        boolean emptyAddress = addressField.getText().trim().isEmpty();
        boolean emptyBeds = bedsField.getText().trim().isEmpty();
        boolean emptyBaths = bathsField.getText().trim().isEmpty();
        boolean emptyCars = carsField.getText().trim().isEmpty();
        boolean emptyRent = rentField.getText().trim().isEmpty();
        boolean emptyUtilities = utilitiesField.getText().trim().isEmpty();
        return emptyAddress || emptyBeds || emptyBaths || emptyCars || emptyRent || emptyUtilities;
    }

}
