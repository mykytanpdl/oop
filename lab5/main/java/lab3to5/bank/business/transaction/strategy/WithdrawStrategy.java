package lab3to5.bank.business.transaction.strategy;

import lab3to5.bank.business.transaction.Transaction;

public class WithdrawStrategy implements TransactionStrategy
{
    public void execute(Transaction transaction)
    {
        transaction.getSourceAccount().withdraw(transaction.getAmount());
    }
}
