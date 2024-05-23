package lab3to5.bank.controllers;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.account.AccountService;
import lab3to5.bank.business.transaction.Transaction;
import lab3to5.bank.business.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/bank/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

@PostMapping
public ResponseEntity<Transaction> makeTransaction(@RequestBody Transaction transaction) {
    try {
        Transaction createdTransaction = transactionService.createTransaction(transaction, accountService);
        return ResponseEntity.ok(createdTransaction);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    } catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> retrieveTransactionHistory(@PathVariable String accountId) {
        Account account = accountService.findAccountById(accountId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactionService.retrieveTransactionHistory(account));
    }
}