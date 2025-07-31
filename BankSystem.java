import java.util.HashMap;

public class BankSystem {
    private HashMap<Integer, Account> accounts = new HashMap<>();

    public Account register(String name, String pin, double deposit, String type) {
        Account acc;
        if (type.equalsIgnoreCase("savings")) {
            acc = new SavingsAccount(name, pin, deposit);
        } else if (type.equalsIgnoreCase("current")) {
            acc = new CurrentAccount(name, pin, deposit);
        } else {
            System.out.println("Invalid type.");
            return null;
        }
        accounts.put(acc.getAccountNumber(), acc);
        System.out.println("Account created. A/C No: " + acc.getAccountNumber());
        return acc;
    }

    public Account login(int accNo, String pin) {
        Account acc = accounts.get(accNo);
        if (acc != null && acc.verifyPin(pin)) {
            return acc;
        }
        return null;
    }

    public void transfer(int from, int to, double amt) {
        Account a1 = accounts.get(from);
        Account a2 = accounts.get(to);
        if (a1 != null && a2 != null && a1.getBalance() >= amt) {
            a1.withdraw(amt);
            a2.deposit(amt);
            a1.recordTransaction("Transferred ₹" + amt + " to A/C " + to);
            a2.recordTransaction("Received ₹" + amt + " from A/C " + from);
        } else {
            System.out.println("Transfer failed.");
        }
    }
}
