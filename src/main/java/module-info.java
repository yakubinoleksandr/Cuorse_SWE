module com.yakubin.hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.yakubin.hospital to javafx.fxml;
    exports com.yakubin.hospital;
    exports com.yakubin.hospital.service;
}