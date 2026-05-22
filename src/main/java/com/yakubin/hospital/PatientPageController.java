package com.yakubin.hospital;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PatientPageController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_patientID;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private Button login_loginBtn;

    @FXML
    private ComboBox<?> login_user;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void loginAccount() {

        String patientID = login_patientID.getText();

        String password = login_password.isVisible()
                ? login_password.getText()
                : login_showPassword.getText();

        if (patientID.isEmpty() || password.isEmpty()) {

            alert.errorMessage("Incorrect Patient ID/Password");
            return;
        }

        String checkStatus =
                "SELECT status FROM patient "
                        + "WHERE patient_id = ? "
                        + "AND password = ? "
                        + "AND status = 'Confirm' "
                        + "AND date_delete IS NULL";

        String sql =
                "SELECT * FROM patient "
                        + "WHERE patient_id = ? "
                        + "AND password = ? "
                        + "AND date_delete IS NULL";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(checkStatus);

            prepare.setLong(1,
                    Long.parseLong(patientID));

            prepare.setString(2, password);

            result = prepare.executeQuery();

            if (result.next()) {

                alert.errorMessage(
                        "Need the confirmation of the Admin!");

                return;
            }

            prepare = connect.prepareStatement(sql);

            prepare.setLong(1,
                    Long.parseLong(patientID));

            prepare.setString(2, password);

            result = prepare.executeQuery();

            if (result.next()) {

                Data.patient_id =
                        Integer.parseInt(patientID);

                alert.successMessage("Login Successfully!");

                Parent root = FXMLLoader.load(getClass().getResource("PatientMainForm.fxml"));

                Stage stage = new Stage();

                stage.setTitle(
                        "Hospital Management System");

                stage.setScene(new Scene(root));

                stage.show();

                login_loginBtn
                        .getScene()
                        .getWindow()
                        .hide();

            } else {

                alert.errorMessage(
                        "Incorrect Patient ID/Password");
            }

        } catch (NumberFormatException e) {

            alert.errorMessage(
                    "Patient ID must contain only numbers");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    void loginShowPassword() {

        boolean show = login_checkBox.isSelected();

        login_showPassword.setVisible(show);
        login_showPassword.setManaged(show);

        login_password.setVisible(!show);
        login_password.setManaged(!show);
    }

    public void userList() {

        List<String> listU = new ArrayList<>();

        for (String data : Users.user) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    @FXML
    void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Doctor Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Patient Portal") {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("PatientPage.fxml"));
                Stage stage = new Stage();

                stage.setTitle("Hospital Management System");

                stage.setMinWidth(340);
                stage.setMinHeight(580);

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        login_user.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login_showPassword.textProperty().bindBidirectional(login_password.textProperty());

        login_showPassword.setVisible(false);
        login_showPassword.setManaged(false);

        userList();
    }

}
