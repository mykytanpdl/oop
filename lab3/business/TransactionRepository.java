package com.banknew.business;

import java.util.List;

public interface TransactionRepository
{
    public Transaction save(Transaction transaction);
    public List<Transaction> findByAccount(Account account);
}
