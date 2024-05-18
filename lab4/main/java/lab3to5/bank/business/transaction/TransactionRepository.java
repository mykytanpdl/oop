package lab3to5.bank.business.transaction;

import lab3to5.bank.business.account.Account;
import java.util.List;

public interface TransactionRepository
{
    public Transaction save(Transaction transaction);
    public List<Transaction> findByAccount(Account account);
}
