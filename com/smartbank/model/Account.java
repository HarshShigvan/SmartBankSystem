package com.smartbank.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.smartbank.exceptions.InsufficientFundsException;
import com.smartbank.utils.ConfigLoader;

public abstract class Account {
    private static final AtomicInteger counter = new AtomicInteger(Integer.parseInt(ConfigLoader.getProperty("initial.account.number")));
    private static final int MAX_TRANSACTIONS_IN_STATEMENT = 5;

    protected int accountNumber;
    protected String name;
    protected String pin;
    protected double balance;
    protected List<Transaction> transactions;

    public Account(String name, String pin, double deposit) {
        this.accountNumber = counter.incrementAndGet();
        this.name = name;
        this.pin = pin;
        this.balance = deposit;
        this.transactions = new ArrayList<>();
        recordTransaction(deposit, "DEPOSIT", "Initial account deposit");
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public boolean verifyPin(String input) {
        return this.pin.equals(input);
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        recordTransaction(amount, "DEPOSIT", "Cash deposit");
        System.out.println("Deposit successful. New balance: ₹" + String.format("%.2f", balance));
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public void recordTransaction(double amount, String type, String description) {
        if (transactions.size() == MAX_TRANSACTIONS_IN_STATEMENT) transactions.remove(0);
        transactions.add(new Transaction(type, amount, description));
    }

    public void printMiniStatement() {
        System.out.println("Mini Statement for A/C " + accountNumber + ":");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        }
        for (Transaction tx : transactions) {
            System.out.println(tx);
        }
        System.out.println("------------------------------------------");
    }
}
