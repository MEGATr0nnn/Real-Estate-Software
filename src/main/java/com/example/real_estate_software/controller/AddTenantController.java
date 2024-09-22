package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * The Tenant Controller Class is for the functionality of adding a Tenant to the associated Property
 * Class is used primarily for the user input on adding information and images to the associated tenant profile
 */
public class AddTenantController {
    public Button backButton;
    public Button addImageButton;
    public Button saveButton;


    /**
     * Button action to revert back to the Property Dashboard page
     */
    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button action to add an image associated with the tenant (Work in progress)
     */
    @FXML
    protected void onAddImageClick() throws IOException {
        Stage stage = (Stage) addImageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    //Create button functionality for saving

}
