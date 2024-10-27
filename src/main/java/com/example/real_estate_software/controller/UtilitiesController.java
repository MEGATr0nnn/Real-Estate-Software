package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Class that is used for Utilities Page [WORK-IN-PROGRESS]
 */
public class UtilitiesController extends AbstractController {
    @FXML
    private Button backButton;
    @FXML
    private Button addWater;
    @FXML
    private TextField waterTextField;
    @FXML
    private Button addElectricity;
    @FXML
    private TextField electricTextField;
    @FXML
    private Button addGas;
    @FXML
    private TextField gasTextField;


    public UtilitiesController() {
        super();
    }

    @FXML
    public void initialize() {
        displayUtil();
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

    public void onWaterClick() {
        if(!waterTextField.getText().trim().isEmpty()) {
            editUtil();
        }
    }

    public void onElectricityClick() {
        if(!electricTextField.getText().trim().isEmpty()) {
            editUtil();
        }
    }

    public void onGasClick() {
        if(!gasTextField.getText().trim().isEmpty()) {
            editUtil();
        }
    }

    /**
     * Used to edit Utilities information for the database
     */
    private void editUtil() {
        Utilities currentUtil = getUtilitiesDAO().getUtilities(getPropertyDAO().get_Property(true));
        currentUtil.setWaterUtilities(Integer.parseInt(waterTextField.getText()));
        currentUtil.setElectricityUtilities(Integer.parseInt(electricTextField.getText()));
        currentUtil.setGasUtilities(Integer.parseInt(gasTextField.getText()));
        getUtilitiesDAO().updateUtilities(currentUtil, getPropertyDAO().get_Property(true));
    }

    /**
     * Used to display Utilities information on screen
     */
    private void displayUtil() {
        Utilities currentUtil = getUtilitiesDAO().getUtilities(getPropertyDAO().get_Property(true));
        waterTextField.setText(String.valueOf(currentUtil.getWaterUtilities()));
        electricTextField.setText(String.valueOf(currentUtil.getElectricityUtilities()));
        gasTextField.setText(String.valueOf(currentUtil.getGasUtilities()));
    }

}
