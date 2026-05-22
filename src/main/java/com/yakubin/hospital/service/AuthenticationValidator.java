package com.yakubin.hospital.service;

import java.util.Arrays;

public class AuthenticationValidator {

    private static final String[] PORTALS = {"Admin Portal", "Doctor Portal", "Patient Portal"};

    public boolean hasRequiredCredentials(String username, String password) {
        return username != null && !username.trim().isEmpty()
                && password != null && !password.trim().isEmpty();
    }

    public boolean isAllowedPortal(String portal) {
        return portal != null && Arrays.asList(PORTALS).contains(portal);
    }

    public boolean canLogin(String username, String password, String portal) {
        return hasRequiredCredentials(username, password) && isAllowedPortal(portal);
    }
}
