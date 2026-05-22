package com.yakubin.hospital;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DoctorDataTest {

    @Test
    void fullConstructorShouldStoreAllDoctorFields() {
        Date created = Date.valueOf("2026-05-01");
        Date modified = Date.valueOf("2026-05-02");
        Date deleted = Date.valueOf("2026-05-03");

        DoctorData doctor = new DoctorData(
                1, "DID-1", "pass", "Dr. Smith", "smith@test.com", "Male",
                "+380501234567", "Cardiologist", "Kyiv", "doctor.jpg",
                created, modified, deleted, "Active"
        );

        assertEquals(1, doctor.getId());
        assertEquals("DID-1", doctor.getDoctorID());
        assertEquals("pass", doctor.getPassword());
        assertEquals("Dr. Smith", doctor.getFullName());
        assertEquals("smith@test.com", doctor.getEmail());
        assertEquals("Male", doctor.getGender());
        assertEquals("+380501234567", doctor.getMobileNumber());
        assertEquals("Cardiologist", doctor.getSpecialized());
        assertEquals("Kyiv", doctor.getAddress());
        assertEquals("doctor.jpg", doctor.getImage());
        assertEquals(created, doctor.getDate());
        assertEquals(modified, doctor.getDateModify());
        assertEquals(deleted, doctor.getDateDelete());
        assertEquals("Active", doctor.getStatus());
    }

    @Test
    void dashboardConstructorShouldStoreShortFields() {
        DoctorData doctor = new DoctorData("DID-2", "Dr. Brown", "Dermatologist", "Inactive");

        assertEquals("DID-2", doctor.getDoctorID());
        assertEquals("Dr. Brown", doctor.getFullName());
        assertEquals("Dermatologist", doctor.getSpecialized());
        assertEquals("Inactive", doctor.getStatus());
        assertNull(doctor.getId());
        assertNull(doctor.getEmail());
    }

    @Test
    void cardConstructorShouldStoreCardFields() {
        DoctorData doctor = new DoctorData(5, "DID-5", "Dr. Green", "Ophthalmologist", "green@test.com", "green.jpg");

        assertEquals(5, doctor.getId());
        assertEquals("DID-5", doctor.getDoctorID());
        assertEquals("Dr. Green", doctor.getFullName());
        assertEquals("Ophthalmologist", doctor.getSpecialized());
        assertEquals("green@test.com", doctor.getEmail());
        assertEquals("green.jpg", doctor.getImage());
        assertNull(doctor.getStatus());
    }
}
