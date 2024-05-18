package lab3to5.bank.controllers;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.account.AccountService;
import lab3to5.bank.business.transaction.Transaction;
import lab3to5.bank.business.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (transaction.getAmount() <= 0 || transaction.getTransactionType() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (transaction.getSourceAccount() != null) {
            Account account = accountService.findAccountById(transaction.getSourceAccount().getAccountId());
            if (account == null) {
                return ResponseEntity.notFound().build();
            }
            transaction.setSourceAccount(account);
        }
        if (transaction.getDestinationAccount() != null) {
            Account account = accountService.findAccountById(transaction.getDestinationAccount().getAccountId());
            if (account == null) {
                return ResponseEntity.notFound().build();
            }
            transaction.setDestinationAccount(account);
        }
        Transaction createdTransaction = transactionService.makeTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
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