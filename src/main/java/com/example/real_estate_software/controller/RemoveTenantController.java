package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Class the is used for the Remove Tenant Page
 */
public class RemoveTenantController extends AbstractController {
    @FXML
    private GridPane tenantGrid;
    @FXML
    private Button backButton;

    public RemoveTenantController() {
        super();
    }

    @FXML
    public void initialize() {
        Property selectedProperty = getPropertyDAO().get_Property(true);
        loadTenants(selectedProperty);
    }

    /**
     * Button action that directs the user to the Property Dashboard
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
     * Used to render each properties tenant in its own box
     * @param property
     */
    private void loadTenants(Property property) {
        List<Tenant> tenants = getTenantDAO().getAllType(property);
        int column = 0;
        int row = 0;
        for(Tenant tenant : tenants) {
            AnchorPane tenantBox = createTenantBox(tenant);

            tenantGrid.add(tenantBox, column, row);

            column++;

            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    /**
     * Used to design the box for each tenant
     * @param tenant
     * @return
     */
    private AnchorPane createTenantBox(Tenant tenant) {

        AnchorPane box = new AnchorPane();
        box.setPrefSize(180, 190);
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

        Image tenantImage = new Image(getClass().getResource("/com/example/real_estate_software/images/ProfileIcon.jpg").toExternalForm(), 150, 50, true, true);
        ImageView imageView = new ImageView();
        imageView.setImage(tenantImage);
        imageView.setLayoutX(110);
        imageView.setLayoutY(15);

        Label nameLabel = new Label( tenant.getFirstName() + " " + tenant.getLastName());
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(60);
        nameLabel.getStyleClass().add("name-label");

        Label emailLabel = new Label(tenant.getEmail());
        emailLabel.setLayoutX(10);
        emailLabel.setLayoutY(90);
        emailLabel.getStyleClass().add("email-address-label");

        Label phoneNumberLabel = new Label(tenant.getPhoneNumber());
        phoneNumberLabel.setLayoutX(10);
        phoneNumberLabel.setLayoutY(120);
        phoneNumberLabel.getStyleClass().add("phone-number-label");

        Button removeButton = new Button("Remove");
        removeButton.setLayoutX(65);
        removeButton.setLayoutY(150);
        removeButton.setOnMouseClicked(event -> {
            try {
                removeTenant(tenant);
                reloadPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        innerPane.getChildren().addAll(imageView);
        box.getChildren().addAll(imageView, nameLabel, emailLabel, phoneNumberLabel, removeButton);

        return box;
    }

    /**
     * Used to remove tenants from the database
     * @param tenant
     */
    private void removeTenant(Tenant tenant) {
        getTenantDAO().delete(tenant);
        Property selectedProperty = getPropertyDAO().get_Property(true);
        selectedProperty.setNum_Tenants(selectedProperty.getNum_Tenants() - 1);
        getPropertyDAO().update_Property(selectedProperty);
    }

    /**
     * Button action for each tenant that reloads the Remove Tenant Page
     * @throws IOException
     */
    private void reloadPage() throws IOException{
        Stage stage = (Stage) tenantGrid.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RemoveTenant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/stylesheet.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
