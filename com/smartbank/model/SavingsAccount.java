package com.smartbank.model;

import com.smartbank.exceptions.InsufficientFundsException;
import com.smartbank.utils.ConfigLoader;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = Double.parseDouble(ConfigLoader.getProperty("savings.interest.rate"));

    public SavingsAccount(String name, String pin, double deposit) {
        super(name, pin, deposit);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance) {
            balance -= amount;
            recordTransaction(amount, "WITHDRAWAL", "Cash withdrawal");
            System.out.println("Withdrawal successful. New balance: ₹" + String.format("%.2f", balance));
        } else {
            throw new InsufficientFundsException("Insufficient balance. Available: ₹" + String.format("%.2f", balance));
        }
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        recordTransaction(interest, "INTEREST", "Quarterly interest credited");
        System.out.println("Interest of ₹" + String.format("%.2f", interest) + " applied.");
        System.out.println("New balance: ₹" + String.format("%.2f", balance));
    }
}
