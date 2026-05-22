package com.yakubin.hospital.service;

public class PatientValidator {

    public boolean isValidFullName(String fullName) {
        return fullName != null && fullName.trim().length() >= 2;
    }

    public boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber != null && mobileNumber.matches("\\+?\\d{10,15}");
    }

    public boolean isValidGender(String gender) {
        return "Male".equals(gender) || "Female".equals(gender) || "Others".equals(gender);
    }

    public boolean isValidStatus(String status) {
        return "Active".equals(status) || "Inactive".equals(status) || "Confirm".equals(status);
    }

    public boolean isPatientInputValid(String fullName, String mobileNumber, String gender, String status) {
        return isValidFullName(fullName)
                && isValidMobileNumber(mobileNumber)
                && isValidGender(gender)
                && isValidStatus(status);
    }
}
