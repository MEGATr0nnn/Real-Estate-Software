package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class DashController {
    @FXML
    private Parent dashPage;
    private OwnerDAO ownerDAO;

    public DashController() {
        ownerDAO = new OwnerDAO();
    }

    @FXML
    protected void onEditClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) dashPage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("account-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void onDeleteClick(ActionEvent event) throws IOException {
        Owner currentOwner = ownerDAO.getOwner(true);
        ownerDAO.deleteOwner(currentOwner);
        Stage stage = (Stage) dashPage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSignOutClick(ActionEvent event) throws IOException {
        List<Owner> owners = ownerDAO.getAllOwners();
        for(Owner owner : owners) {
            if(owner.getConnection()) {
                owner.setConnection(false);
                ownerDAO.updateOwner(owner);
                Stage stage = (Stage) dashPage.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
                stage.setScene(scene);
            }
        }
    }

}
