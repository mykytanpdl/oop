package lab3to5.bank.business.transaction.strategy;

import lab3to5.bank.business.transaction.Transaction;

public class DepositStrategy implements TransactionStrategy
{
    @Override
    public void execute(Transaction transaction)
    {
        transaction.getSourceAccount().deposit(transaction.getAmount());
    }
}
