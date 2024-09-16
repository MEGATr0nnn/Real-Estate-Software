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
import java.util.List;

public class MainController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button logOutButton;
    private OwnerDAO ownerDAO;

    public MainController() {
        ownerDAO = new OwnerDAO();
    }

    @FXML
    protected void onEditClick() {
        Owner currentOwner = ownerDAO.getOwner(true);
        if(!emptyFields()) {
            //Owner currentOwner = ownerDAO.getOwner(true);
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
    protected void onDeleteClick() throws IOException {
        Owner currentOwner = ownerDAO.getOwner(true);
        ownerDAO.deleteOwner(currentOwner);
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onLogOutClick() throws IOException {
        List<Owner> owners = ownerDAO.getAllOwners();
        for(Owner owner : owners) {
            if(owner.getConnection()) {
                owner.setConnection(false);
                ownerDAO.updateOwner(owner);
                Stage stage = (Stage) logOutButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
                stage.setScene(scene);
            }
        }
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
