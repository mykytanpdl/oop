package com.banknew.business.account;

import com.banknew.business.Account;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    Account findById(String id);
    List<Account> findAll();
}