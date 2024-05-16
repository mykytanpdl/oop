package com.banknew.business;
import com.banknew.business.account.Account;
import com.banknew.business.account.AccountRepository;
import com.banknew.business.account.AccountService;
import com.banknew.business.account.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAccount() {
        String accountType = "Savings";

        accountService.createAccount(accountType, user);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testCreateAccountWithNullType() {
        String accountType = null;

        accountService.createAccount(accountType, user);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testCreateAccountWithEmptyType() {
        String accountType = "";

        accountService.createAccount(accountType, user);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testCreateAccountWithNullUser() {
        String accountType = "Savings";
        User nullUser = null;

        accountService.createAccount(accountType, nullUser);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testCreateAccountWithAllNull() {
        String accountType = null;
        User nullUser = null;

        accountService.createAccount(accountType, nullUser);

        verify(accountRepository, times(1)).save(any(Account.class));
    }
}