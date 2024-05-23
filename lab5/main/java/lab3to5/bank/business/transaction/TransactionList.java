package lab3to5.bank.business.transaction;

import lab3to5.bank.business.account.Account;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionList implements TransactionRepository
{
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public Transaction save(Transaction transaction) {
        if (!transactions.contains(transaction))
            transactions.add(transaction);
        return transaction;
    }

    @Override
    public Transaction findById(String transactionId) {
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Transaction transaction) {
        transactions.remove(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        int index = transactions.indexOf(transaction);
        if (index != -1) {
            transactions.set(index, transaction);
        }
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> findByAccount(Account account) {
        return transactions.stream()
                .filter(transaction -> (transaction.getSourceAccount() != null && transaction.getSourceAccount().equals(account))
                        || (transaction.getDestinationAccount() != null && transaction.getDestinationAccount().equals(account)))
                .collect(Collectors.toList());
    }
}