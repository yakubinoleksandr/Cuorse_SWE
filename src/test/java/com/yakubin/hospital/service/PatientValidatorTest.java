package com.yakubin.hospital.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientValidatorTest {

    private final PatientValidator validator = new PatientValidator();

    @Test
    void unitShouldAcceptValidPatientName() {
        assertTrue(validator.isValidFullName("Ivan Petrenko"));
    }

    @Test
    void unitShouldRejectEmptyOrTooShortPatientName() {
        assertFalse(validator.isValidFullName(null));
        assertFalse(validator.isValidFullName(""));
        assertFalse(validator.isValidFullName(" A "));
    }

    @Test
    void unitShouldAcceptValidMobileNumber() {
        assertTrue(validator.isValidMobileNumber("+380501234567"));
        assertTrue(validator.isValidMobileNumber("0501234567"));
    }

    @Test
    void unitShouldRejectInvalidMobileNumber() {
        assertFalse(validator.isValidMobileNumber(null));
        assertFalse(validator.isValidMobileNumber("123"));
        assertFalse(validator.isValidMobileNumber("phone-number"));
    }

    @Test
    void unitShouldValidateGenderOptionsFromProject() {
        assertTrue(validator.isValidGender("Male"));
        assertTrue(validator.isValidGender("Female"));
        assertTrue(validator.isValidGender("Others"));
        assertFalse(validator.isValidGender("Unknown"));
    }

    @Test
    void unitShouldValidateStatusOptionsFromProject() {
        assertTrue(validator.isValidStatus("Active"));
        assertTrue(validator.isValidStatus("Inactive"));
        assertTrue(validator.isValidStatus("Confirm"));
        assertFalse(validator.isValidStatus("Deleted"));
    }

    @Test
    void integrationShouldAcceptCompleteValidPatientInput() {
        assertTrue(validator.isPatientInputValid("Olena Ivanenko", "+380671234567", "Female", "Active"));
    }

    @Test
    void integrationShouldRejectPatientInputWhenOneRequiredFieldIsInvalid() {
        assertFalse(validator.isPatientInputValid("Olena Ivanenko", "bad", "Female", "Active"));
        assertFalse(validator.isPatientInputValid("Olena Ivanenko", "+380671234567", "Bad", "Active"));
        assertFalse(validator.isPatientInputValid("Olena Ivanenko", "+380671234567", "Female", "Bad"));
    }
}
