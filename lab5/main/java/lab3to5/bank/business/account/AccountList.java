package lab3to5.bank.business.account;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
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
    public void delete(Account account) {
        accounts.remove(account);
    }

    @Override
    public void update(Account account) {
        int index = accounts.indexOf(account);
        if (index != -1) {
            accounts.set(index, account);
        }
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts);
    }
}