package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


/**
 * The Tenant Controller Class is for the functionality of adding a Tenant to the associated Property
 * Class is used primarily for the user input on adding information and images to the associated tenant profile
 */
public class AddTenantController {
    public Button backButton;
    public Button addImageButton;
    public Button saveButton;

    public ImageView imageView;

    public Label fileSelected;


    /**
     * Button action to revert back to the Property Dashboard page
     */
    @FXML
    protected void onBackClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PropertyDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Button action to add an image associated with the tenant (Work in progress)
     */
    @FXML
    public void onAddImageClick(ActionEvent actionEvent) throws IOException {
        FileChooser chooserFile = new FileChooser();
        chooserFile.setTitle("Add Profile Picture");
        chooserFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        File selectedFile = chooserFile.showOpenDialog(stage);

        if (selectedFile != null) {
                fileSelected.setText("Selected file: " + selectedFile.getName());

                String path = selectedFile.toURI().toURL().toString();
                Image image = new Image(path);
                imageView.setImage(image);

                saveImageToFile(selectedFile);
        }
    }

    private void saveImageToFile(File fileSelected) throws IOException {

        String savedFolder = "src/main/resources/com/example/real_estate_software/images";
        Path savedPath = Path.of(savedFolder, fileSelected.getName());

        Files.copy(fileSelected.toPath(), savedPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public void onSaveClick(ActionEvent actionEvent) {
        //Create button functionality for saving here
    }



}
