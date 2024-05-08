package com.banknew.business.transaction;

import com.banknew.business.account.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionList implements TransactionRepository
{
    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        if (!transactions.contains(transaction))
            transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findByAccount(Account account) {
        return transactions.stream()
                .filter(transaction -> transaction.getSourceAccount().equals(account) || transaction.getDestinationAccount().equals(account))
                .collect(Collectors.toList());
    }
}