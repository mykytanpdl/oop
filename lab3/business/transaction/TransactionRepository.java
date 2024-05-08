package com.banknew.business.transaction;

import com.banknew.business.account.Account;

import java.util.List;

public interface TransactionRepository
{
    public Transaction save(Transaction transaction);
    public List<Transaction> findByAccount(Account account);
}
