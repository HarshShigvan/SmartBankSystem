import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Account {
    private static int counter = 1000;
    protected int accountNumber;
    protected String name;
    protected String pin;
    protected double balance;
    protected ArrayList<String> transactions;

    public Account(String name, String pin, double deposit) {
        this.accountNumber = ++counter;
        this.name = name;
        this.pin = pin;
        this.balance = deposit;
        this.transactions = new ArrayList<>();
        recordTransaction("Account created with ₹" + deposit);
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

    public void deposit(double amount) {
        balance += amount;
        recordTransaction("Deposited ₹" + amount);
    }

    public abstract void withdraw(double amount);

    public void recordTransaction(String message) {
        if (transactions.size() == 5) transactions.remove(0);
        transactions.add(LocalDateTime.now() + " - " + message);
    }

    public void printMiniStatement() {
        System.out.println("Mini Statement for A/C " + accountNumber + ":");
        for (String tx : transactions) {
            System.out.println(tx);
        }
    }
}
