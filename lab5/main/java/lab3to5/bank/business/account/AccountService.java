package lab3to5.bank.business.account;

import lab3to5.bank.business.transaction.Transaction;
import lab3to5.bank.business.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    private AccountService(TransactionRepository trRepo, AccountRepository accRepo)
    {
        this.transactionRepository = trRepo;
        this.accountRepository = accRepo;
    }

    public Account createAccount(Account account) {
        if (accountRepository.findById(account.getAccountId()) != null) {
            return null;
        }
        return accountRepository.save(account);
    }
    public Account createAccount(String accountType, User owner) {
        Account account = new Account(accountType, 0, owner);
        return accountRepository.save(account);
    }

    public double inquireBalance(Account account) {
        return account.getBalance();
    }

    public List<Transaction> retrieveTransactionHistory(Account account) {
        return transactionRepository.findByAccount(account);
    }

    public Account findAccountById(String id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
}