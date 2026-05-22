package com.yakubin.hospital;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DoctorMainFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Button logout_btn;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;

    @FXML
    private AreaChart<String, Number> dashboad_chart_PD;

    @FXML
    private BarChart<String, Number> dashboad_chart_DD;

    @FXML
    private TableView<AppointmentData> dashboad_tableView;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_name;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_description;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentDate;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_status;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextField appointment_PatientId;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_confirmBtn;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private Button patients_PI_recordBtn;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<AppointmentData> appointments_tableView;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_name;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_contactNumber;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_status;

    @FXML
    private TextField appointment_appointmentID;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextArea appointment_address;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private ComboBox<String> appointment_time;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private Button appointment_updateBtn;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private Button appointment_deleteBtn;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;

    @FXML
    private ComboBox<String> profile_specialized;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    private final AlertMessage alert = new AlertMessage();

    public void dashbboardDisplayIP() {
        String sql = "SELECT COUNT(id) AS total FROM patient WHERE status = 'Inactive' AND doctor = ? AND date_delete IS NULL";
        connect = Database.connectDB();
        int getIP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            if (result.next()) {
                getIP = result.getInt("total");
            }
            dashboard_IP.setText(String.valueOf(getIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayTP() {
        String sql = "SELECT COUNT(id) AS total FROM patient WHERE doctor = ? AND date_delete IS NULL";
        connect = Database.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            if (result.next()) {
                getTP = result.getInt("total");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayAP() {
        String sql = "SELECT COUNT(id) AS total FROM patient WHERE status = 'Active' AND doctor = ? AND date_delete IS NULL";
        connect = Database.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            if (result.next()) {
                getAP = result.getInt("total");
            }
            dashboard_AP.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayTA() {
        String sql = "SELECT COUNT(id) AS total FROM appointment WHERE status = 'Active' AND doctor = ? AND date_delete IS NULL";
        connect = Database.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            if (result.next()) {
                getTA = result.getInt("total");
            }
            dashboard_tA.setText(String.valueOf(getTA));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<AppointmentData> dashboardAppointmentTableView() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while (result.next()) {
                aData = new AppointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("description"),
                        result.getDate("date"), result.getString("status"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentData> dashboardGetData;

    public void dashboardDisplayData() {
        dashboardGetData = dashboardAppointmentTableView();

        dashboad_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboad_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboad_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetData);
    }

    public void dashboardNOP() {

        dashboad_chart_PD.getData().clear();

        String sql = "SELECT date AS patient_date, COUNT(id) AS total " +
                "FROM patient " +
                "WHERE doctor = ? AND date_delete IS NULL " +
                "GROUP BY date " +
                "ORDER BY date ASC " +
                "LIMIT 8";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString("patient_date"),
                        result.getInt("total")));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardNOA() {

        dashboad_chart_DD.getData().clear();

        String sql = "SELECT date AS appointment_date, COUNT(id) AS total " +
                "FROM appointment " +
                "WHERE doctor = ? AND date_delete IS NULL " +
                "GROUP BY date " +
                "ORDER BY date ASC " +
                "LIMIT 7";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString("appointment_date"),
                        result.getInt("total")));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientConfirmBtn() {

        if (patients_patientID.getText().isEmpty()
                || patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_password.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_password.setText(patients_password.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));

            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());
        }

    }

    public void patientAddBtn() {

        if (patients_PA_patientID.getText().isEmpty()
                || patients_PA_password.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            alert.errorMessage("Something wenr wrong");
        } else {

            connect = Database.connectDB();
            try {
                String doctorName = "";
                String doctorSpecialized = "";

                String getDoctor = "SELECT * FROM doctor WHERE doctor_id = '"
                        + nav_adminID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(getDoctor);

                if (result.next()) {
                    doctorName = result.getString("full_name");
                    doctorSpecialized = result.getString("specialized");
                }
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                        + patients_PA_patientID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientID);
                if (result.next()) {
                    alert.errorMessage(patients_PA_patientID.getText() + " is already exist");
                } else {
                    String insertData = "INSERT INTO patient "
                            + "(patient_id, password, full_name, gender, mobile_number, "
                            + "address, doctor, specialized, date, status) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);

                    prepare.setLong(1, Long.parseLong(patients_PA_patientID.getText()));
                    prepare.setString(2, patients_PA_password.getText());
                    prepare.setString(3, patients_PI_patientName.getText());
                    prepare.setString(4, patients_PI_gender.getText());
                    prepare.setString(5, patients_PI_mobileNumber.getText());
                    prepare.setString(6, patients_PI_address.getText());
                    prepare.setString(7, nav_adminID.getText());
                    prepare.setString(8, doctorSpecialized);
                    prepare.setDate(9, sqlDate);
                    prepare.setString(10, "Confirm");

                    prepare.executeUpdate();

                    alert.successMessage("Added successfully!");
                    patientClearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void patientRecordBtn() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RecordPageForm.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Hospital Management System | Record of Patients");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientClearFields() {
        patients_patientID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_password.clear();
        patients_address.clear();

        patients_PA_patientID.setText("");
        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
    }

    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_gender.setItems(listData);

    }

    public void appointmentInsertBtn() {

        if (appointment_appointmentID.getText().isEmpty()
                || appointment_PatientId.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null
                || appointment_time.getSelectionModel().isEmpty()) {

            alert.errorMessage("Please fill the blank fields");
            return;
        }

        connect = Database.connectDB();

        try {
            int appointmentID = Integer.parseInt(appointment_appointmentID.getText());
            long patientID = Long.parseLong(appointment_PatientId.getText());

            String checkPatient =
                    "SELECT id FROM patient WHERE patient_id = ? AND date_delete IS NULL";

            prepare = connect.prepareStatement(checkPatient);
            prepare.setLong(1, patientID);
            result = prepare.executeQuery();

            if (!result.next()) {
                alert.errorMessage("Patient ID not found in database");
                return;
            }

            LocalDate date = appointment_schedule.getValue();
            LocalTime time = LocalTime.parse(
                    appointment_time.getSelectionModel().getSelectedItem()
            );

            LocalDateTime scheduleDateTime = LocalDateTime.of(date, time);
            Timestamp scheduleTimestamp = Timestamp.valueOf(scheduleDateTime);

            if (scheduleDateTime.isBefore(LocalDateTime.now())) {
                alert.errorMessage("You cannot create an appointment in the past");
                return;
            }

            String checkAppointmentID =
                    "SELECT id FROM appointment "
                            + "WHERE patient_id = ? "
                            + "AND appointment_id = ?";

            prepare = connect.prepareStatement(checkAppointmentID);
            prepare.setLong(1, patientID);
            prepare.setInt(2, appointmentID);
            result = prepare.executeQuery();

            if (result.next()) {
                alert.errorMessage(appointmentID + " was already taken");
                return;
            }

            String checkSchedule =
                    "SELECT id FROM appointment "
                            + "WHERE doctor = ? "
                            + "AND schedule = ? "
                            + "AND date_delete IS NULL";

            prepare = connect.prepareStatement(checkSchedule);
            prepare.setString(1, Data.doctor_id);
            prepare.setTimestamp(2, scheduleTimestamp);
            result = prepare.executeQuery();

            if (result.next()) {
                alert.errorMessage("Appointment time already taken!");
                return;
            }

            String getDoctorData =
                    "SELECT specialized FROM doctor WHERE doctor_id = ?";

            String getSpecialized = "";

            prepare = connect.prepareStatement(getDoctorData);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            if (result.next()) {
                getSpecialized = result.getString("specialized");
            }

            java.sql.Date sqlDate =
                    new java.sql.Date(System.currentTimeMillis());

            String insertData =
                    "INSERT INTO appointment "
                            + "(appointment_id, patient_id, name, gender, description, diagnosis, treatment, "
                            + "mobile_number, address, date, status, doctor, specialized, schedule) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            prepare = connect.prepareStatement(insertData);

            prepare.setInt(1, appointmentID);
            prepare.setLong(2, patientID);
            prepare.setString(3, appointment_name.getText());
            prepare.setString(4, appointment_gender.getSelectionModel().getSelectedItem());
            prepare.setString(5, appointment_description.getText());
            prepare.setString(6, appointment_diagnosis.getText());
            prepare.setString(7, appointment_treatment.getText());
            prepare.setString(8, appointment_mobileNumber.getText());
            prepare.setString(9, appointment_address.getText());
            prepare.setDate(10, sqlDate);
            prepare.setString(11, appointment_status.getSelectionModel().getSelectedItem());
            prepare.setString(12, Data.doctor_id);
            prepare.setString(13, getSpecialized);
            prepare.setTimestamp(14, scheduleTimestamp);

            prepare.executeUpdate();

            String updatePatient =
                    "UPDATE patient SET "
                            + "full_name = ?, "
                            + "gender = ?, "
                            + "mobile_number = ?, "
                            + "address = ?, "
                            + "description = ?, "
                            + "diagnosis = ?, "
                            + "treatment = ?, "
                            + "specialized = ?, "
                            + "status = ?, "
                            + "date_modify = ? "
                            + "WHERE patient_id = ?";

            prepare = connect.prepareStatement(updatePatient);

            prepare.setString(1, appointment_name.getText());
            prepare.setString(2, appointment_gender.getSelectionModel().getSelectedItem());
            prepare.setString(3, appointment_mobileNumber.getText());
            prepare.setString(4, appointment_address.getText());
            prepare.setString(5, appointment_description.getText());
            prepare.setString(6, appointment_diagnosis.getText());
            prepare.setString(7, appointment_treatment.getText());
            prepare.setString(8, getSpecialized);
            prepare.setString(9, appointment_status.getSelectionModel().getSelectedItem());
            prepare.setDate(10, sqlDate);
            prepare.setLong(11, patientID);

            prepare.executeUpdate();

            appointmentClearBtn();
            refreshAppointmentForm();

            alert.successMessage("Successfully added!");

        } catch (NumberFormatException e) {
            alert.errorMessage("Appointment ID and Patient ID must be numbers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointmentUpdateBtn() {

        if (appointment_appointmentID.getText().isEmpty()
                || appointment_PatientId.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null
                || appointment_time.getSelectionModel().isEmpty()) {

            alert.errorMessage("Please fill the blank fields");
            return;
        }

        connect = Database.connectDB();

        try {
            int appointmentID = Integer.parseInt(appointment_appointmentID.getText());
            long patientID = Long.parseLong(appointment_PatientId.getText());

            LocalDate date = appointment_schedule.getValue();
            LocalTime time = LocalTime.parse(appointment_time.getSelectionModel().getSelectedItem());

            LocalDateTime scheduleDateTime = LocalDateTime.of(date, time);
            Timestamp scheduleTimestamp = Timestamp.valueOf(scheduleDateTime);

            if (scheduleDateTime.isBefore(LocalDateTime.now())) {
                alert.errorMessage("You cannot set an appointment in the past");
                return;
            }

            String checkPatient =
                    "SELECT full_name, gender, mobile_number, address "
                            + "FROM patient "
                            + "WHERE patient_id = ? AND date_delete IS NULL";

            prepare = connect.prepareStatement(checkPatient);
            prepare.setLong(1, patientID);
            result = prepare.executeQuery();

            if (!result.next()) {
                alert.errorMessage("Patient ID not found");
                return;
            }

            appointment_name.setText(result.getString("full_name"));
            appointment_gender.getSelectionModel().select(result.getString("gender"));
            appointment_mobileNumber.setText(result.getString("mobile_number"));
            appointment_address.setText(result.getString("address"));

            String checkSchedule =
                    "SELECT id FROM appointment "
                            + "WHERE doctor = ? "
                            + "AND schedule = ? "
                            + "AND appointment_id <> ? "
                            + "AND date_delete IS NULL";

            prepare = connect.prepareStatement(checkSchedule);
            prepare.setString(1, Data.doctor_id);
            prepare.setTimestamp(2, scheduleTimestamp);
            prepare.setInt(3, appointmentID);
            result = prepare.executeQuery();

            if (result.next()) {
                alert.errorMessage("Appointment time already taken!");
                return;
            }

            String getDoctorData =
                    "SELECT specialized FROM doctor WHERE doctor_id = ?";

            String getSpecialized = "";

            prepare = connect.prepareStatement(getDoctorData);
            prepare.setString(1, Data.doctor_id);
            result = prepare.executeQuery();

            if (result.next()) {
                getSpecialized = result.getString("specialized");
            }

            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

            String checkAppointment =
                    "SELECT id FROM appointment "
                            + "WHERE appointment_id = ? "
                            + "AND date_delete IS NULL";

            prepare = connect.prepareStatement(checkAppointment);
            prepare.setInt(1, appointmentID);
            result = prepare.executeQuery();

            boolean appointmentExists = result.next();

            if (appointmentExists) {

                String updateData =
                        "UPDATE appointment SET "
                                + "patient_id = ?, "
                                + "name = ?, "
                                + "gender = ?, "
                                + "mobile_number = ?, "
                                + "description = ?, "
                                + "diagnosis = ?, "
                                + "treatment = ?, "
                                + "address = ?, "
                                + "status = ?, "
                                + "schedule = ?, "
                                + "date_modify = ? "
                                + "WHERE appointment_id = ?";

                prepare = connect.prepareStatement(updateData);

                prepare.setLong(1, patientID);
                prepare.setString(2, appointment_name.getText());
                prepare.setString(3, appointment_gender.getSelectionModel().getSelectedItem());
                prepare.setString(4, appointment_mobileNumber.getText());
                prepare.setString(5, appointment_description.getText());
                prepare.setString(6, appointment_diagnosis.getText());
                prepare.setString(7, appointment_treatment.getText());
                prepare.setString(8, appointment_address.getText());
                prepare.setString(9, appointment_status.getSelectionModel().getSelectedItem());
                prepare.setTimestamp(10, scheduleTimestamp);
                prepare.setDate(11, sqlDate);
                prepare.setInt(12, appointmentID);

                prepare.executeUpdate();

            } else {

                String insertData =
                        "INSERT INTO appointment "
                                + "(appointment_id, patient_id, name, gender, description, diagnosis, treatment, "
                                + "mobile_number, address, date, status, doctor, specialized, schedule) "
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                prepare = connect.prepareStatement(insertData);

                prepare.setInt(1, appointmentID);
                prepare.setLong(2, patientID);
                prepare.setString(3, appointment_name.getText());
                prepare.setString(4, appointment_gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, appointment_description.getText());
                prepare.setString(6, appointment_diagnosis.getText());
                prepare.setString(7, appointment_treatment.getText());
                prepare.setString(8, appointment_mobileNumber.getText());
                prepare.setString(9, appointment_address.getText());
                prepare.setDate(10, sqlDate);
                prepare.setString(11, appointment_status.getSelectionModel().getSelectedItem());
                prepare.setString(12, Data.doctor_id);
                prepare.setString(13, getSpecialized);
                prepare.setTimestamp(14, scheduleTimestamp);

                prepare.executeUpdate();
            }
            String updatePatient =
                    "UPDATE patient SET "
                            + "description = ?, "
                            + "diagnosis = ?, "
                            + "treatment = ?, "
                            + "date_modify = ? "
                            + "WHERE patient_id = ?";

            prepare = connect.prepareStatement(updatePatient);

            prepare.setString(1, appointment_description.getText());
            prepare.setString(2, appointment_diagnosis.getText());
            prepare.setString(3, appointment_treatment.getText());
            prepare.setDate(4, sqlDate);
            prepare.setLong(5, patientID);

            prepare.executeUpdate();
            prepare.executeUpdate();

            appointmentClearBtn();
            refreshAppointmentForm();

            alert.successMessage("Successfully Updated!");

        } catch (NumberFormatException e) {
            alert.errorMessage("Appointment ID and Patient ID must be numbers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointmentDeleteBtn() {

        if (appointment_appointmentID.getText().isEmpty()) {
            alert.errorMessage("Please select the item first");
        } else {

            String updateData = "UPDATE appointment SET date_delete = ? WHERE appointment_id = ?";

            connect = Database.connectDB();

            try {
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if (alert.confirmationMessage("Are you sure you want to DELETE Appointment ID: "
                        + appointment_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);

                    prepare.setDate(1, sqlDate);
                    prepare.setInt(2, Integer.parseInt(appointment_appointmentID.getText()));
                    prepare.executeUpdate();

                    refreshAppointmentForm();
                    appointmentClearBtn();

                    alert.successMessage("Successully Updated!");
                } else {
                    alert.errorMessage("Cancelled.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void appointmentClearBtn() {
        appointment_appointmentID.clear();
        appointment_name.clear();
        appointment_gender.getSelectionModel().clearSelection();
        appointment_mobileNumber.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_schedule.setValue(null);
        appointment_time.getSelectionModel().clearSelection();
        appointment_PatientId.clear();

        appointmentAppointmentID();
        appointmentShowData();
    }

    private Integer appointmentID;

    public void appointmentGetAppointmentID() {

        if (appointment_PatientId.getText().isEmpty()) {
            appointmentID = 1;
            return;
        }

        String sql =
                "SELECT COALESCE(MAX(appointment_id), 0) + 1 AS next_id "
                        + "FROM appointment "
                        + "WHERE patient_id = ?";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setLong(1, Long.parseLong(appointment_PatientId.getText()));

            result = prepare.executeQuery();

            if (result.next()) {
                appointmentID = result.getInt("next_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointmentAppointmentID() {
        appointmentGetAppointmentID();

        appointment_appointmentID.setText("" + appointmentID);
        appointment_appointmentID.setDisable(true);

    }

    public void appointmentGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        appointment_gender.setItems(listData);

    }

    public void appointmentStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        appointment_status.setItems(listData);

    }

    public void appointmentTimeList() {

        ObservableList<String> list =
                FXCollections.observableArrayList();

        for (int hour = 8; hour <= 18; hour++) {

            list.add(String.format("%02d:00", hour));
            list.add(String.format("%02d:30", hour));
        }

        appointment_time.setItems(list);
    }

    public ObservableList<AppointmentData> appointmentGetData() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL and doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData appData;

            while (result.next()) {

                appData = new AppointmentData(
                        result.getInt("appointment_id"),
                        result.getInt("patient_id"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getString("mobile_number"),
                        result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"),
                        result.getString("address"),
                        result.getDate("date"),
                        result.getDate("date_modify"),
                        result.getDate("date_delete"),
                        result.getString("status"),
                        result.getTimestamp("schedule")
                );
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<AppointmentData> appoinmentListData;

    public void appointmentShowData() {
        appoinmentListData = appointmentGetData();

        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appoinmentListData);
    }

    public void appointmentSelect() {

        AppointmentData appData =
                appointments_tableView.getSelectionModel().getSelectedItem();

        int num =
                appointments_tableView.getSelectionModel().getSelectedIndex();

        if (num < 0) {
            return;
        }

        appointment_appointmentID.setText(String.valueOf(appData.getAppointmentID()));
        appointment_name.setText(appData.getName());
        appointment_gender.getSelectionModel().select(appData.getGender());
        appointment_mobileNumber.setText(appData.getMobileNumber());
        appointment_description.setText(appData.getDescription());
        appointment_diagnosis.setText(appData.getDiagnosis());
        appointment_treatment.setText(appData.getTreatment());
        appointment_address.setText(appData.getAddress());
        appointment_status.getSelectionModel().select(appData.getStatus());
        appointment_PatientId.setText(String.valueOf(appData.getPatient_id()));

        Timestamp ts = appData.getSchedule();

        if (ts != null) {
            LocalDateTime ldt = ts.toLocalDateTime();

            appointment_schedule.setValue(ldt.toLocalDate());

            appointment_time.getSelectionModel().select(
                    ldt.toLocalTime().toString().substring(0, 5)
            );
        }
    }

    public void profileUpdateBtn() {

        connect = Database.connectDB();

        if (profile_doctorID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobileNumber.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specialized.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {

            alert.errorMessage("Please fill all blank fields");
            return;
        }

        try {

            String newDoctorID = profile_doctorID.getText();

            String checkID =
                    "SELECT id FROM doctor "
                            + "WHERE doctor_id = ? "
                            + "AND delete_date IS NULL "
                            + "AND doctor_id <> ?";

            prepare = connect.prepareStatement(checkID);
            prepare.setString(1, newDoctorID);
            prepare.setString(2, Data.doctor_id);

            result = prepare.executeQuery();

            if (result.next()) {
                alert.errorMessage("Doctor ID " + newDoctorID + " is already used by active doctor!");
                return;
            }

            java.sql.Date sqlDate =
                    new java.sql.Date(System.currentTimeMillis());

            String updateData;

            if (Data.path == null || Data.path.isEmpty()) {

                updateData =
                        "UPDATE doctor SET "
                                + "doctor_id = ?, "
                                + "full_name = ?, "
                                + "email = ?, "
                                + "gender = ?, "
                                + "mobile_number = ?, "
                                + "address = ?, "
                                + "specialized = ?, "
                                + "status = ?, "
                                + "modify_date = ? "
                                + "WHERE doctor_id = ?";

                prepare = connect.prepareStatement(updateData);

                prepare.setString(1, newDoctorID);
                prepare.setString(2, profile_name.getText());
                prepare.setString(3, profile_email.getText());
                prepare.setString(4, profile_gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, profile_mobileNumber.getText());
                prepare.setString(6, profile_address.getText());
                prepare.setString(7, profile_specialized.getSelectionModel().getSelectedItem());
                prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                prepare.setDate(9, sqlDate);
                prepare.setString(10, Data.doctor_id);

            } else {

                Path transfer = Paths.get(Data.path);

                Path copy = Paths.get(
                        "D:\\JavaProject\\Hospital\\src\\main\\resources\\com\\yakubin\\hospital\\Directory\\"
                                + newDoctorID + ".jpg"
                );

                Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);

                updateData =
                        "UPDATE doctor SET "
                                + "doctor_id = ?, "
                                + "full_name = ?, "
                                + "email = ?, "
                                + "gender = ?, "
                                + "mobile_number = ?, "
                                + "address = ?, "
                                + "image = ?, "
                                + "specialized = ?, "
                                + "status = ?, "
                                + "modify_date = ? "
                                + "WHERE doctor_id = ?";

                prepare = connect.prepareStatement(updateData);

                prepare.setString(1, newDoctorID);
                prepare.setString(2, profile_name.getText());
                prepare.setString(3, profile_email.getText());
                prepare.setString(4, profile_gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, profile_mobileNumber.getText());
                prepare.setString(6, profile_address.getText());
                prepare.setString(7, copy.toAbsolutePath().toString());
                prepare.setString(8, profile_specialized.getSelectionModel().getSelectedItem());
                prepare.setString(9, profile_status.getSelectionModel().getSelectedItem());
                prepare.setDate(10, sqlDate);
                prepare.setString(11, Data.doctor_id);
            }

            prepare.executeUpdate();

            Data.doctor_id = newDoctorID;
            Data.doctor_name = profile_name.getText();
            Data.path = "";

            profileLabels();
            profileFields();
            profileDisplayImages();
            displayAdminIDNumberName();

            alert.successMessage("Updated Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_circleImage.setFill(new ImagePattern(image));
        }

    }

    public void profileLabels() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileFields() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialized.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_path1 = "File:" + result.getString("image");
                temp_path2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(temp_path1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 128, 103, false, true);
                    profile_circleImage.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public void profileSpecializedList() {

        List<String> listS = new ArrayList<>();

        for (String data : specialization) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialized.setItems(listData);
    }

    public void profileStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminIDNumberName() {

        String name = Data.doctor_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);

    }

    public void refreshAppointmentForm() {
        appointmentShowData();
        appointmentAppointmentID();

        dashbboardDisplayTA();
        dashboardDisplayData();
        dashboardNOA();
    }


    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);

            dashbboardDisplayIP();
            dashbboardDisplayTP();
            dashbboardDisplayAP();
            dashbboardDisplayTA();

            dashboardDisplayData();
            dashboardNOP();
            dashboardNOA();
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);

            appointmentShowData();
            appointmentAppointmentID();
            appointmentTimeList();
            appointmentGenderList();
            appointmentStatusList();
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        }

    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                logout_btn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
                Data.temp_name = "";
                Data.temp_gender = "";
                Data.temp_number = "0";
                Data.temp_address = "";
                Data.temp_status = "";
                Data.temp_date = "";
                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runTime() {
        new Thread(() -> {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    date_time.setText(format.format(new Date()));
                });
            }
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayAdminIDNumberName();
        displayAdminIDNumberName();
        runTime();

        dashbboardDisplayIP();
        dashbboardDisplayTP();
        dashbboardDisplayAP();
        dashbboardDisplayTA();

        dashboardDisplayData();
        dashboardNOP();
        dashboardNOA();

        appointmentShowData();
        appointmentGenderList();
        appointmentStatusList();
        appointmentAppointmentID();
        appointmentTimeList();

        patientGenderList();

        profileGenderList();
        profileSpecializedList();
        profileStatusList();

        profileLabels();
        profileFields();
        profileDisplayImages();

        profile_doctorID.setEditable(false);
    }

}
