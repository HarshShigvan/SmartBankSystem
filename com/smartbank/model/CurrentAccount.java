package com.smartbank.model;

import com.smartbank.exceptions.InsufficientFundsException;
import com.smartbank.utils.ConfigLoader;

public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = Double.parseDouble(ConfigLoader.getProperty("current.overdraft.limit"));

    public CurrentAccount(String name, String pin, double deposit) {
        super(name, pin, deposit);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance + OVERDRAFT_LIMIT) {
            balance -= amount;
            recordTransaction(amount, "WITHDRAWAL", "Cash withdrawal");
            System.out.println("Withdrawal successful. New balance: ₹" + String.format("%.2f", balance));
        } else {
            throw new InsufficientFundsException("Overdraft limit exceeded. Available including overdraft: ₹" + String.format("%.2f", balance + OVERDRAFT_LIMIT));
        }
    }
}
