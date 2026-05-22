package com.yakubin.hospital.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationValidatorTest {

    private final AuthenticationValidator validator = new AuthenticationValidator();

    @Test
    void unitShouldAcceptNonEmptyCredentials() {
        assertTrue(validator.hasRequiredCredentials("admin", "password123"));
    }

    @Test
    void unitShouldRejectMissingCredentials() {
        assertFalse(validator.hasRequiredCredentials(null, "password123"));
        assertFalse(validator.hasRequiredCredentials("admin", null));
        assertFalse(validator.hasRequiredCredentials(" ", "password123"));
        assertFalse(validator.hasRequiredCredentials("admin", " "));
    }

    @Test
    void unitShouldAcceptOnlyExistingProjectPortals() {
        assertTrue(validator.isAllowedPortal("Admin Portal"));
        assertTrue(validator.isAllowedPortal("Doctor Portal"));
        assertTrue(validator.isAllowedPortal("Patient Portal"));
        assertFalse(validator.isAllowedPortal("Guest Portal"));
        assertFalse(validator.isAllowedPortal(null));
    }

    @Test
    void functionalShouldAllowLoginWhenCredentialsAndPortalAreValid() {
        assertTrue(validator.canLogin("doctor01", "secret", "Doctor Portal"));
    }

    @Test
    void functionalShouldRejectLoginWhenPortalOrCredentialsAreInvalid() {
        assertFalse(validator.canLogin("doctor01", "secret", "Guest Portal"));
        assertFalse(validator.canLogin("doctor01", "", "Doctor Portal"));
    }
}
