package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Rent Controller Class is for the functionality of managing the rent for an associated property
 * Class is used to locate all associated tenants with the property and assign
 * the rent and set the frequency of rent to be paid by the tenant.
 */
public class RentController {
    public Button backButton;

    public void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    //Create a function which sets the frequency of rent
}
