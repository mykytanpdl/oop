package com.banknew.business.account;

import org.springframework.stereotype.Component;
import com.banknew.business.Account;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountList implements AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account save(Account account) {
        accounts.add(account);
        return account;
    }

    @Override
    public Account findById(String id) {
        return accounts.stream()
                .filter(account -> account.getAccountId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }
}