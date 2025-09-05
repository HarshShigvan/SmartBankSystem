package com.smartbank.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final LocalDateTime timestamp;
    private final String type;
    private final double amount;
    private final String description;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaction(String type, double amount, String description) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s - %-10s - Amount: ₹%.2f - %s",
                timestamp.format(FORMATTER), type, amount, description);
    }
}
