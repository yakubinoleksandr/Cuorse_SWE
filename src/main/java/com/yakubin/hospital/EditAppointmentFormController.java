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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditAppointmentFormController implements Initializable{

    @FXML
    private TextField editApp_appointmentID;

    @FXML
    private TextField editApp_fullName;

    @FXML
    private ComboBox<String> editApp_gender;

    @FXML
    private TextField editApp_mobileNumber;

    @FXML
    private TextArea editApp_address;

    @FXML
    private Button editApp_updateBtn;

    @FXML
    private Button editApp_cancelBtn;

    @FXML
    private TextArea editApp_description;

    @FXML
    private TextField editApp_diagnosis;

    @FXML
    private TextField editApp_treatment;

    @FXML
    private ComboBox<String> editApp_doctor;

    @FXML
    private ComboBox<String> editApp_specialized;

    @FXML
    private ComboBox<String> editApp_status;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    public void displayFields(){
        editApp_appointmentID.setText(Data.temp_appID);
        editApp_fullName.setText(Data.temp_appName);
        editApp_gender.getSelectionModel().select(Data.temp_appGender);
        editApp_mobileNumber.setText(Data.temp_appMobileNumber);
        editApp_address.setText(Data.temp_appAddress);
        editApp_description.setText(Data.temp_appDescription);
        editApp_diagnosis.setText(Data.temp_appDiagnosis);
        editApp_treatment.setText(Data.temp_appTreatment);
        editApp_doctor.getSelectionModel().select(Data.temp_appDoctor);
        editApp_specialized.getSelectionModel().select(Data.temp_appSpecialized);
        editApp_status.getSelectionModel().select(Data.temp_appStatus);
    }

    public void doctorList(){
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";

        connect = Database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while(result.next()){
                listData.add(result.getString("doctor_id"));
            }

            editApp_doctor.setItems(listData);
            specializedList();
        }catch(Exception e){e.printStackTrace();}
    }

    @FXML
    public void specializedList() {

        if (editApp_doctor.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        String sql = "SELECT specialized FROM doctor WHERE delete_date IS NULL AND doctor_id = ?";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, editApp_doctor.getSelectionModel().getSelectedItem());

            result = prepare.executeQuery();

            ObservableList<String> listData = FXCollections.observableArrayList();

            if (result.next()) {
                listData.add(result.getString("specialized"));
            }

            editApp_specialized.setItems(listData);

            if (!listData.isEmpty()) {
                editApp_specialized.getSelectionModel().selectFirst();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editApp_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editApp_status.setItems(listData);
    }

    @FXML
    public void updateBtn() {

        if (editApp_appointmentID.getText().isEmpty()
                || editApp_fullName.getText().isEmpty()
                || editApp_gender.getSelectionModel().getSelectedItem() == null
                || editApp_mobileNumber.getText().isEmpty()
                || editApp_address.getText().isEmpty()
                || editApp_description.getText().isEmpty()
                || editApp_doctor.getSelectionModel().getSelectedItem() == null
                || editApp_specialized.getSelectionModel().getSelectedItem() == null
                || editApp_status.getSelectionModel().getSelectedItem() == null) {

            alert.errorMessage("Please fill all blank fields");
            return;
        }

        String updateData =
                "UPDATE appointment SET "
                        + "name = ?, "
                        + "gender = ?, "
                        + "mobile_number = ?, "
                        + "address = ?, "
                        + "description = ?, "
                        + "diagnosis = ?, "
                        + "treatment = ?, "
                        + "doctor = ?, "
                        + "specialized = ?, "
                        + "status = ?, "
                        + "date_modify = ? "
                        + "WHERE appointment_id = ?";

        connect = Database.connectDB();

        try {
            if (!alert.confirmationMessage("Are you sure you want to update Appointment ID: "
                    + editApp_appointmentID.getText() + "?")) {

                alert.errorMessage("Cancelled.");
                return;
            }

            java.sql.Date sqlDate =
                    new java.sql.Date(System.currentTimeMillis());

            prepare = connect.prepareStatement(updateData);

            prepare.setString(1, editApp_fullName.getText());
            prepare.setString(2, editApp_gender.getSelectionModel().getSelectedItem());
            prepare.setString(3, editApp_mobileNumber.getText());
            prepare.setString(4, editApp_address.getText());
            prepare.setString(5, editApp_description.getText());
            prepare.setString(6, editApp_diagnosis.getText());
            prepare.setString(7, editApp_treatment.getText());
            prepare.setString(8, editApp_doctor.getSelectionModel().getSelectedItem());
            prepare.setString(9, editApp_specialized.getSelectionModel().getSelectedItem());
            prepare.setString(10, editApp_status.getSelectionModel().getSelectedItem());
            prepare.setDate(11, sqlDate);
            prepare.setInt(12, Integer.parseInt(editApp_appointmentID.getText()));

            prepare.executeUpdate();

            alert.successMessage("Updated Successfully!");

            editApp_updateBtn.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelBtn() {
        editApp_cancelBtn.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderList();
        statusList();
        doctorList();

        displayFields();

        editApp_appointmentID.setEditable(false);
    }

}
