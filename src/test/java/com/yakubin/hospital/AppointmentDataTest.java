package com.yakubin.hospital;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AppointmentDataTest {

    private final Date created = Date.valueOf("2026-05-21");
    private final Date modified = Date.valueOf("2026-05-22");
    private final Date deleted = Date.valueOf("2026-05-23");
    private final Timestamp schedule = Timestamp.valueOf("2026-05-24 10:30:00");

    @Test
    void fullConstructorShouldStoreAllAppointmentFields() {
        AppointmentData data = new AppointmentData(
                1, 2, "Ivan Petrenko", "Male", "+380501112233",
                "Headache", "Migraine", "Rest", "Kyiv",
                "DID-1", "Cardiologist", created, modified, deleted,
                "Confirm", schedule
        );

        assertEquals(1, data.getId());
        assertEquals(2, data.getAppointmentID());
        assertEquals("Ivan Petrenko", data.getName());
        assertEquals("Male", data.getGender());
        assertEquals("+380501112233", data.getMobileNumber());
        assertEquals("Headache", data.getDescription());
        assertEquals("Migraine", data.getDiagnosis());
        assertEquals("Rest", data.getTreatment());
        assertEquals("Kyiv", data.getAddress());
        assertEquals("DID-1", data.getDoctorID());
        assertEquals("Cardiologist", data.getSpecialized());
        assertEquals(created, data.getDate());
        assertEquals(modified, data.getDateModify());
        assertEquals(deleted, data.getDateDelete());
        assertEquals("Confirm", data.getStatus());
        assertEquals(schedule, data.getSchedule());
        assertNull(data.getDoctor());
        assertNull(data.getPatient_id());
    }

    @Test
    void patientConstructorShouldStorePatientAppointmentFields() {
        AppointmentData data = new AppointmentData(
                7, 15, "Olena", "Female", "+380671234567",
                "Cough", "Cold", "Tea", "Lviv",
                created, modified, deleted, "Active", schedule
        );

        assertEquals(7, data.getAppointmentID());
        assertEquals(15, data.getPatient_id());
        assertEquals("Olena", data.getName());
        assertEquals("Female", data.getGender());
        assertEquals("+380671234567", data.getMobileNumber());
        assertEquals("Cough", data.getDescription());
        assertEquals("Cold", data.getDiagnosis());
        assertEquals("Tea", data.getTreatment());
        assertEquals("Lviv", data.getAddress());
        assertEquals(created, data.getDate());
        assertEquals(modified, data.getDateModify());
        assertEquals(deleted, data.getDateDelete());
        assertEquals("Active", data.getStatus());
        assertEquals(schedule, data.getSchedule());
    }

    @Test
    void shortPatientViewConstructorShouldStoreOnlyViewFields() {
        AppointmentData data = new AppointmentData(3, "Petro", "Checkup", created, "Inactive");

        assertEquals(3, data.getAppointmentID());
        assertEquals("Petro", data.getName());
        assertEquals("Checkup", data.getDescription());
        assertEquals(created, data.getDate());
        assertEquals("Inactive", data.getStatus());
        assertNull(data.getId());
    }

    @Test
    void recordConstructorShouldStoreMedicalRecordFields() {
        AppointmentData data = new AppointmentData(11, "Pain", "Sprain", "Bandage", "DID-2", schedule);

        assertEquals(11, data.getAppointmentID());
        assertEquals("Pain", data.getDescription());
        assertEquals("Sprain", data.getDiagnosis());
        assertEquals("Bandage", data.getTreatment());
        assertEquals("DID-2", data.getDoctor());
        assertEquals(schedule, data.getSchedule());
    }
}
