package com.example.real_estate_software.controller;

import com.example.real_estate_software.model.Owner;
import com.example.real_estate_software.model.OwnerDAO;
import com.example.real_estate_software.model.Property;
import com.example.real_estate_software.model.PropertyDAO;
import javafx.fxml.FXML;


import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.List;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.example.real_estate_software.HelloApplication; // Ensure this import is correct

import java.io.IOException;
import java.util.Objects;

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

    public ChartController() {
        propertyDAO = new PropertyDAO();
        ownerDAO = new OwnerDAO();
    }

    @FXML
    public void initialize() {
        Owner currentOwner = ownerDAO.getAllBool(true);
        loadBarChartData(currentOwner);
        loadPieChartData(currentOwner);
    }

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
            int totalRent = property.getRent() * property.getNum_Tenants();

            // Add data to rent and tenant series
            rentSeries.getData().add(new XYChart.Data<>(property.getAddress(), totalRent));
            tenantSeries.getData().add(new XYChart.Data<>(property.getAddress(), property.getNum_Tenants()));
        }

        // Add the series to the bar charts
        propertyBarChart.getData().add(rentSeries);
        tenantBarChart.getData().add(tenantSeries);
    }

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
    @FXML
    private void handleBackButtonClick() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainDashboard.fxml"));
            Parent dashboardView = fxmlLoader.load();


            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(dashboardView);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/real_estate_software/dashboard.css")).toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
