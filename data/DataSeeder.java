package com.smartbank.data;

import com.smartbank.model.SavingsAccount;
import com.smartbank.model.CurrentAccount;

public class DataSeeder {
    private final Database database = Database.getInstance();

    public void seedData() {
        // Seed some sample accounts
        SavingsAccount savings = new SavingsAccount("John Doe", "1234", 1000.0);
        CurrentAccount current = new CurrentAccount("Jane Smith", "5678", 500.0);

        database.saveAccount(savings);
        database.saveAccount(current);

        System.out.println("Sample data seeded.");
    }
}
