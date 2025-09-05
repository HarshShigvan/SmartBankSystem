package com.smartbank.test;

import com.smartbank.model.SavingsAccount;
import com.smartbank.exceptions.InsufficientFundsException;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
    @Test
    public void testDeposit() {
        SavingsAccount account = new SavingsAccount("Test", "1234", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdraw() throws InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Test", "1234", 100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance(), 0.01);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawInsufficientFunds() throws InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Test", "1234", 100.0);
        account.withdraw(150.0);
    }
}
