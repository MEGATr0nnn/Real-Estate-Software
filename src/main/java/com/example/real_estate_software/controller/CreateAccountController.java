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
import java.util.Objects;

/**
 * Class used for the Sign-Up Page
 */
public class CreateAccountController {
    @FXML
    private Button createAccountButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    private final OwnerDAO ownerDAO;

    public CreateAccountController() {
        ownerDAO = new OwnerDAO();
    }

    /**
     * Button action for when the user signs in
     * User will then be directed to the Main Dashboard upon successful login
     * @throws IOException
     */
    @FXML
    protected void onCreateAccountClick() throws IOException {
        if(!emptyFields()) {
            createAccount();
            Stage stage = (Stage) createAccountButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Button action to revert back to the Sign-In page
     * Upon pressing the button, the user will be directed to the Sign-In Page
     * @throws IOException
     */
    @FXML
    protected void onBackClick () throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates an Owner account for the database
     */
    private void createAccount() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        ownerDAO.insertNew(new Owner(firstName, lastName, email, password));
    }

    /**
     * Used to check if the text fields are empty
     * @return boolean
     */
    private boolean emptyFields() {
        boolean emptyFirstName = firstNameField.getText().trim().isEmpty();
        boolean emptyLastName = lastNameField.getText().trim().isEmpty();
        boolean emptyEmail = emailField.getText().trim().isEmpty();
        boolean emptyPassword = passwordField.getText().trim().isEmpty();
        return emptyFirstName || emptyLastName || emptyEmail || emptyPassword;
    }
}

