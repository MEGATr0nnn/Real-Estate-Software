package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class EditAccountController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button backButton;
    private OwnerDAO ownerDAO;

    public EditAccountController() {
        ownerDAO = new OwnerDAO();
    }

    @FXML
    protected void onEditClick() {
        Owner currentOwner = ownerDAO.getOwner(true);
        if(!emptyFields()) {
            currentOwner.setFirstName(firstNameField.getText());
            currentOwner.setLastName(lastNameField.getText());
            currentOwner.setEmail(emailField.getText());
            currentOwner.setPassword(passwordField.getText());
            ownerDAO.updateOwner(currentOwner);
        }
        firstNameField.setText(currentOwner.getFirstName());
        lastNameField.setText(currentOwner.getLastName());
        emailField.setText(currentOwner.getEmail());
        passwordField.setText(currentOwner.getPassword());
    }

    @FXML
    protected void onBackClick () throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dash-view-final.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    private boolean emptyFields() {
        boolean emptyFirstName = firstNameField.getText().trim().isEmpty();
        boolean emptyLastName = lastNameField.getText().trim().isEmpty();
        boolean emptyEmail = emailField.getText().trim().isEmpty();
        boolean emptyPassword = passwordField.getText().trim().isEmpty();
        return emptyFirstName || emptyLastName || emptyEmail || emptyPassword;
    }

}
