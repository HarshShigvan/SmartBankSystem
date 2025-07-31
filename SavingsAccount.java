public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.03;

    public SavingsAccount(String name, String pin, double deposit) {
        super(name, pin, deposit);
    }

    
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            recordTransaction("Withdrawn ₹" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        recordTransaction("Interest added ₹" + interest);
    }
}
