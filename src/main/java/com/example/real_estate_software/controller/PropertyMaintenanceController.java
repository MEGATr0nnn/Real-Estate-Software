package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Class that is used for the Property Maintenance Page [WORK-IN-PROGRESS]
 */
public class PropertyMaintenanceController extends AbstractController {
    @FXML
    private Button backButton;

    public PropertyMaintenanceController() {
        super();
    }

    public void onAddFiles() {
        //NEED TO IMPLEMENT FOR FILE UPLOAD
    }

    /**
     * Button action that directs the user back to the Property Dashboard
     * @throws IOException
     */
    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
