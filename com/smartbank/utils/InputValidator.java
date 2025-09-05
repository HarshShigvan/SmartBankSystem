package com.smartbank.utils;

public class InputValidator {
    public static boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4}");
    }

    public static boolean isValidAccountType(String type) {
        return type != null && (type.equalsIgnoreCase("savings") || type.equalsIgnoreCase("current"));
    }

    public static boolean isPositiveAmount(double amount) {
        return amount > 0;
    }
}
