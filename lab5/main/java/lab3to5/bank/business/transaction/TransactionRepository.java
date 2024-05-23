package lab3to5.bank.business.transaction;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.model.Repository;

import java.util.List;

public interface TransactionRepository extends Repository<Transaction, String>
{
    public List<Transaction> findByAccount(Account account);
}
