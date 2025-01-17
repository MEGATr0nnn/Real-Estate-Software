package com.example.real_estate_software.controller;

import com.example.real_estate_software.HelloApplication;
import com.example.real_estate_software.model.*;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Class that is used for Property Charts Page
 */
public class ChartController {
    @FXML
    private BarChart<String, Number> propertyBarChart;
    @FXML
    private BarChart<String, Number> tenantBarChart;
    @FXML
    private PieChart propertyPieChart;
    @FXML
    private Button backButton;
    private PropertyDAO propertyDAO;
    private OwnerDAO ownerDAO;
    private TenantDAO tenantDAO;

    public ChartController() {
        propertyDAO = new PropertyDAO();
        ownerDAO = new OwnerDAO();
        tenantDAO = new TenantDAO();
    }

    @FXML
    public void initialize() {
        Owner currentOwner = ownerDAO.getAllBool(true);
        loadBarChartData(currentOwner);
        loadPieChartData(currentOwner);
    }

    /**
     * Used to create bar charts
     * @param owner
     */
    private void loadBarChartData(Owner owner) {
        List<Property> properties = propertyDAO.get_OwnerProperties(owner);

        // Create a new series for total rent values
        XYChart.Series<String, Number> rentSeries = new XYChart.Series<>();
        rentSeries.setName("Total Rent");

        // Create a series for the number of tenants
        XYChart.Series<String, Number> tenantSeries = new XYChart.Series<>();
        tenantSeries.setName("Number of Tenants");

        for (Property property : properties) {
            // Calculate total rent
            List<Tenant> tenants = tenantDAO.getAllType(property);
            int totalRent = 0;
            for (Tenant tenant : tenants) {
                totalRent += tenant.getRentOwed();
            }

            // Add data to rent and tenant series
            rentSeries.getData().add(new XYChart.Data<>(property.getAddress(), totalRent));
            tenantSeries.getData().add(new XYChart.Data<>(property.getAddress(), tenantDAO.getAllType(property).size()));
        }

        // Add the series to the bar charts
        propertyBarChart.getData().add(rentSeries);
        tenantBarChart.getData().add(tenantSeries);
    }

    /**
     * Used to create pie charts
     * @param owner
     */
    private void loadPieChartData(Owner owner) {
        List<Property> properties = propertyDAO.get_OwnerProperties(owner);

        int tenantedProperties = 0;
        int nonTenantedProperties = 0;

        for (Property property : properties) {
            if (property.getHas_Tenants()) {
                tenantedProperties++;
            } else {
                nonTenantedProperties++;
            }
        }

        PieChart.Data tenantedSlice = new PieChart.Data("Tenanted", tenantedProperties);
        PieChart.Data nonTenantedSlice = new PieChart.Data("Non-Tenanted", nonTenantedProperties);

        propertyPieChart.getData().addAll(tenantedSlice, nonTenantedSlice);
    }

    /**
     * Button action to revert back to the Main Dashboard page
     * Upon pressing the button, the user will be directed to the Main Dashboard
     */
    @FXML
    private void handleBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
