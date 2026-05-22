package com.yakubin.hospital;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatientsDataTest {

    private final Date created = Date.valueOf("2026-05-10");
    private final Date modified = Date.valueOf("2026-05-11");
    private final Date deleted = Date.valueOf("2026-05-12");

    @Test
    void fullConstructorShouldStoreAllPatientFields() {
        PatientsData patient = new PatientsData(
                1, 100, "pass", "Anna", "+380501111111", "Female", "Kyiv",
                "anna.jpg", "Fever", "Flu", "Medicine", "DID-1", "Cardiologist",
                created, modified, deleted, "Active"
        );

        assertEquals(1, patient.getId());
        assertEquals(100, patient.getPatientID());
        assertEquals("pass", patient.getPassword());
        assertEquals("Anna", patient.getFullName());
        assertEquals("+380501111111", patient.getMobileNumber());
        assertEquals("Female", patient.getGender());
        assertEquals("Kyiv", patient.getAddress());
        assertEquals("anna.jpg", patient.getImage());
        assertEquals("Fever", patient.getDescription());
        assertEquals("Flu", patient.getDiagnosis());
        assertEquals("Medicine", patient.getTreatment());
        assertEquals("DID-1", patient.getDoctor());
        assertEquals("Cardiologist", patient.getSpecialized());
        assertEquals(created, patient.getDate());
        assertEquals(modified, patient.getDateModify());
        assertEquals(deleted, patient.getDateDelete());
        assertEquals("Active", patient.getStatus());
        assertNull(patient.getAppointmentID());
    }

    @Test
    void tableConstructorShouldStorePatientTableFields() {
        PatientsData patient = new PatientsData(2, 101, "Oleh", "Male", "+380502222222", "Odesa", "Inactive", created, modified, deleted);

        assertEquals(2, patient.getId());
        assertEquals(101, patient.getPatientID());
        assertEquals("Oleh", patient.getFullName());
        assertEquals("Male", patient.getGender());
        assertEquals("+380502222222", patient.getMobileNumber());
        assertEquals("Odesa", patient.getAddress());
        assertEquals("Inactive", patient.getStatus());
        assertEquals(created, patient.getDate());
        assertEquals(modified, patient.getDateModify());
        assertEquals(deleted, patient.getDateDelete());
    }

    @Test
    void cardConstructorShouldStoreMedicalAndImageFields() {
        PatientsData patient = new PatientsData(3, 102, "Ira", "Female", "Pain", "Injury", "Rest", "DID-2", "ira.jpg", created);

        assertEquals(3, patient.getId());
        assertEquals(102, patient.getPatientID());
        assertEquals("Ira", patient.getFullName());
        assertEquals("Female", patient.getGender());
        assertEquals("Pain", patient.getDescription());
        assertEquals("Injury", patient.getDiagnosis());
        assertEquals("Rest", patient.getTreatment());
        assertEquals("DID-2", patient.getDoctor());
        assertEquals("ira.jpg", patient.getImage());
        assertEquals(created, patient.getDate());
    }

    @Test
    void recordConstructorShouldStoreOnlyMedicalRecordFields() {
        PatientsData patient = new PatientsData(4, 103, "Cough", "Cold", "Tea", created);

        assertEquals(4, patient.getId());
        assertEquals(103, patient.getPatientID());
        assertEquals("Cough", patient.getDescription());
        assertEquals("Cold", patient.getDiagnosis());
        assertEquals("Tea", patient.getTreatment());
        assertEquals(created, patient.getDate());
        assertNull(patient.getFullName());
    }

    @Test
    void appointmentRecordConstructorShouldStoreAppointmentAndPatientFields() {
        PatientsData patient = new PatientsData(5, 9, 104, "Roman", "Male", "Check", "Healthy", "None", "DID-3", "roman.jpg", created);

        assertEquals(5, patient.getId());
        assertEquals(9, patient.getAppointmentID());
        assertEquals(104, patient.getPatientID());
        assertEquals("Roman", patient.getFullName());
        assertEquals("Male", patient.getGender());
        assertEquals("Check", patient.getDescription());
        assertEquals("Healthy", patient.getDiagnosis());
        assertEquals("None", patient.getTreatment());
        assertEquals("DID-3", patient.getDoctor());
        assertEquals("roman.jpg", patient.getImage());
        assertEquals(created, patient.getDate());
    }
}
