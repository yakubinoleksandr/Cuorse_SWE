package com.yakubin.hospital;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataAndUsersTest {

    @AfterEach
    void cleanSharedState() {
        Data.admin_id = null;
        Data.admin_username = null;
        Data.path = null;
        Data.doctor_id = null;
        Data.doctor_name = null;
        Data.patient_id = null;
        Data.temp_PatientID = null;
        Data.temp_name = null;
        Data.temp_gender = null;
        Data.temp_number = null;
        Data.temp_address = null;
        Data.temp_status = null;
        Data.temp_date = null;
        Data.temp_path = null;
        Data.temp_doctorID = null;
        Data.temp_doctorName = null;
        Data.temp_doctorEmail = null;
        Data.temp_doctorPassword = null;
        Data.temp_doctorSpecialized = null;
        Data.temp_doctorGender = null;
        Data.temp_doctorMobileNumber = null;
        Data.temp_doctorImagePath = null;
        Data.temp_doctorAddress = null;
        Data.temp_doctorStatus = null;
        Data.temp_appID = null;
        Data.temp_appName = null;
        Data.temp_appGender = null;
        Data.temp_appMobileNumber = null;
        Data.temp_appAddress = null;
        Data.temp_appDescription = null;
        Data.temp_appDiagnosis = null;
        Data.temp_appTreatment = null;
        Data.temp_appDoctor = null;
        Data.temp_appSpecialized = null;
        Data.temp_appStatus = null;
    }

    @Test
    void shouldExposeConstantOptionsUsedByForms() {
        assertArrayEquals(new String[]{"Male", "Female", "Others"}, Data.gender);
        assertArrayEquals(new String[]{"Active", "Inactive", "Confirm"}, Data.status);
        assertArrayEquals(new String[]{"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"}, Data.specialization);
        assertArrayEquals(new String[]{"Admin Portal", "Doctor Portal", "Patient Portal"}, Users.user);
    }

    @Test
    void shouldKeepTemporarySessionValues() {
        Data.admin_id = 1;
        Data.admin_username = "admin";
        Data.doctor_id = "DID-1";
        Data.doctor_name = "Doctor";
        Data.patient_id = 55;
        Data.temp_appID = "3";
        Data.temp_appDoctor = "DID-2";
        Data.temp_appStatus = "Confirm";

        assertEquals(1, Data.admin_id);
        assertEquals("admin", Data.admin_username);
        assertEquals("DID-1", Data.doctor_id);
        assertEquals("Doctor", Data.doctor_name);
        assertEquals(55, Data.patient_id);
        assertEquals("3", Data.temp_appID);
        assertEquals("DID-2", Data.temp_appDoctor);
        assertEquals("Confirm", Data.temp_appStatus);
    }
}
