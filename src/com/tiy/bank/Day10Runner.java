package com.tiy.bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Day10Runner {
    public static boolean runInterestThread = true;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Bank myBank = new Bank();
        myBank.readBank(myBank.accountHolders);
        System.out.println("\nWelcome to " + myBank.getBankName() + ", how may we assist you?");
        while (true) {
            int menuChoice;
            System.out.println("1.Sign in");
            System.out.println("2.Create an account");
            System.out.println("3.Check Bank info");
            System.out.println("4.Add a customer");
            System.out.println("========");
            System.out.println("0.Exit");
            menuChoice = Integer.valueOf(inputScanner.nextLine());
            if (menuChoice == 1) {
                myBank.accountInteract();
            } else if (menuChoice == 2) {
                myBank.createAccount();
            } else if (menuChoice == 3) {
                myBank.printInfo();
                System.out.println("Total in deposits: " + myBank.getTotalInDeposits());
            } else if (menuChoice == 4) {
                myBank.addCustomer();
            } else if (menuChoice == 0) {
                System.out.println("*************\nThank you for using " + myBank.getBankName() + "!");
                Day10Runner.runInterestThread = false;
                myBank.writeBank(myBank);
                break;
            }


        }
    }

}




