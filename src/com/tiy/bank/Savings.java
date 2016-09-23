
package com.tiy.bank;


public class Savings extends BankAccount implements Runnable{

    private String accountName;
    private double balance;
    private int type;

    public Savings(String accountName, double balance, int type) {
        this.setAccountName(accountName);
        this.setBalance(balance);
        this.setType(type);

        Thread savingsThread = new Thread(this);
        savingsThread.start();
    }

    public double interest() {
        setBalance(getBalance() * 1.05);
        return getBalance();
    }


    public void run() {
        try {
            System.out.println("Running thread for savings");
            while (Day10Runner.runInterestThread) {
                setBalance(getBalance() * 1.05);
                Thread.sleep(10000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
