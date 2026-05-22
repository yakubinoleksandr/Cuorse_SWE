package com.yakubin.hospital.service;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentValidator {

    public boolean isTimeAvailable(LocalDateTime selectedTime, List<LocalDateTime> existingAppointments) {
        if (selectedTime == null || existingAppointments == null) {
            return false;
        }

        return !existingAppointments.contains(selectedTime);
    }

    public boolean isFutureDate(LocalDateTime selectedTime) {
        if (selectedTime == null) {
            return false;
        }

        return selectedTime.isAfter(LocalDateTime.now());
    }
}