package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class UtilitiesController {

    public Button backButton;
    public Button addElectricity;
    public Button addGas;
    public Button addWater;

    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void onGasClick(ActionEvent actionEvent) {
        //Add functionality here for when the GAS button is pressed, the data gets saved and uploaded to DB
    }

    public void onWaterClick(ActionEvent actionEvent) {
        //Add functionality here for when the WATER button is pressed, the data gets saved and uploaded to DB

    }

    public void onElectricityClick(ActionEvent actionEvent) {
        //Add functionality here for when the ELECTRICITY button is pressed, the data gets saved and uploaded to DB

    }
}
