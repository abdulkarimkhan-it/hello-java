import java.util.*;
import java.io.*;

class Account implements Serializable {
      private String accountNumber;
      private String name;
      private double balance;

      public Account(String accountNumber, String name, double balance) {
            this.accountNumber = accountNumber;
            this.name = name;
            this.balance = balance;
      }

      public String getAccountNumber() {
            return accountNumber;
      }

      public String getName() {
            return name;
      }

      public double getBalance() {
            return balance;
      }

      public void deposit(double amount) {
            balance += amount;
            System.out.println("Amount Deposited Successfully!");
      }

      public void withdraw(double amount) {
            if (amount <= balance) {
                  balance -= amount;
                  System.out.println("Withdrawal Successful!");
            } else {
                  System.out.println("Insufficient Balance!");
            }
      }

      public void display() {
            System.out.println("Account No: " + accountNumber);
            System.out.println("Name: " + name);
            System.out.println("Balance: " + balance);
      }
}

public class BankingSystem {

      static ArrayList<Account> accounts = new ArrayList<>();
      static final String FILE_NAME = "accounts.dat";

      public static void main(String[] args) {
            loadAccounts();
            Scanner sc = new Scanner(System.in);

            while (true) {
                  System.out.println("\n===== BANKING SYSTEM =====");
                  System.out.println("1. Create Account");
                  System.out.println("2. Deposit");
                  System.out.println("3. Withdraw");
                  System.out.println("4. Display Account");
                  System.out.println("5. Exit");
                  System.out.print("Choose Option: ");

                  int choice = sc.nextInt();

                  switch (choice) {
                        case 1:
                              createAccount(sc);
                              break;
                        case 2:
                              deposit(sc);
                              break;
                        case 3:
                              withdraw(sc);
                              break;
                        case 4:
                              displayAccount(sc);
                              break;
                        case 5:
                              saveAccounts();
                              System.out.println("Data Saved. Exiting...");
                              System.exit(0);
                        default:
                              System.out.println("Invalid Option!");
                  }
            }
      }

      static void createAccount(Scanner sc) {
            System.out.print("Enter Account Number: ");
            String accNo = sc.next();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();

            accounts.add(new Account(accNo, name, balance));
            System.out.println("Account Created Successfully!");
      }

      static Account findAccount(String accNo) {
            for (Account acc : accounts) {
                  if (acc.getAccountNumber().equals(accNo)) {
                        return acc;
                  }
            }
            return null;
      }

      static void deposit(Scanner sc) {
            System.out.print("Enter Account Number: ");
            String accNo = sc.next();
            Account acc = findAccount(accNo);

            if (acc != null) {
                  System.out.print("Enter Amount: ");
                  double amount = sc.nextDouble();
                  acc.deposit(amount);
            } else {
                  System.out.println("Account Not Found!");
            }
      }

      static void withdraw(Scanner sc) {
            System.out.print("Enter Account Number: ");
            String accNo = sc.next();
            Account acc = findAccount(accNo);

            if (acc != null) {
                  System.out.print("Enter Amount: ");
                  double amount = sc.nextDouble();
                  acc.withdraw(amount);
            } else {
                  System.out.println("Account Not Found!");
            }
      }

      static void displayAccount(Scanner sc) {
            System.out.print("Enter Account Number: ");
            String accNo = sc.next();
            Account acc = findAccount(accNo);

            if (acc != null) {
                  acc.display();
            } else {
                  System.out.println("Account Not Found!");
            }
      }

      static void saveAccounts() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                  oos.writeObject(accounts);
            } catch (IOException e) {
                  System.out.println("Error Saving Data!");
            }
      }

      static void loadAccounts() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                  accounts = (ArrayList<Account>) ois.readObject();
            } catch (Exception e) {
                  accounts = new ArrayList<>();
            }
      }
}
