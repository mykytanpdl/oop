package com.banknew.business.account;

import com.banknew.business.loan.Loan;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private static int id = 1;
    private final String accountId;
    private String accountType;
    private double balance;
    private User owner;
    private final List<Loan> loans = new ArrayList<>();

    public Account()
    {
        this.accountId = String.format("AC%06d", id++);
    }

    public Account(String accountType, double balance, User owner) {
        this.accountId = String.format("AC%06d", id++);
        this.accountType = accountType;
        this.balance = balance;
        this.owner = owner;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public User getOwner() {
        return owner;
    }

    public void deposit(double amount) {
        // Implement deposit logic here
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        // Implement withdrawal logic here
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void transfer(Account destinationAccount, double amount) {
        if (this.balance >= amount) {
            this.withdraw(amount);
            destinationAccount.deposit(amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void addLoan(Loan loan)
    {
        loans.add(loan);
    }

    public double inquireBalance() {
        return this.balance;
    }

    public List<Loan> getLoans()
    {
        return loans;
    }

}