import java.util.Scanner;

// Class to represent a user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the balance
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Method to check the current balance
    public double getBalance() {
        return balance;
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;

    // Constructor to initialize the ATM with a user's bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the ATM menu
    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    // Method to perform a transaction based on user's choice
    public void performTransaction(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                deposit(scanner);
                break;
            case 3:
                withdraw(scanner);
                break;
            case 4:
                System.out.println("Exiting. Thank you for using the ATM.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to check the balance
    private void checkBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    // Method to handle deposit
    private void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // Method to handle withdrawal
    private void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a bank account with an initial balance of 1000
        BankAccount account = new BankAccount(1000);
        
        // Create an ATM object connected to the user's bank account
        ATM atm = new ATM(account);
        
        int choice = 0;
        while (choice != 4) {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            atm.performTransaction(choice, scanner);
        }

        scanner.close();
    }
}
