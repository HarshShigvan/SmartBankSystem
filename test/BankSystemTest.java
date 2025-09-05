package com.smartbank.test;

import com.smartbank.service.BankSystem;
import com.smartbank.model.Account;
import com.smartbank.exceptions.AccountNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankSystemTest {
    private BankSystem bank = new BankSystem();

    @Test
    public void testRegister() {
        Account account = bank.register("Test User", "1234", 100.0, "savings");
        assertNotNull(account);
        assertEquals("Test User", account.getName());
    }

    @Test
    public void testLogin() throws AccountNotFoundException {
        Account account = bank.register("Test User", "1234", 100.0, "savings");
        Account loggedIn = bank.login(account.getAccountNumber(), "1234");
        assertNotNull(loggedIn);
        assertEquals(account.getAccountNumber(), loggedIn.getAccountNumber());
    }

    @Test(expected = AccountNotFoundException.class)
    public void testLoginInvalid() throws AccountNotFoundException {
        bank.login(9999, "1234");
    }
}
