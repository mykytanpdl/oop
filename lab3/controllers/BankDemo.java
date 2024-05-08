package com.banknew.controllers;

import com.banknew.business.*;
import com.banknew.business.account.Account;
import com.banknew.business.account.AccountService;
import com.banknew.business.account.User;
import com.banknew.business.enums.*;
import com.banknew.business.loan.LoanService;
import com.banknew.business.transaction.Transaction;
import com.banknew.business.transaction.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.format.DateTimeFormatter;

public class BankDemo
{
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Bank2Application.class, args);

        // Get the services from the Spring context
        AccountService accountService = context.getBean(AccountService.class);
        TransactionService transactionService = context.getBean(TransactionService.class);
        LoanService loanService = context.getBean(LoanService.class);

        // Create users
        User user1 = new User();
        user1.setUsername("John");
        User user2 = new User();
        user2.setUsername("Jane");

        // Create accounts
        Account account1 = accountService.createAccount("Savings", user1);
        Account account2 = accountService.createAccount("Checking", user2);

        // Deposit money into accounts
        transactionService.makeTransaction(account1, account1, 1000, TransactionType.DEPOSIT);
        transactionService.makeTransaction(account2, account2, 500, TransactionType.DEPOSIT);

        // Withdraw money from accounts
        transactionService.makeTransaction(account1, account1, 200, TransactionType.WITHDRAWAL);
        transactionService.makeTransaction(account2, account2, 100, TransactionType.WITHDRAWAL);

        // Create a transaction from account1 to account2
        transactionService.makeTransaction(account1, account2, 200, TransactionType.TRANSFER);

        // Apply for a loan
        transactionService.makeTransaction(account1, account1, 5000, TransactionType.LOAN_APPLICATION);
        System.out.println(account1.getLoans().get(0).getLoanId() + " " + account1.getLoans().get(0).getStatus());
        // Print the loan status
        System.out.println("Loan status of John: " + account1.getLoans().getFirst().getStatus());

        // Make a loan repayment
        transactionService.makeTransaction(account1, account1, 1000, TransactionType.LOAN_REPAYMENT);

        // Print the balances
        System.out.println("Balance of John: " + accountService.inquireBalance(account1));
        System.out.println("Balance of Jane: " + accountService.inquireBalance(account2));

        // Print the transaction history
        System.out.println("Transaction history of John: ");
        System.out.printf("%-20s %-20s %-15s %-20s%n", "Transaction ID", "Transaction Type", "Amount", "Timestamp");
        for (Transaction t : transactionService.retrieveTransactionHistory(account1)) {
            System.out.printf("%-20s %-20s %-15.2f %-20s%n", t.getTransactionId(), t.getTransactionType(), t.getAmount(), t.getTimestamp().format(formatter));
        }
        System.out.println("Transaction history of Jane: ");
        System.out.printf("%-20s %-20s %-15s %-20s%n", "Transaction ID", "Transaction Type", "Amount", "Timestamp");
        for (Transaction t : transactionService.retrieveTransactionHistory(account2)) {
            System.out.printf("%-20s %-20s %-15.2f %-20s%n", t.getTransactionId(), t.getTransactionType(), t.getAmount(), t.getTimestamp().format(formatter));
        }
    }
}