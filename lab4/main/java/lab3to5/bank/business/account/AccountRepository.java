package lab3to5.bank.business.account;

import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    Account findById(String id);
    List<Account> findAll();
}