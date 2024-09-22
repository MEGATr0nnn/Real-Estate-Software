package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import com.example.real_estate_software.model.Property;
import com.example.real_estate_software.model.PropertyDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainDashboardController {
    @FXML
    private GridPane propertyGrid;
    @FXML
    private Button signOutButton;
    @FXML
    private Button addPropertyButton;
    @FXML
    private Button viewStatsButton;
    @FXML
    private Button editButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button plusButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button settingsTopButton;
    @FXML
    private Button notificationsButton;
    @FXML
    private Button searchButton;

    private final OwnerDAO ownerDAO;
    private final PropertyDAO propertyDAO;

    public MainDashboardController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
    }

    @FXML
    public void initialize() {
        // Ensure the properties table exists
        propertyDAO.create_Table_Property();

        // Fetch the current owner
        Owner currentOwner = getCurrentOwner();

        // Insert default properties if none exist
        if (propertyDAO.getAllProperties(currentOwner).isEmpty()) {
            propertyDAO.insertDefaultTestProperties(currentOwner);
        }

        // Load properties into the dashboard
        loadProperties(currentOwner);
    }

    // Load properties into the GridPane
    private void loadProperties(Owner owner) {
        List<Property> properties = propertyDAO.getAllProperties(owner);
        int column = 0;
        int row = 0;
        for (Property property : properties) {
            AnchorPane propertyBox = createPropertyBox(property);

            // Add to the grid (3 properties per row)
            propertyGrid.add(propertyBox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    // Create the individual property box AnchorPane
    private AnchorPane createPropertyBox(Property property) {
        AnchorPane box = new AnchorPane();
        box.setPrefSize(208, 200);
        box.setStyle("-fx-background-color: white;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.ONE_PASS_BOX);
        dropShadow.setColor(Color.rgb(202, 202, 202));
        box.setEffect(dropShadow);

        Pane innerPane = new Pane();
        innerPane.setPrefSize(227, 44);
        innerPane.setStyle("-fx-background-color: white;");
        AnchorPane.setLeftAnchor(innerPane, 0.0);
        AnchorPane.setRightAnchor(innerPane, 0.0);
        AnchorPane.setTopAnchor(innerPane, 0.0);

        Circle iconCircle = new Circle(47.0, 44.0, 22.0);
        iconCircle.setFill(Color.DODGERBLUE);
        iconCircle.setStroke(Color.BLACK);
        iconCircle.setStrokeType(StrokeType.INSIDE);

        Label propertyIdLabel = new Label("Property ID: " + property.getId());
        propertyIdLabel.setLayoutX(123);
        propertyIdLabel.setLayoutY(19);
        propertyIdLabel.setTextFill(Color.web("#8a8a8a"));

        Label roomsLabel = new Label("No. rooms: " + property.getNum_Beds());
        roomsLabel.setLayoutX(10);
        roomsLabel.setLayoutY(65);

        Label addressLabel = new Label(property.getAddress());
        addressLabel.setLayoutX(10);
        addressLabel.setLayoutY(100);

        Label tenantsLabel = new Label("Number of tenants: " + property.getNum_Tenants());
        tenantsLabel.setLayoutX(10);
        tenantsLabel.setLayoutY(130);

        Label rentLabel = new Label("Current rent: $" + property.getRent());
        rentLabel.setLayoutX(10);
        rentLabel.setLayoutY(150);
        rentLabel.setTextFill(Color.GREEN);


        innerPane.getChildren().addAll(iconCircle, propertyIdLabel);

        // Add children to the outer box
        box.getChildren().addAll(innerPane, roomsLabel, addressLabel, tenantsLabel, rentLabel);

        // Set click event to open property details page
        box.setOnMouseClicked(event -> {
            System.out.println("Property box clicked: " + property.getId());
            try {
                loadPropertyDetailsPage(property);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        return box;
    }

    private void loadPropertyDetailsPage(Property property) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/real_estate_software/PropertyDashboard.fxml"));

        // Load the FXML file and print the type of the loaded root element
        Object root = loader.load();
        System.out.println("Root element type: " + root.getClass().getName());

        if (!(root instanceof AnchorPane)) {
            throw new IllegalStateException("Root element is not of type AnchorPane");
        }

        // Cast to AnchorPane as per your FXML
        AnchorPane propertyPage = (AnchorPane) root;

        // Get the PropertyDashboardController
        PropertyDashboardController controller = loader.getController();

        // Pass the selected property to the controller's setProperty() method
        controller.setProperty(property);

        // Show the new scene
        Stage stage = (Stage) propertyGrid.getScene().getWindow();
        Scene scene = new Scene(propertyPage);
        stage.setScene(scene);
        stage.show();
    }






    private Owner getCurrentOwner() {
        return ownerDAO.getOwner(true);
    }

    @FXML
    protected void handleSignOutClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleAddPropertyClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Add Property page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handleSettingsClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The settings page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handlePlusClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Add Property page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handleHelpClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("TheHelp page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handleSettingsTopClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The settings page is not yet implemented.");
        alert.showAndWait();
    }
    @FXML
    protected void handleNotificationsClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Notifications page is not yet implemented.");
        alert.showAndWait();
    }
    @FXML
    protected void handleSearchClick() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Notifications page is not yet implemented.");
        alert.showAndWait();
    }
    @FXML
    protected void handleEditClick() throws IOException {
        // Logic for handling the edit action
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    protected void handleViewStatsClick() throws IOException {
        // Navigate to Stats/Charts page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("charts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) viewStatsButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
