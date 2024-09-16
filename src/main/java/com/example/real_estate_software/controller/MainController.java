package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private Button logOutButton;
    private OwnerDAO ownerDAO;

    public MainController() {
        ownerDAO = new OwnerDAO();
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
}
