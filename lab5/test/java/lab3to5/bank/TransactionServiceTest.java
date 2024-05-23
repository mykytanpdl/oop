package lab3to5.bank;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.enums.TransactionType;
import lab3to5.bank.business.loan.Loan;
import lab3to5.bank.business.loan.Repayment;
import lab3to5.bank.business.transaction.Transaction;
import lab3to5.bank.business.transaction.TransactionRepository;
import lab3to5.bank.business.transaction.TransactionService;
import lab3to5.bank.business.loan.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    LoanService loanService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void depositIncreasesAccountBalance() {
        Account account = new Account();
        account.setBalance(1000.0);

        Transaction transaction = new Transaction(account, null, 500.0, TransactionType.DEPOSIT);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.makeTransaction(transaction);

        assertEquals(1500.0, result.getSourceAccount().getBalance());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void withdrawalDecreasesAccountBalance() {
        Account account = new Account();
        account.setBalance(1000.0);

        Transaction transaction = new Transaction(account, null, 500.0, TransactionType.WITHDRAWAL);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.makeTransaction(transaction);

        assertEquals(500.0, result.getSourceAccount().getBalance());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void transferDecreasesSourceAndIncreasesDestinationAccountBalance() {
        Account sourceAccount = new Account();
        sourceAccount.setBalance(1000.0);

        Account destinationAccount = new Account();
        destinationAccount.setBalance(500.0);

        Transaction transaction = new Transaction(sourceAccount, destinationAccount, 200.0, TransactionType.TRANSFER);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.makeTransaction(transaction);

        assertEquals(800.0, result.getSourceAccount().getBalance());
        assertEquals(700.0, result.getDestinationAccount().getBalance());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void loanRepaymentDecreasesLoanAmount() {
        Account account = new Account();
        account.setBalance(1000.0);

        Loan loan = new Loan(account, 500.0, 0.05, 12);
        account.addLoan(loan);

        Transaction transaction = new Transaction(account, null, 200.0, TransactionType.LOAN_REPAYMENT);

        doAnswer(invocation -> {
            Repayment repayment = invocation.getArgument(0);
            Loan loan1 = repayment.getLoan();
            loan1.setAmount(loan1.getAmount() - repayment.getAmount());
            return null;
        }).when(loanService).makeRepayment(any(Repayment.class));

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.makeTransaction(transaction);

        assertEquals(300.0, loan.getAmount());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void loanApplicationIncreasesAccountBalance() {
        Account account = new Account();
        account.setBalance(1000.0);

        Transaction transaction = new Transaction(account, null, 500.0, TransactionType.LOAN_APPLICATION);

        doAnswer(invocation -> {
            Account account1 = invocation.getArgument(0);
            double amount = invocation.getArgument(1);
            account1.deposit(amount);
            return null;
        }).when(loanService).applyForLoan(any(Account.class), anyDouble(), anyDouble(), anyInt());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.makeTransaction(transaction);

        assertEquals(1500.0, account.getBalance());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void retrieveTransactionHistoryReturnsCorrectTransactions() {
        Account account = new Account();
        account.setBalance(1000.0);

        Transaction transaction1 = new Transaction(account, null, 500.0, TransactionType.DEPOSIT);
        Transaction transaction2 = new Transaction(account, null, 200.0, TransactionType.WITHDRAWAL);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByAccount(account)).thenReturn(transactions);

        List<Transaction> result = transactionService.retrieveTransactionHistory(account);

        assertEquals(transactions, result);
        verify(transactionRepository, times(1)).findByAccount(account);
    }
}