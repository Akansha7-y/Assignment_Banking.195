import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private String email;
    private String phoneNumber;
    private double balance;

    public Account(int accountNumber, String accountHolderName, String email, String phoneNumber,double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = initialDeposit;
        
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount. Amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Balance: " + balance);
        
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }
}

public class Banking{
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;
    private int nextAccountNumber;

    public Banking() {
        accounts = new Account[100]; 
        accountCount = 0;
        scanner = new Scanner(System.in);
        nextAccountNumber = 1001; 
    }

    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        double initialDeposit = 0;
        while (true) {
            System.out.print("Enter initial deposit amount: ");
            String depositInput = scanner.nextLine();
            try {
                initialDeposit = Double.parseDouble(depositInput);
                if (initialDeposit < 0) {
                    System.out.println("Initial deposit cannot be negative. Please enter again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        Account newAccount = new Account(nextAccountNumber, name,email, phone, initialDeposit);
        accounts[accountCount] = newAccount;
        accountCount++;
        System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
        nextAccountNumber++;
    }

    private Account findAccountByNumber(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = readIntInput();
        Account acc = findAccountByNumber(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = readDoubleInput();
        acc.deposit(amount);
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = readIntInput();
        Account acc = findAccountByNumber(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = readDoubleInput();
        acc.withdraw(amount);
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = readIntInput();
        Account acc = findAccountByNumber(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        acc.displayAccountDetails();
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = readIntInput();
        Account acc = findAccountByNumber(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter new email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        acc.updateContactDetails(email, phone);
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\nWelcome!");
            System.out.println("1. Create a new account");
            System.out.println("2. View account details");
            System.out.println("3. Deposit money");
            System.out.println("4. Withdraw money");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    showAccountDetails();
                    
                    break;
                case 3:
                    performDeposit();
                    
                    break;
                case 4:
                    performWithdrawal();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using this Application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private int readIntInput() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int val = Integer.parseInt(input);
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    private double readDoubleInput() {
        while (true) {
            String input = scanner.nextLine();
            try {
                double val = Double.parseDouble(input);
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public static void main(String[] args) {
        Banking ui = new Banking();
        ui.mainMenu();
    }
}
