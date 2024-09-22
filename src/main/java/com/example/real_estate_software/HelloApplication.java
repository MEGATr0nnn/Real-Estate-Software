package com.example.real_estate_software;

import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HelloApplication extends Application {
    public static final String TITLE = "Real Estate Software";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private static OwnerDAO ownerDAO;

    @Override
    public void start(Stage stage) throws IOException {
        if(signedIn()) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());

            stage.setTitle(TITLE);
            stage.setScene(scene);
            stage.show();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignIn.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
            stage.setTitle(TITLE);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean signedIn() {
        List<Owner> owners = ownerDAO.getAllOwners();
        for(Owner owner : owners) {
            if(owner.getConnection()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ownerDAO = new OwnerDAO();
        launch();
    }
}