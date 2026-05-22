package com.yakubin.hospital;

import java.sql.Date;
import java.sql.Timestamp;

public class AppointmentData {

    private Integer id;
    private Integer appointmentID;
    private Integer patient_id;

    private String doctor;

    private String name;
    private String gender;
    private String description;
    private String diagnosis;
    private String treatment;
    private String mobileNumber;
    private String address;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;

    private String doctorID;

    private String specialized;
    private Timestamp schedule;
    public AppointmentData(Integer id, Integer appointmentID, String name, String gender,
                           String mobileNumber, String description, String diagnosis, String treatment, String address,
                           String doctorID, String specialized,
                           Date date, Date dateModify, Date dateDelete, String status, Timestamp schedule) {
        this.id = id;
        this.appointmentID = appointmentID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.address = address;
        this.doctorID = doctorID;
        this.specialized = specialized;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
        this.schedule = schedule;

    }
    public AppointmentData(Integer appointmentID, Integer patient_id, String name, String gender,
                           String mobileNumber, String description, String diagnosis, String treatment, String address,
                           Date date, Date dateModify, Date dateDelete, String status, Timestamp schedule) {

        this.appointmentID = appointmentID;
        this.patient_id = patient_id;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.address = address;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
        this.schedule = schedule;
    }

    public AppointmentData(Integer appointmentID, String name,
                           String description, Date date, String status) {
        this.appointmentID = appointmentID;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public AppointmentData(Integer appointmentID, String description,
                           String diagnosis, String treatment, String doctor, Timestamp schedule) {
        this.appointmentID = appointmentID;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctor = doctor;
        this.schedule = schedule;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getAddress() {
        return address;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getSpecialized() {
        return specialized;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public Timestamp getSchedule() {
        return schedule;
    }

    public String getDoctor() {
        return doctor;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

}
