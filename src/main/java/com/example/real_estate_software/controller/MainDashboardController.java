package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MainDashboardController extends AbstractController {
    @FXML
    private Button addPropertyButton;
    @FXML
    private GridPane propertyGrid;

    public MainDashboardController() {
        super();
    }

    @FXML
    public void initialize() {
        Owner currentOwner = getOwnerDAO().getAllBool(true);
        loadProperties(currentOwner);
    }

    @FXML
    protected void handleAddPropertyClick() throws IOException {
        Stage stage = (Stage) addPropertyButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddProperty.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     loads properties of current owner
     **/
    //make sure this only gets current owner properties
    private void loadProperties(Owner owner) {
        List<Property> properties = getPropertyDAO().get_OwnerProperties(owner);
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

        innerPane.getChildren().addAll(iconCircle);

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
        getPropertyDAO().update_Property(property);

        Stage stage = (Stage) propertyGrid.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/real_estate_software/PropertyDashboard.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
