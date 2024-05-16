package com.banknew.business.account;

import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    Account findById(String id);
    List<Account> findAll();
}