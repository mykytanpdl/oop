package lab3to5.bank.controllers;

import lab3to5.bank.business.account.*;
import lab3to5.bank.business.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> inquireBalance(@PathVariable String id) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountService.inquireBalance(account));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> retrieveTransactionHistory(@PathVariable String id) {
        Account account = accountService.findAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountService.retrieveTransactionHistory(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable String id) {
        Account account = accountService.findAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }
}