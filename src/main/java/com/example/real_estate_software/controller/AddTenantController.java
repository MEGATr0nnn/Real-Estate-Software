package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Class is used primarily for the user input on adding information and images to the associated tenant profile
 */
public class AddTenantController extends AbstractController {
    @FXML
    private Button backButton;
    @FXML
    private Button addImageButton;
    @FXML
    private Button addTenantButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ImageView imageView;
    @FXML
    private Image image;

    public AddTenantController() {
        super();
    }

    /**
     * Button action to revert back to the Property Dashboard page
     * Upon pressing the button, the user will be directed to the Property Dashboard
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
     * Button action for when the Owner adds a tenant
     * Upon pressing the button, a tenant will be added to the Remove Tenant Page
     * @throws IOException
     */
    @FXML
    protected void onAddTenantClick() throws IOException {
        if(!emptyFields()) {
            addTenant();
            reloadPage();
        }
    }

    /**
     * Button action to add an image associated with the tenant
     */
    @FXML
    protected void onAddImageClick() {
        Stage stage = (Stage) addImageButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Image");
        File filePath = fileChooser.showOpenDialog(stage);
        if(filePath != null) {
            image = new Image(filePath.toURI().toString());
            imageView.setImage(image);
        }
    }

    /**
     * Used to add tenant to the database
     */
    private void addTenant() {
        Property selectedProperty = getPropertyDAO().get_Property(true);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        getTenantDAO().insertNew(new Tenant(firstName, lastName, email, phoneNumber), selectedProperty);
        selectedProperty.setNum_Tenants(selectedProperty.getNum_Tenants() + 1);
        getPropertyDAO().update_Property(selectedProperty);
    }

    /**
     * Used to check if any text fields are empty
     * @return boolean
     */
    private boolean emptyFields() {
        boolean emptyFirstName = firstNameField.getText().trim().isEmpty();
        boolean emptyLastName = lastNameField.getText().trim().isEmpty();
        boolean emptyEmail = emailField.getText().trim().isEmpty();
        boolean emptyPhoneNumber = phoneNumberField.getText().trim().isEmpty();
        return emptyFirstName || emptyLastName || emptyEmail || emptyPhoneNumber;
    }

    /**
     * Used to reload the Add Tenant Page and clear fields
     * @throws IOException
     */
    private void reloadPage() throws IOException{
        Stage stage = (Stage) addTenantButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddTenant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
