package lab3to5.bank.business.transaction.strategy;

import lab3to5.bank.business.transaction.Transaction;

public interface TransactionStrategy
{
    void execute(Transaction transaction);
}
