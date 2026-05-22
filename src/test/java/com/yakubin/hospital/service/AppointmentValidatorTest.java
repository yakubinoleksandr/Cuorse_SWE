package com.yakubin.hospital.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppointmentValidatorTest {

    private final AppointmentValidator validator = new AppointmentValidator();

    @Test
    void shouldReturnTrueWhenTimeIsAvailable() {
        LocalDateTime selectedTime = LocalDateTime.of(2026, 5, 21, 10, 0);

        List<LocalDateTime> existingAppointments = List.of(
                LocalDateTime.of(2026, 5, 21, 9, 0),
                LocalDateTime.of(2026, 5, 21, 11, 0)
        );

        assertTrue(validator.isTimeAvailable(selectedTime, existingAppointments));
    }

    @Test
    void shouldReturnFalseWhenTimeIsAlreadyBooked() {
        LocalDateTime selectedTime = LocalDateTime.of(2026, 5, 21, 10, 0);
        List<LocalDateTime> existingAppointments = List.of(selectedTime);

        assertFalse(validator.isTimeAvailable(selectedTime, existingAppointments));
    }

    @Test
    void shouldReturnFalseWhenSelectedTimeIsNull() {
        assertFalse(validator.isTimeAvailable(null, List.of()));
    }

    @Test
    void shouldReturnFalseWhenExistingAppointmentsListIsNull() {
        assertFalse(validator.isTimeAvailable(LocalDateTime.now().plusDays(1), null));
    }

    @Test
    void shouldReturnTrueForFutureDate() {
        assertTrue(validator.isFutureDate(LocalDateTime.now().plusDays(1)));
    }

    @Test
    void shouldReturnFalseForPastDate() {
        assertFalse(validator.isFutureDate(LocalDateTime.now().minusDays(1)));
    }

    @Test
    void shouldReturnFalseWhenFutureDateIsNull() {
        assertFalse(validator.isFutureDate(null));
    }
}
