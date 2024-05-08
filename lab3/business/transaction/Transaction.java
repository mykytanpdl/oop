package com.banknew.business.transaction;

import com.banknew.business.account.Account;
import com.banknew.business.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private static int transactionCounter = 1;
    private String transactionId;
    private LocalDateTime timestamp;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
    private TransactionType transactionType;

    public Transaction() {
        this.transactionId = String.format("TR%06d", transactionCounter++);
    }

    public Transaction(Account sourceAccount, Account destinationAccount, double amount, TransactionType transactionType) {
        this.transactionId = String.format("TR%06d", transactionCounter++);
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType)
    {
        this.transactionType = transactionType;
    }
}