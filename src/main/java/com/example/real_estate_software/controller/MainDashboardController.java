package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import com.example.real_estate_software.model.PropertyDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class MainDashboardController {
    public Button propertyDashboard;
    @FXML
    private Parent dashPage;
    private OwnerDAO ownerDAO;
    private PropertyDAO propertyDAO;

    public MainDashboardController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
    }

    @FXML
    protected void onEditAccountClick() throws IOException {
        Stage stage = (Stage) dashPage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSignOutAccountClick() throws IOException {
        List<Owner> owners = ownerDAO.getAllOwners();
        for(Owner owner : owners) {
            if(owner.getConnection()) {
                owner.setConnection(false);
                ownerDAO.updateOwner(owner);
                Stage stage = (Stage) dashPage.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
                stage.setScene(scene);
            }
        }
    }

    @FXML
    protected void onAddPropertyClick() throws IOException {
        Stage stage = (Stage) dashPage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onEditPropertyClick() throws IOException {
        Stage stage = (Stage) dashPage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onPropertyClick() throws IOException {
        Stage stage = (Stage) propertyDashboard.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}
