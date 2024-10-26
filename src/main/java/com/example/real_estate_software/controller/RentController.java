package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * The Rent AbstractController Class is for the functionality of managing the rent for an associated property
 * Class is used to locate all associated tenants with the property and assign
 * the rent and set the frequency of rent to be paid by the tenant.
 */
public class RentController extends AbstractController {
    @FXML
    private Button backButton;

    public RentController() {
        super();
    }

    public void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
