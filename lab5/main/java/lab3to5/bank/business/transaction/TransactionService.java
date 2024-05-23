package lab3to5.bank.business.transaction;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.account.AccountService;
import lab3to5.bank.business.transaction.strategy.TransactionStrategy;
import lab3to5.bank.business.transaction.strategy.factory.TransactionStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionStrategyFactory strategyFactory;

    @Autowired
    private TransactionService(TransactionRepository repo, TransactionStrategyFactory strategyFactory)
    {
        this.transactionRepository = repo;
        this.strategyFactory = strategyFactory;
    }


    public Transaction createTransaction(Transaction transaction, AccountService accountService) {
        if (transaction.getAmount() <= 0 || transaction.getTransactionType() == null) {
            throw new IllegalArgumentException("Invalid transaction data");
        }
        if (transaction.getSourceAccount() != null) {
            Account account = accountService.findAccountById(transaction.getSourceAccount().getAccountId());
            if (account == null) {
                throw new NoSuchElementException("Source account not found");
            }
            transaction.setSourceAccount(account);
        }
        if (transaction.getDestinationAccount() != null) {
            Account account = accountService.findAccountById(transaction.getDestinationAccount().getAccountId());
            if (account == null) {
                throw new NoSuchElementException("Destination account not found");
            }
            transaction.setDestinationAccount(account);
        }
        return makeTransaction(transaction);
    }

    public Transaction makeTransaction(Transaction transaction) {
        TransactionStrategy strategy = strategyFactory.createStrategy(transaction.getTransactionType());
        strategy.execute(transaction);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> retrieveTransactionHistory(Account account) {
        return transactionRepository.findByAccount(account);
    }
}