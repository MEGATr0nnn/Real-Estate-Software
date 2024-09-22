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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

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
    private OwnerDAO ownerDAO;
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
        Owner currentOwner = ownerDAO.getOwner(true);
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
}
