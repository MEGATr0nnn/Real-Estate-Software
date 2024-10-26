package com.example.real_estate_software.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import com.example.real_estate_software.model.Tenant;

public class RenterTileController {
    @FXML
    private Label tenantName;
    @FXML
    private Image tenantPicture;

    public void setRenterData(Tenant tenant) {
    tenantName.setText(tenant.getFirstName()+ tenant.getLastName());
    }
}

