import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();
        Scanner sc = new Scanner(System.in);
        Account current = null;

        while (true) {
            if (current == null) {
                System.out.println("\n--- Smart Bank ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                int ch = sc.nextInt();

                if (ch == 1) {
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Set PIN: ");
                    String pin = sc.next();
                    System.out.print("Initial Deposit: ");
                    double dep = sc.nextDouble();
                    System.out.print("Type (savings/current): ");
                    String type = sc.next();
                    current = bank.register(name, pin, dep, type);
                } else if (ch == 2) {
                    System.out.print("A/C No: ");
                    int acc = sc.nextInt();
                    System.out.print("PIN: ");
                    String pin = sc.next();
                    current = bank.login(acc, pin);
                    if (current != null)
                        System.out.println("Welcome " + current.getName());
                    else
                        System.out.println("Login failed.");
                } else if (ch == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("\n--- Dashboard ---");
                System.out.println("1. Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Statement");
                System.out.println("6. Interest (Savings only)");
                System.out.println("7. Logout");
                System.out.print("Choice: ");
                int op = sc.nextInt();

                if (op == 1) {
                    System.out.println("₹" + current.getBalance());
                } else if (op == 2) {
                    System.out.print("Amount: ");
                    current.deposit(sc.nextDouble());
                } else if (op == 3) {
                    System.out.print("Amount: ");
                    current.withdraw(sc.nextDouble());
                } else if (op == 4) {
                    System.out.print("To A/C No: ");
                    int to = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    bank.transfer(current.getAccountNumber(), to, amt);
                } else if (op == 5) {
                    current.printMiniStatement();
                } else if (op == 6) {
                    if (current instanceof SavingsAccount) {
                        ((SavingsAccount) current).applyInterest();
                    } else {
                        System.out.println("Only for savings accounts.");
                    }
                } else if (op == 7) {
                    current = null;
                } else {
                    System.out.println("Invalid.");
                }
            }
        }

        System.out.println("Thank you!");
    }
}
