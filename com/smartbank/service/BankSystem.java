package com.smartbank.service;

import com.smartbank.data.Database;
import com.smartbank.exceptions.AccountNotFoundException;
import com.smartbank.exceptions.InsufficientFundsException;
import com.smartbank.model.Account;
import com.smartbank.model.CurrentAccount;
import com.smartbank.model.SavingsAccount;

public class BankSystem {
    private final Database database = Database.getInstance();

    public Account register(String name, String pin, double deposit, String type) {
        Account acc;
        if (type.equalsIgnoreCase("savings")) {
            acc = new SavingsAccount(name, pin, deposit);
        } else if (type.equalsIgnoreCase("current")) {
            acc = new CurrentAccount(name, pin, deposit);
        } else {
            return null;
        }
        database.saveAccount(acc);
        System.out.println("Account created. A/C No: " + acc.getAccountNumber());
        return acc;
    }

    public Account login(int accNo, String pin) throws AccountNotFoundException {
        Account acc = database.findAccountById(accNo);
        if (acc != null && acc.verifyPin(pin)) {
            return acc;
        }
        return null;
    }

    public void transfer(int fromAccNumber, int toAccNumber, double amount) throws AccountNotFoundException, InsufficientFundsException {
        if (fromAccNumber == toAccNumber) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        Account fromAccount = database.findAccountById(fromAccNumber);
        Account toAccount = database.findAccountById(toAccNumber);

        // The withdraw method will throw InsufficientFundsException if needed
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        fromAccount.recordTransaction(amount, "TRANSFER_OUT", "To A/C " + toAccNumber);
        toAccount.recordTransaction(amount, "TRANSFER_IN", "From A/C " + fromAccNumber);
    }
}
