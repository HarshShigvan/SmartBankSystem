package com.smartbank;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.smartbank.exceptions.AccountNotFoundException;
import com.smartbank.exceptions.InsufficientFundsException;
import com.smartbank.model.Account;
import com.smartbank.model.SavingsAccount;
import com.smartbank.service.BankSystem;

public class Main {
    private static BankSystem bank = new BankSystem();
    private static Account currentAccount = null;

    public static void main(String[] args) {
        printBanner();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                if (currentAccount == null) {
                    showLoginMenu(sc);
                } else {
                    showDashboardMenu(sc);
                }
            }
        }
    }

    private static void showLoginMenu(Scanner sc) {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Register New Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
        int choice = getIntInput(sc);

        switch (choice) {
            case 1: handleRegistration(sc); break;
            case 2: handleLogin(sc); break;
            case 3: System.out.println("\nThank you for using Smart Bank!"); System.exit(0);
            default: System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void showDashboardMenu(Scanner sc) {
        System.out.println("\n--- Dashboard ---");
        System.out.println("Welcome " + currentAccount.getName() + " (A/C: " + currentAccount.getAccountNumber() + ")");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Funds");
        System.out.println("5. View Mini Statement");
        if (currentAccount instanceof SavingsAccount) System.out.println("6. Apply Interest");
        System.out.println("7. Logout");
        System.out.print("Choice: ");
        int choice = getIntInput(sc);

        try {
            switch (choice) {
                case 1: System.out.println("Current Balance: ₹" + String.format("%.2f", currentAccount.getBalance())); break;
                case 2: handleDeposit(sc); break;
                case 3: handleWithdrawal(sc); break;
                case 4: handleTransfer(sc); break;
                case 5: currentAccount.printMiniStatement(); break;
                case 6: if (currentAccount instanceof SavingsAccount) ((SavingsAccount) currentAccount).applyInterest(); else System.out.println("Invalid option."); break;
                case 7: currentAccount = null; System.out.println("Logged out successfully."); break;
                default: System.out.println("Invalid option.");
            }
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleRegistration(Scanner sc) {
        System.out.print("Enter Full Name: ");
        String name = sc.nextLine();
        System.out.print("Set a 4-digit PIN: ");
        String pin = sc.nextLine();
        System.out.print("Initial Deposit Amount: ");
        double deposit = getDoubleInput(sc);
        System.out.print("Account Type (savings/current): ");
        String type = sc.nextLine();

        currentAccount = bank.register(name, pin, deposit, type);
        if (currentAccount == null) {
            System.out.println("Registration failed. Please specify a valid account type (savings/current).");
        }
    }

    private static void handleLogin(Scanner sc) {
        try {
            System.out.print("Enter Account Number: ");
            int accNo = getIntInput(sc);
            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();
            currentAccount = bank.login(accNo, pin);
            if (currentAccount == null) {
                System.out.println("Login failed. Invalid Account Number or PIN.");
            }
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleDeposit(Scanner sc) {
        System.out.print("Amount to deposit: ");
        double amount = getDoubleInput(sc);
        currentAccount.deposit(amount);
    }

    private static void handleWithdrawal(Scanner sc) {
        System.out.print("Amount to withdraw: ");
        double amount = getDoubleInput(sc);
        currentAccount.withdraw(amount);
    }

    private static void handleTransfer(Scanner sc) {
        System.out.print("Recipient's Account Number: ");
        int toAcc = getIntInput(sc);
        System.out.print("Amount to transfer: ");
        double amount = getDoubleInput(sc);
        bank.transfer(currentAccount.getAccountNumber(), toAcc, amount);
        System.out.println("Transfer successful.");
    }

    private static int getIntInput(Scanner sc) {
        try {
            int value = sc.nextInt();
            sc.nextLine(); // consume newline
            return value;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // clear the invalid input
            return -1; // Indicate error
        }
    }

    private static double getDoubleInput(Scanner sc) {
        try {
            double value = sc.nextDouble();
            sc.nextLine(); // consume newline
            return value;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.nextLine(); // clear the invalid input
            return -1.0; // Indicate error
        }
    }

    private static void printBanner() {
        try {
            String banner = new String(Files.readAllBytes(Paths.get("resources/banner.txt")));
            System.out.println(banner);
        } catch (Exception e) {
            // Fallback if banner file is not found
            System.out.println("\n--- Smart Bank System ---");
        }
    }
}
