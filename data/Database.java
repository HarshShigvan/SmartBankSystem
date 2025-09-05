package com.smartbank.data;

import com.smartbank.exceptions.AccountNotFoundException;
import com.smartbank.model.Account;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A singleton class simulating an in-memory database for accounts.
 */
public class Database {
    private static final Database INSTANCE = new Database();
    private final Map<Integer, Account> accounts = new ConcurrentHashMap<>();

    private Database() {}

    public static Database getInstance() {
        return INSTANCE;
    }

    public void saveAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccountById(int accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber))
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + accountNumber + " not found."));
    }
}