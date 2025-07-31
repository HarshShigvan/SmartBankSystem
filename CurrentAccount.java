public class CurrentAccount extends Account {
    private static final double OVERDRAFT = 1000.0;

    public CurrentAccount(String name, String pin, double deposit) {
        super(name, pin, deposit);
    }

   
    public void withdraw(double amount) {
        if (amount <= balance + OVERDRAFT) {
            balance -= amount;
            recordTransaction("Withdrawn ₹" + amount);
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }
}
