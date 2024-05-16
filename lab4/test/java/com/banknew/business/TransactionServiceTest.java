package com.banknew.business;

import com.banknew.business.account.Account;
import com.banknew.business.enums.TransactionType;
import com.banknew.business.transaction.Transaction;
import com.banknew.business.transaction.TransactionRepository;
import com.banknew.business.transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private Account sourceAccount;

    @Mock
    private Account destinationAccount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMakeDepositTransaction() {
        double amount = 5000.0;

        transactionService.makeTransaction(sourceAccount, sourceAccount, amount, TransactionType.DEPOSIT);

        verify(sourceAccount, times(1)).deposit(amount);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testMakeWithdrawalTransaction() {
        double amount = 2000.0;
        when(sourceAccount.withdraw(amount)).thenReturn(true);

        transactionService.makeTransaction(sourceAccount, sourceAccount, amount, TransactionType.WITHDRAWAL);

        verify(sourceAccount, times(1)).withdraw(amount);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testMakeTransferTransaction() {
        double amount = 2000.0;
        when(sourceAccount.withdraw(amount)).thenReturn(true);

        transactionService.makeTransaction(sourceAccount, destinationAccount, amount, TransactionType.TRANSFER);

        verify(sourceAccount, times(1)).withdraw(amount);
        verify(destinationAccount, times(1)).deposit(amount);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testMakeTransferTransactionWithInsufficientBalance() {
        double amount = 7000.0;
        when(sourceAccount.withdraw(amount)).thenReturn(false);

        transactionService.makeTransaction(sourceAccount, destinationAccount, amount, TransactionType.TRANSFER);

        verify(sourceAccount, times(1)).withdraw(amount);
        verify(destinationAccount, times(0)).deposit(amount);
        verify(transactionRepository, times(0)).save(any(Transaction.class));
    }

    @Test
    public void testMakeWithdrawalTransactionWithInsufficientBalance() {
        double amount = 7000.0;
        when(sourceAccount.withdraw(amount)).thenReturn(false);

        transactionService.makeTransaction(sourceAccount, sourceAccount, amount, TransactionType.WITHDRAWAL);

        verify(sourceAccount, times(1)).withdraw(amount);
        verify(transactionRepository, times(0)).save(any(Transaction.class));
    }
}