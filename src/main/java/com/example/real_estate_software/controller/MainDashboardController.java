package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import com.example.real_estate_software.model.Property;
import com.example.real_estate_software.model.PropertyDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.util.List;

public class MainDashboardController {
    @FXML
    private AnchorPane propertyContainer;
    public Button propertyDashboard;
    @FXML
    private Button cogsButton;
    @FXML
    private Button barChartButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button signOutButton;
    @FXML
    private Button addPropertyButton;
    @FXML
    private GridPane propertyGrid;
    @FXML
    private Button editButton;
    @FXML
    private Button viewStatsButton;
    private final OwnerDAO ownerDAO;
    private final PropertyDAO propertyDAO;

    public MainDashboardController() {
        ownerDAO = new OwnerDAO();
        propertyDAO = new PropertyDAO();
    }

    @FXML
    public void initialize() {
        propertyDAO.create_Table_Property();
        Owner currentOwner = getCurrentOwner();
        loadProperties(currentOwner);
    }

    /**
     loads properties of current owner
     **/
    //make sure this only gets current owner properties
    private void loadProperties(Owner owner) {
        List<Property> properties = propertyDAO.get_OwnerProperties(owner);
        int column = 0;
        int row = 0;
        for(Property property : properties) {
            AnchorPane propertyBox = createPropertyBox(property);

            propertyGrid.add(propertyBox, column, row);

            column++;

            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }


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

        DropShadow innerShadow = new DropShadow();
        innerShadow.setColor(Color.rgb(215, 209, 209));
        innerPane.setEffect(innerShadow);

        Circle iconCircle = new Circle(47.0, 44.0, 22.0);
        iconCircle.setFill(Color.DODGERBLUE);
        iconCircle.setStroke(Color.BLACK);
        iconCircle.setStrokeType(StrokeType.INSIDE);

        Label propertyIdLabel = new Label("Property ID: " + property.getId());
        propertyIdLabel.setLayoutX(123);
        propertyIdLabel.setLayoutY(19);
        propertyIdLabel.getStyleClass().add("propertyID-label");
        propertyIdLabel.setTextFill(Color.web("#8a8a8a"));

        Label roomsLabel = new Label("No. rooms: " + property.getNum_Beds());
        roomsLabel.setLayoutX(10);
        roomsLabel.setLayoutY(65);
        roomsLabel.getStyleClass().add("no-rooms-label");

        Label addressLabel = new Label(property.getAddress());
        addressLabel.setLayoutX(10);
        addressLabel.setLayoutY(100);
        addressLabel.getStyleClass().add("property-address-label");

        Label tenantsLabel = new Label("Number of tenants: " + property.getNum_Tenants());
        tenantsLabel.setLayoutX(10);
        tenantsLabel.setLayoutY(130);

        Label rentLabel = new Label("Current rent: $" + property.getRent());
        rentLabel.setLayoutX(10);
        rentLabel.setLayoutY(150);
        rentLabel.getStyleClass().add("rent-label");
        rentLabel.setTextFill(Color.GREEN); // Rent text in green

        innerPane.getChildren().addAll(iconCircle, propertyIdLabel);

        box.getChildren().addAll(innerPane, roomsLabel, addressLabel, tenantsLabel, rentLabel);

        // Set click event to open property details page
        box.setOnMouseClicked(event -> {
            try {
                loadPropertyDetailsPage(property);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return box;
    }

    private void loadPropertyDetailsPage(Property property) throws IOException {

        // Used new select column from database
        property.setIs_Selected(true);
        propertyDAO.update_Property(property);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/real_estate_software/PropertyDashboard.fxml"));

        // Load the FXML file and print the type of the loaded root element
        Object root = loader.load();

        if (!(root instanceof AnchorPane)) {
            throw new IllegalStateException("Root element is not of type AnchorPane");
        }

        // Cast to AnchorPane as per your FXML
        AnchorPane propertyPage = (AnchorPane) root;

        // Show the new scene
        Stage stage = (Stage) propertyGrid.getScene().getWindow();
        Scene scene = new Scene(propertyPage);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleSettingsClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void handleSignOutClick() throws IOException {
        Owner currentOwner = getCurrentOwner();
        currentOwner.setSignedIn(false);
        ownerDAO.updateOwner(currentOwner);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void handleEditClick() throws IOException {
        // Load the EditProperty.fxml when the edit button is clicked
        Stage stage = (Stage) editButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EditProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void handleAddPropertyClick() throws IOException {
        Stage stage = (Stage) addPropertyButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    private Owner getCurrentOwner() {
        return ownerDAO.getOwner(true);
    }

    @FXML
    protected void handleViewStatsClick() throws IOException {
        // Correctly specify the location of the FXML file for the stats page
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("charts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Get the current stage and set the new scene
        Stage stage = (Stage) viewStatsButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleSearchClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Search page is not yet implemented.");
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
    protected void handleSettingsTopClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The settings page is not yet implemented.");
        alert.showAndWait();
    }

    @FXML
    protected void handleHelpClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText("The Help page is not yet implemented.");
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
}
