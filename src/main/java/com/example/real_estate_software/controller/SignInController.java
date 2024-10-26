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
import java.util.Objects;

public class SignInController {
    @FXML
    private Button signUpButton;
    @FXML
    private Button signInButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    private final OwnerDAO ownerDAO;

    public SignInController() {
        ownerDAO = new OwnerDAO();
    }

    /**
     * Button action for Owner to sign in with pre-existing account associated with the Application
     * Upon pressing the button, the user will be directed to the Main Dashboard
     */
    @FXML
    protected void onSignInClick() throws IOException {
        if(checkExists()){
            Stage stage = (Stage) signInButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Button action for Owner to create a new account associated with the Application
     * Upon pressing the button, the user will be directed to the Create Account page
     */
    @FXML
    protected void onSignUpClick() throws IOException{
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CreateAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    private boolean checkExists() {
        String email = emailField.getText();
        String password = passwordField.getText();
        List<Owner> owners = ownerDAO.getAll();
        for(Owner owner : owners) {
            if(owner.getEmail().equals(email) && owner.getPassword().equals(password)) {
                owner.setSignedIn(true);
                ownerDAO.update(owner);
                return true;
            }
        }
        return false;
    }

}
