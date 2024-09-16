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

public class CreateAccountController {
    @FXML
    private Button createAccountButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    private OwnerDAO ownerDAO;

    public CreateAccountController() {
        ownerDAO = new OwnerDAO();
    }

    @FXML
    protected void onCreateAccountClick() throws IOException {
        if(!emptyFields()) {
            createAccount();
            Stage stage = (Stage) createAccountButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
    }

    private void createAccount() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        ownerDAO.addOwner(new Owner(firstName, lastName, email, password));
    }

    private boolean emptyFields() {
        boolean emptyFirstName = firstNameField.getText().trim().isEmpty();
        boolean emptyLastName = lastNameField.getText().trim().isEmpty();
        boolean emptyEmail = emailField.getText().trim().isEmpty();
        boolean emptyPassword = passwordField.getText().trim().isEmpty();
        if(emptyFirstName || emptyLastName || emptyEmail || emptyPassword) {
            return true;
        }
        return false;
    }
}
