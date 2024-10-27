package com.example.real_estate_software.controller;

import com.example.real_estate_software.model.Property;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PropertyBoxController {
    @FXML
    private Label propertyAddressLabel;
    @FXML
    private Label numRoomsLabel;
    @FXML
    private Label tenantLabel;
    @FXML
    private Label rentLabel;
    /**
    used to create the property boxes and populate them with the relevant information
     */
    public void setPropertyData(Property property) {
        propertyAddressLabel.setText(property.getAddress());
        numRoomsLabel.setText(String.valueOf(property.getNum_Beds()));
        tenantLabel.setText(String.valueOf(property.getNum_Tenants()));
        rentLabel.setText(String.valueOf(property.getRent()));
    }
}
