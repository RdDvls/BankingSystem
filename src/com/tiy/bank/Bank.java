package com.tiy.bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Bank {

    private String bankName = "Bank Strickland";
    ArrayList<Customer> accountHolders = new ArrayList<Customer>();
    Scanner inputScanner = new Scanner(System.in);

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void createAccount() {
        int accountChoice;
        double initialDeposit;
        int index = 1;
        System.out.println("Please select a customer:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        for (Customer currentCustomer : accountHolders) {
            System.out.println(index + ": " + currentCustomer.getUserName());
            index++;
        }
        int customerChoice = Integer.valueOf(inputScanner.nextLine());
        Customer myCustomer = accountHolders.get(customerChoice-1);


        while (true) {
            System.out.println("Please choose an account to open\n");
            System.out.println("1.Checking");
            System.out.println("2.Savings");
            System.out.println("3.Retirement");
            System.out.println("========");
            System.out.println("0.Exit");
            System.out.println("========");
            accountChoice = Integer.valueOf(inputScanner.nextLine());
            if (accountChoice == 1) {
                System.out.println("Please name your Checking account");
                String acctName = inputScanner.nextLine();
                System.out.println("How much money would you like to put into your account?\n");
                initialDeposit = Double.valueOf(inputScanner.nextLine());
                myCustomer.customerAccounts.put(acctName, new Checking(acctName, initialDeposit,1));
                System.out.println(myCustomer.customerAccounts.get(acctName).getBalance());

            } else if (accountChoice == 2) {
                System.out.println("Please name your Savings account");
                String acctName = inputScanner.nextLine();
                System.out.println("How much money would you like to put into your account?");
                initialDeposit = Double.valueOf(inputScanner.nextLine());
                myCustomer.customerAccounts.put(acctName, new Savings(acctName, initialDeposit, 2));
                System.out.println(myCustomer.customerAccounts.get(acctName).getBalance());

            } else if (accountChoice == 3) {
                System.out.println("Please name your Retirement account");
                String acctName = inputScanner.nextLine();
                System.out.println("How much money would you like to put into your account?");
                initialDeposit = Double.valueOf(inputScanner.nextLine());
                myCustomer.customerAccounts.put(acctName, new Retirement(acctName, initialDeposit, 3));
                System.out.println(myCustomer.customerAccounts.get(acctName).getBalance());

            }else if(accountChoice == 0){
                break;
            }
        }
    }


    public void addCustomer() {
        System.out.println("Please enter your first name:");
        String userName = inputScanner.nextLine();
        Customer myCustomer = new Customer(userName);
        accountHolders.add(myCustomer);
    }

    public double getTotalInDeposits() {
        double total = 0.0;
        for (Customer myCustomer : accountHolders) {
            for (HashMap.Entry<String, BankAccount> currAcct : myCustomer.customerAccounts.entrySet()) {
                total += currAcct.getValue().getBalance();
            }
        }
        return total;
    }

    public void printInfo() {
        int counter = 1;
        System.out.println(getBankName());
        for (Customer customer : accountHolders){
            System.out.println(customer.getUserName());
        }

        for (Customer myCustomer : accountHolders) {
            for (HashMap.Entry<String, BankAccount> currAcct : myCustomer.customerAccounts.entrySet()) {
                System.out.println(counter++ + " - " + currAcct.getKey() + " : " + currAcct.getValue().getBalance());
            }
        }

    }


    public void accountInteract(){

        while (true) {
            int index = 1;
            System.out.println("Sign-In\n```````");
            for (Customer currentCustomer : accountHolders) {
                System.out.println(index + ": " + currentCustomer.getUserName());
                index++;
            }
            int customerChoice = Integer.valueOf(inputScanner.nextLine());
            Customer myCustomer = accountHolders.get(customerChoice-1);

            System.out.println("Which account would you like to access?");
            System.out.println("Type \"exit\" to exit\n");
            for(String accountName : myCustomer.customerAccounts.keySet()) {
                System.out.println(accountName);
            }

            String userChoice = inputScanner.nextLine();
            System.out.println("You chose " + userChoice);
            if (userChoice == null || userChoice.equals("exit")) {
                break;
            }

            BankAccount userChosenAccount = myCustomer.customerAccounts.get(userChoice);
            if(userChosenAccount == null) {
                System.out.println("Try again");
            } else {
                while(true) {
                    System.out.println("What would you like to do?");
                    System.out.println("1.Deposit");
                    System.out.println("2.Withdraw");
                    System.out.println("3.Print Info");
                    System.out.println("4.Transfer between accounts");
                    System.out.println("========");
                    System.out.println("0.Exit");
                    int bankingChoice = Integer.valueOf(inputScanner.nextLine());
                    if(bankingChoice == 1) {
                        System.out.println("How much would you like to deposit?\n");
                        double deposit = Double.valueOf(inputScanner.nextLine());
                        userChosenAccount.deposit(deposit);
                    } else if(bankingChoice == 2) {
                        System.out.println("How much would you like to withdraw?\n");
                        double withdraw = Double.valueOf(inputScanner.nextLine());
                        userChosenAccount.withdraw(withdraw);
                    }else if(bankingChoice == 3) {
                        userChosenAccount.printInfo();
                    }else if(bankingChoice == 4) {
                        System.out.println("How much would you like to transfer?\n");
                        double toTransfer = Double.valueOf(inputScanner.nextLine());
                        System.out.println("Which account would you like to transfer to?\n");
                        for (String accountName : myCustomer.customerAccounts.keySet()) {
                            System.out.println(accountName);
                        }
                        String transferChoice = inputScanner.nextLine();
                        System.out.println("We will transfer to " + transferChoice);
                        BankAccount transferAccount = myCustomer.customerAccounts.get(transferChoice);
                        if (transferAccount == null) {
                            System.out.println("No such account exist\n");
                            break;
                        } else
                            userChosenAccount.withdraw(toTransfer);
                        transferAccount.deposit(toTransfer);
                        System.out.println("Transfer complete \n");
                    }
                    else if(bankingChoice == 0) {
                        break;

                    }
                }
            }
        }
    }

    public void writeBank (Bank bank) {
        FileWriter bankWriter = null;
        FileWriter custWriter = null;
        try {
            File bankFile = new File("bank.txt");
            File accountFile = new File("customer-name-accounts.txt");
            bankWriter = new FileWriter(bankFile);
            for (Customer myCustomers : accountHolders ){
                bankWriter.write(myCustomers.getUserName() + ",");
                File customerFile = new File(myCustomers.getUserName() + ".txt");
                custWriter = new FileWriter(customerFile);
                for (HashMap.Entry<String, BankAccount> currAcct: myCustomers.customerAccounts.entrySet()){
                    custWriter.write("account.name=" + currAcct.getKey() + "\n" + "account.currentBalance=" + currAcct.getValue().getBalance() + "\n" + "account.type=" + currAcct.getValue().getType() + "\n");

                }
                custWriter.close();
            }
            bankWriter.close();
        } catch (Exception exception){
            System.out.println("Bank Error.");
            exception.printStackTrace();
        } finally {
            if(bankWriter != null) {
                try {
                    bankWriter.close();
                } catch (Exception ex){
                    System.out.println("Bank Error");
                    ex.printStackTrace();
                }
            }
        }
    }

    public void readBank(ArrayList<Customer> accountHolders) {
        try {
            HashMap<String, BankAccount> customerAccounts;//  = new HashMap<String, BankAccount>();
            File bankFile = new File("bank.txt");
            Scanner fileScanner = new Scanner(bankFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                String[] currName = nextLine.split(",");
                for (String currentName : currName) {
                    Customer myCustomer = new Customer(currentName);
                    customerAccounts = new HashMap<>();
                    accountHolders.add(myCustomer);
                    File accountFile = new File(currentName + ".txt");
                    Scanner accountScanner = new Scanner(accountFile);
                    while(accountScanner.hasNext()) {
                        String accountName = accountScanner.nextLine().split("=")[1];
                        double balance = Double.valueOf(accountScanner.nextLine().split("=")[1]);
                        int type = Integer.valueOf(accountScanner.nextLine().split("=")[1]);
                        if (type == 1) {
                            BankAccount myAccount = new Checking(accountName, balance, type);
                            customerAccounts.put(accountName, myAccount);
                        } else if (type == 2) {
                            BankAccount myAccount = new Savings(accountName, balance, type);
                            customerAccounts.put(accountName, myAccount);
                        } else if (type == 3) {
                            BankAccount myAccount = new Retirement(accountName, balance, type);
                            customerAccounts.put(accountName, myAccount);
                        }
                        System.out.println("Recovered account!");
                    }
                    myCustomer.setCustomerAccounts(customerAccounts);
                }
            }

        } catch (IOException IoEx){
            System.out.println("No Files in System");
            IoEx.printStackTrace();
        }

    }
}