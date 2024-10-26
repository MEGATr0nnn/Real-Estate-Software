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

/**
 * Class that is used for the Edit Account Page
 */
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
    private Button deleteAccountButton;

    public EditAccountController() {
        super();
    }

    @FXML
    public void initialize() {
        displayOwner();
    }

    /**
     * Button action that edits the Owner information in the database
     */
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

    /**
     * Button action that deletes the Owner from the database
     * Upon pressing the button, Owner wil be deleted and returned to the Sign-In Page
     * @throws IOException
     */
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

    /**
     * Used to display Owner information on screen
     */
    private void displayOwner() {
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        firstNameField.setText(currentOwner.getFirstName());
        lastNameField.setText(currentOwner.getLastName());
        emailField.setText(currentOwner.getEmail());
        passwordField.setText(currentOwner.getPassword());
    }

    /**
     * Used to check if any text fields are empty
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
