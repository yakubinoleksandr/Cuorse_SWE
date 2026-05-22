package com.yakubin.hospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HospitalApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HospitalApplication.class.getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setMinWidth(340);
        stage.setMinHeight(580);

        stage.setTitle("Hospital Management System");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
