package com.yakubin.hospital.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HospitalBusinessScenarioTest {

    private final AuthenticationValidator authenticationValidator = new AuthenticationValidator();
    private final PatientValidator patientValidator = new PatientValidator();
    private final AppointmentValidator appointmentValidator = new AppointmentValidator();
    private final PaymentCalculator paymentCalculator = new PaymentCalculator();

    @Test
    void integrationDoctorCanCreateAppointmentWhenPatientDataAndTimeAreValid() {
        LocalDateTime selectedTime = LocalDateTime.now().plusDays(2).withMinute(0).withSecond(0).withNano(0);
        List<LocalDateTime> existingAppointments = List.of(selectedTime.minusHours(1), selectedTime.plusHours(1));

        assertTrue(authenticationValidator.canLogin("DID-1", "doctorPass", "Doctor Portal"));
        assertTrue(patientValidator.isPatientInputValid("Petro Bondar", "+380501112233", "Male", "Active"));
        assertTrue(appointmentValidator.isFutureDate(selectedTime));
        assertTrue(appointmentValidator.isTimeAvailable(selectedTime, existingAppointments));
    }

    @Test
    void integrationDoctorCannotCreateAppointmentWhenSelectedTimeIsBusy() {
        LocalDateTime selectedTime = LocalDateTime.now().plusDays(2).withMinute(0).withSecond(0).withNano(0);

        assertFalse(appointmentValidator.isTimeAvailable(selectedTime, List.of(selectedTime)));
    }

    @Test
    void functionalPatientPaymentScenarioShouldCalculateDiscountAndDebt() {
        BigDecimal total = paymentCalculator.calculateTotal(new BigDecimal("1000"), new BigDecimal("10"));
        BigDecimal debt = paymentCalculator.calculateDebt(total, new BigDecimal("600"));

        assertEquals(new BigDecimal("900.00"), total);
        assertEquals(new BigDecimal("300.00"), debt);
        assertFalse(paymentCalculator.isFullyPaid(total, new BigDecimal("600")));
        assertTrue(paymentCalculator.isFullyPaid(total, new BigDecimal("900")));
    }
}
