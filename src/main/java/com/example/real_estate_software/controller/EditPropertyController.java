package com.example.real_estate_software.controller;

import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.Property;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.util.List;

/**
 * CLass that is used for the Edit Property Page
 */
public class EditPropertyController extends AbstractController {
    @FXML
    private ListView<Property> propertyListView;
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

    public EditPropertyController() {
        super();
    }

    @FXML
    public void initialize() {
        propertyListView.setCellFactory(this::renderCell);
        displayProperties();
    }

    /**
     * Button action that edits selected property information for the database
     */
    @FXML
    protected void onEditClick() {
        Property selectedProperty = propertyListView.getSelectionModel().getSelectedItem();
        if(!emptyFields()) {
            selectedProperty.setAddress(addressField.getText());
            selectedProperty.setNum_Beds(Integer.parseInt(bedsField.getText()));
            selectedProperty.setNum_Baths(Integer.parseInt(bathsField.getText()));
            selectedProperty.setNum_Cars(Integer.parseInt(carsField.getText()));
            selectedProperty.setRent(Integer.parseInt(rentField.getText()));
            selectedProperty.setHas_Tenants(selectedProperty.checkHas_Tenants());
            getPropertyDAO().update_Property(selectedProperty);
            displayProperties();
            clearFields();
        }
    }

    /**
     * Button action that deletes selected property from the databse
     */
    @FXML
    protected void onDeleteClick() {
        Property selectedProperty = propertyListView.getSelectionModel().getSelectedItem();
        if(selectedProperty != null) {
            getPropertyDAO().delete_Property(selectedProperty);
            displayProperties();
            clearFields();
        }
    }

    /**
     * Used to render each owner property as its on clickable cell
     * @param propertyListVIew
     * @return ListCell<Property>
     */
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

    /**
     * Used to display all owner properties in the list window
     */
    private void displayProperties() {
        propertyListView.getItems().clear();
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        List<Property> properties = getPropertyDAO().get_OwnerProperties(currentOwner);
        propertyListView.getItems().addAll(properties);
    }

    /**
     * Used to display selected property information in text fields
     * @param property
     */
    private void selectProperty(Property property) {
        propertyListView.getSelectionModel().select(property);
        addressField.setText(property.getAddress());
        bedsField.setText(String.valueOf(property.getNum_Beds()));
        bathsField.setText(String.valueOf(property.getNum_Baths()));
        carsField.setText(String.valueOf(property.getNum_Cars()));
        rentField.setText(String.valueOf(property.getRent()));
    }

    /**
     * Used to clear text fields
     */
    private void clearFields() {
        addressField.clear();
        bedsField.clear();
        bathsField.clear();
        carsField.clear();
        rentField.clear();
    }

    /**
     * Used to check if any text fields are empty
     * @return boolean
     */
    private boolean emptyFields() {
        boolean emptyAddress = addressField.getText().trim().isEmpty();
        boolean emptyBeds = bedsField.getText().trim().isEmpty();
        boolean emptyBaths = bathsField.getText().trim().isEmpty();
        boolean emptyCars = carsField.getText().trim().isEmpty();
        boolean emptyRent = rentField.getText().trim().isEmpty();
        return emptyAddress || emptyBeds || emptyBaths || emptyCars || emptyRent;
    }

}
