package com.banknew.controllers;

import com.banknew.business.account.Account;
import com.banknew.business.account.AccountService;
import com.banknew.business.transaction.Transaction;
import com.banknew.business.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public BankController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PostMapping("/makeTransaction")
    public Transaction makeTransaction(@RequestBody Transaction transaction) {
        return transactionService.makeTransaction(transaction.getSourceAccount(), transaction.getDestinationAccount(), transaction.getAmount(), transaction.getTransactionType());
    }

    @GetMapping("/transactionHistory/{accountId}")
    public List<Transaction> retrieveTransactionHistory(@PathVariable String accountId) {
        Account account = accountService.findAccountById(accountId);
        return transactionService.retrieveTransactionHistory(account);
    }
}