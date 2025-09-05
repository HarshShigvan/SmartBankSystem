# 💳 SmartBankSystem

A modular and professional Java-based Banking System built with Object-Oriented Programming principles. This project simulates core banking functionalities through a command-line interface (CLI).

---

## 🚀 Features

- Create new bank accounts (Savings or Current)
- Login to your account
- Deposit and Withdraw money
- Transfer funds between accounts
- View transaction history
- Interest calculation (for savings accounts)
- Overdraft handling (for current accounts)

---

## 📁 Project Structure

The project is organized into a clean, modular structure to promote separation of concerns and maintainability.

```
SmartBankSystem/
├── src/
│   ├── com/
│   │   └── smartbank/
│   │       ├── data/
│   │       │   └── Database.java
│   │       ├── exceptions/
│   │       │   ├── AccountNotFoundException.java
│   │       │   └── InsufficientFundsException.java
│   │       ├── model/
│   │       │   ├── Account.java
│   │       │   ├── SavingsAccount.java
│   │       │   ├── CurrentAccount.java
│   │       │   └── Transaction.java
│   │       ├── service/
│   │       │   └── BankSystem.java
│   │       ├── test/
│   │       │   ├── AccountTest.java
│   │       │   └── BankSystemTest.java
│   │       ├── utils/
│   │       │   └── ConfigLoader.java
│   │       └── Main.java
│   └── resources/
│       ├── banner.txt
│       └── config.properties
└── README.md
```

### Package Descriptions

-   **`com.smartbank`**: The root package for the application.
    -   **`data`**: Simulates the data access layer. `Database.java` acts as an in-memory store for account objects.
    -   **`exceptions`**: Contains custom exceptions to handle specific error scenarios like an account not being found or having insufficient funds.
    -   **`model`**: The core domain objects (POJOs) of the application, representing accounts and transactions.
    -   **`service`**: The business logic layer. `BankSystem.java` orchestrates operations like registration, login, and transfers.
    -   **`utils`**: Utility classes for handling cross-cutting concerns like loading configuration.
    -   **`test`**: Contains JUnit tests for ensuring the correctness of model and service classes.
-   **`resources`**: External configuration and resource files, such as `config.properties` for application settings and `banner.txt` for the welcome message.

---

## ⚙️ How to Run

1.  **Compile the code:**
    Navigate to the `src` directory and run the following command. Make sure the `resources` directory is accessible from this location.
    ```bash
    javac com/smartbank/*.java com/smartbank/data/*.java com/smartbank/exceptions/*.java com/smartbank/model/*.java com/smartbank/service/*.java com/smartbank/utils/*.java
    ```

2.  **Run the application:**
    From the `src` directory, run the `Main` class:
    ```bash
    java com.smartbank.Main
    ```

The application will start, and you can interact with it through the command-line menu.
