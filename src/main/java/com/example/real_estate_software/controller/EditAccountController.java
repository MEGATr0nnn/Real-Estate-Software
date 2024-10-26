package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class EditAccountController extends AbstractController {
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
    @FXML
    private Button deleteAccountButton;

    public EditAccountController() {
        super();
    }

    @FXML
    public void initialize() {
        displayOwner();
    }

    @FXML
    protected void onEditClick() {
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        if (!emptyFields()) {
            currentOwner.setFirstName(firstNameField.getText());
            currentOwner.setLastName(lastNameField.getText());
            currentOwner.setEmail(emailField.getText());
            currentOwner.setPassword(passwordField.getText());
            getOwnerDAO().update(currentOwner);
        }
        firstNameField.setText(currentOwner.getFirstName());
        lastNameField.setText(currentOwner.getLastName());
        emailField.setText(currentOwner.getEmail());
        passwordField.setText(currentOwner.getPassword());
    }

    @FXML
    protected void onDeleteClick() throws IOException {
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        getOwnerDAO().delete(currentOwner);
        Stage stage = (Stage) deleteAccountButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void displayOwner() {
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        firstNameField.setText(currentOwner.getFirstName());
        lastNameField.setText(currentOwner.getLastName());
        emailField.setText(currentOwner.getEmail());
        passwordField.setText(currentOwner.getPassword());
    }

    private boolean emptyFields() {
        boolean emptyFirstName = firstNameField.getText().trim().isEmpty();
        boolean emptyLastName = lastNameField.getText().trim().isEmpty();
        boolean emptyEmail = emailField.getText().trim().isEmpty();
        boolean emptyPassword = passwordField.getText().trim().isEmpty();
        return emptyFirstName || emptyLastName || emptyEmail || emptyPassword;
    }

}
