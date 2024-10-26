package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class UtilitiesController extends AbstractController {
    @FXML
    private Button backButton;
    @FXML
    private Button addElectricity;
    @FXML
    private Button addGas;
    @FXML
    private Button addWater;

    public UtilitiesController() {
        super();
    }

    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
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
