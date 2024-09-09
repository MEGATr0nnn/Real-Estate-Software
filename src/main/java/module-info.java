module com.example.real_estate_software {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.real_estate_software to javafx.fxml;
    exports com.example.real_estate_software;
    exports com.example.real_estate_software.controller;
    opens com.example.real_estate_software.controller to javafx.fxml;
}