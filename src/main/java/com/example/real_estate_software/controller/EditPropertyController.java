package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import com.example.real_estate_software.model.Property;
import com.example.real_estate_software.model.PropertyDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EditPropertyController {
    @FXML
    private ListView<Property> propertyListView;
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
    private Button backButton;



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

    private PropertyDAO propertyDAO;

    public EditPropertyController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
    }

    @FXML
    public void initialize() {
        propertyListView.setCellFactory(this::renderCell);
        displayProperties();
    }

    @FXML
    protected void onEditClick() {
        Property selectedProperty = propertyListView.getSelectionModel().getSelectedItem();
        if(!emptyFields()) {
            selectedProperty.setAddress(addressField.getText());
            selectedProperty.setNum_Tenants(Integer.parseInt(tenantField.getText()));
            selectedProperty.setNum_Beds(Integer.parseInt(bedsField.getText()));
            selectedProperty.setNum_Baths(Integer.parseInt(bathsField.getText()));
            selectedProperty.setNum_Cars(Integer.parseInt(carsField.getText()));
            selectedProperty.setRent(Integer.parseInt(rentField.getText()));
            selectedProperty.setUtilities(Integer.parseInt(utilitiesField.getText()));
            selectedProperty.setHas_Tenants(selectedProperty.checkHas_Tenants());
            propertyDAO.update_Property(selectedProperty);
            displayProperties();
            clearFields();
        }
    }

    @FXML
    protected void onDeleteClick() {
        Property selectedProperty = propertyListView.getSelectionModel().getSelectedItem();
        if(selectedProperty != null) {
            propertyDAO.delete_Property(selectedProperty);
            displayProperties();
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

    private ListCell<Property> renderCell(ListView<Property> propertyListVIew) {
        return new ListCell<>() {

            private void propertySelect(MouseEvent mouseEvent) {
                ListCell<Property> selectedCell = (ListCell<Property>) mouseEvent.getSource();
                Property selectedProperty = selectedCell.getItem();
                if (selectedProperty != null) {
                    selectProperty(selectedProperty);
                }
            }

            @Override
            protected void updateItem(Property property, boolean empty) {
                super.updateItem(property, empty);
                if (empty || property == null || property.getAddress() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::propertySelect);
                } else {
                    setText(property.getAddress());
                }
            }
        };
    }

    private void displayProperties() {
        propertyListView.getItems().clear();
        Owner currentOwner = ownerDAO.getAllBool(true);
        List<Property> properties = propertyDAO.get_OwnerProperties(currentOwner);
        propertyListView.getItems().addAll(properties);
    }

    private void selectProperty(Property property) {
        propertyListView.getSelectionModel().select(property);
        addressField.setText(property.getAddress());
        tenantField.setText(String.valueOf(property.getNum_Tenants()));
        bedsField.setText(String.valueOf(property.getNum_Beds()));
        bathsField.setText(String.valueOf(property.getNum_Baths()));
        carsField.setText(String.valueOf(property.getNum_Cars()));
        rentField.setText(String.valueOf(property.getRent()));
        utilitiesField.setText(String.valueOf(property.getUtilities()));
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
