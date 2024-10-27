package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class that is used for the Property Maintenance Page [WORK-IN-PROGRESS]
 */
public class PropertyMaintenanceController extends AbstractController {
    @FXML
    private Button backButton;
    @FXML
    private Button addFileButton;

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

    /**
     * Button action to add an image associated with the tenant
     */
    @FXML
    protected void onAddFileClick() throws IOException {
        Stage stage = (Stage) addFileButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF and Word Documents", "*.pdf", "*.docx"));

        File filePath = fileChooser.showOpenDialog(stage);

        if (filePath != null){
                Path maintenanceFormsFolder = Paths.get("src/main/resources/com/example/real_estate_software/Maintenance Files");

                if (!Files.exists(maintenanceFormsFolder)) {
                    Files.createDirectories(maintenanceFormsFolder);
                }
                Path targetPath = maintenanceFormsFolder.resolve(filePath.getName());

                Files.copy(filePath.toPath(), targetPath);
        }
    }
}
