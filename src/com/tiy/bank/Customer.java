package com.tiy.bank;

import java.util.HashMap;

public class Customer {
    private String userName;
    HashMap<String, BankAccount> customerAccounts = new HashMap<String, BankAccount>();

    public Customer(String userName) {
        this.userName = userName;
    }

    public HashMap<String, BankAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(HashMap<String, BankAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}