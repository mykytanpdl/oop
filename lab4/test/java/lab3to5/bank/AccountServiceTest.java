package lab3to5.bank;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.account.AccountRepository;
import lab3to5.bank.business.account.AccountService;
import lab3to5.bank.business.transaction.Transaction;
import lab3to5.bank.business.transaction.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Account creation saves account in repository")
    public void accountCreationSavesAccountInRepository() {
        Account account = new Account();
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.createAccount(account);

        assertEquals(account, result);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    @DisplayName("Balance inquiry returns correct balance")
    public void balanceInquiryReturnsCorrectBalance() {
        Account account = new Account();
        account.setBalance(1000.0);

        double result = accountService.inquireBalance(account);

        assertEquals(1000.0, result);
    }

    @Test
    @DisplayName("Transaction history retrieval returns correct transactions")
    public void transactionHistoryRetrievalReturnsCorrectTransactions() {
        Account account = new Account();
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByAccount(account)).thenReturn(transactions);

        List<Transaction> result = accountService.retrieveTransactionHistory(account);

        assertEquals(transactions, result);
        verify(transactionRepository, times(1)).findByAccount(account);
    }

    @Test
    @DisplayName("Account retrieval by ID returns correct account")
    public void accountRetrievalByIdReturnsCorrectAccount() {
        Account account = new Account();
        String id = "123";

        when(accountRepository.findById(id)).thenReturn(account);

        Account result = accountService.findAccountById(id);

        assertEquals(account, result);
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Account retrieval returns all accounts")
    public void accountRetrievalReturnsAllAccounts() {
        Account account1 = new Account();
        Account account2 = new Account();
        List<Account> accounts = Arrays.asList(account1, account2);

        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> result = accountService.findAllAccounts();

        assertEquals(accounts, result);
        verify(accountRepository, times(1)).findAll();
    }
}