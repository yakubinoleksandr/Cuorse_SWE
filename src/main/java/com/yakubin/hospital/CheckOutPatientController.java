package com.yakubin.hospital;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CheckOutPatientController implements Initializable {

    @FXML
    private Label checkout_patientID;

    @FXML
    private Label checkout_name;

    @FXML
    private Label checkout_doctor;

    @FXML
    private DatePicker checkout_date;

    @FXML
    private ImageView checkout_imageView;

    @FXML
    private DatePicker checkout_checkout;

    @FXML
    private Label checkout_totalDays;

    @FXML
    private Label checkout_totalPrice;

    private Image image;

    private final AlertMessage alert = new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;

    public void countBtn() {
        long countDays = 0;
        if (checkout_date.getValue() != null || checkout_checkout.getValue() != null) {
            countDays
                    = ChronoUnit.DAYS.between(checkout_date.getValue(), checkout_checkout.getValue());
        } else {
            alert.errorMessage("Something went wrong.");
        }
        double price = 20.5;

        double totalprice = (price * countDays);

        checkout_totalDays.setText(String.valueOf(countDays));
        checkout_totalPrice.setText(String.valueOf(totalprice));

    }

    public void payBtn() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        if (checkout_checkout.getValue() == null
                || checkout_totalDays.getText().isEmpty()
                || checkout_totalPrice.getText().isEmpty()) {

            alert.errorMessage("Invalid");

        } else {

            if (alert.confirmationMessage("Are you sure you want to pay?")) {

                String sql =
                        "INSERT INTO payment "
                                + "(patient_id, doctor, total_days, total_price, "
                                + "date, date_checkout, date_pay, appointment_id) "
                                + "VALUES(?,?,?,?,?,?,?,?)";

                connect = Database.connectDB();

                try {

                    prepare = connect.prepareStatement(sql);

                    prepare.setInt(1, Integer.parseInt(checkout_patientID.getText()));
                    prepare.setString(2, checkout_doctor.getText());
                    prepare.setInt(3, Integer.parseInt(checkout_totalDays.getText()));
                    prepare.setDouble(4, Double.parseDouble(checkout_totalPrice.getText()));
                    prepare.setDate(5, java.sql.Date.valueOf(checkout_date.getValue()));
                    prepare.setDate(6, java.sql.Date.valueOf(checkout_checkout.getValue()));
                    prepare.setDate(7, sqlDate);
                    prepare.setInt(8, Integer.parseInt(Data.temp_appID));

                    prepare.executeUpdate();

                    String updateData =
                            "UPDATE appointment "
                                    + "SET status_pay = ? "
                                    + "WHERE appointment_id = ?";

                    prepare = connect.prepareStatement(updateData);

                    prepare.setString(1, "Paid");
                    prepare.setInt(2, Integer.parseInt(Data.temp_appID));

                    prepare.executeUpdate();

                    alert.successMessage("Successful!");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                alert.errorMessage("Cancelled.");
            }
        }
    }

    public void displayFields() {

        checkout_patientID.setText(String.valueOf(Data.temp_PatientID));
        checkout_name.setText(Data.temp_name);
        checkout_doctor.setText(Data.temp_appDoctor);
        checkout_date.setValue(LocalDate.parse(Data.temp_date));
        checkout_date.setDisable(true);

        image = new Image("File:" + Data.temp_path, 116, 132, false, true);
        checkout_imageView.setImage(image);

    }
    public String getDoctorName(String doctorId) {

        String doctorName = "";

        String sql = "SELECT full_name FROM doctor WHERE doctor_id = ?";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, doctorId);

            ResultSet result = prepare.executeQuery();

            if (result.next()) {
                doctorName = result.getString("full_name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctorName;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayFields();
    }

}
