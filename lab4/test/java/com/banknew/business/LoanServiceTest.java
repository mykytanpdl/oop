package com.banknew.business;
import com.banknew.business.account.Account;
import com.banknew.business.enums.RepaymentStatus;
import com.banknew.business.loan.Loan;
import com.banknew.business.loan.LoanRepository;
import com.banknew.business.loan.LoanService;
import com.banknew.business.loan.Repayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private Account account;

    @Mock
    private Loan loan;

    @Mock
    private Repayment repayment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApplyForLoan() {
        double amount = 5000.0;
        double interestRate = 0.05;
        int duration = 12;

        loanService.applyForLoan(account, amount, interestRate, duration);

        verify(account, times(1)).deposit(amount);
        verify(loanRepository, times(2)).save(any(Loan.class));
    }

    @Test
    public void testApplyForLoanWithZeroAmount() {
        double amount = 0.0;
        double interestRate = 0.05;
        int duration = 12;

        loanService.applyForLoan(account, amount, interestRate, duration);

        verify(account, times(1)).deposit(amount);
        verify(loanRepository, times(2)).save(any(Loan.class));
    }

    @Test
    public void testApplyForLoanWithNegativeAmount() {
        double amount = -5000.0;
        double interestRate = 0.05;
        int duration = 12;

        loanService.applyForLoan(account, amount, interestRate, duration);

        verify(account, times(1)).deposit(amount);
        verify(loanRepository, times(2)).save(any(Loan.class));
    }

    @Test
    public void testApplyForLoanWithZeroInterestRate() {
        double amount = 5000.0;
        double interestRate = 0.0;
        int duration = 12;

        loanService.applyForLoan(account, amount, interestRate, duration);

        verify(account, times(1)).deposit(amount);
        verify(loanRepository, times(2)).save(any(Loan.class));
    }

    @Test
    public void testApplyForLoanWithZeroDuration() {
        double amount = 5000.0;
        double interestRate = 0.05;
        int duration = 0;

        loanService.applyForLoan(account, amount, interestRate, duration);

        verify(account, times(1)).deposit(amount);
        verify(loanRepository, times(2)).save(any(Loan.class));
    }


    @Test
    public void testMakeRepayment() {
        when(repayment.getLoan()).thenReturn(loan);
        when(loan.getBorrower()).thenReturn(account);
        when(account.withdraw(anyDouble())).thenReturn(true);

        loanService.makeRepayment(repayment);

        verify(account, times(1)).withdraw(anyDouble());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    public void testMakeRepaymentWithInsufficientBalance() {
        when(repayment.getLoan()).thenReturn(loan);
        when(loan.getBorrower()).thenReturn(account);
        when(account.withdraw(anyDouble())).thenReturn(false);

        loanService.makeRepayment(repayment);

        verify(account, times(1)).withdraw(anyDouble());
        verify(loanRepository, times(0)).save(any(Loan.class));
    }

    @Test
    public void testMakeRepaymentWithLatePayment() {
        when(repayment.getLoan()).thenReturn(loan);
        when(loan.getBorrower()).thenReturn(account);
        when(account.withdraw(anyDouble())).thenReturn(true);
        when(repayment.getStatus()).thenReturn(RepaymentStatus.DUE);
        when(repayment.getDueDate()).thenReturn(LocalDate.now().minusDays(1));

        loanService.makeRepayment(repayment);

        verify(account, times(1)).withdraw(anyDouble());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    public void testMakeRepaymentWithOnTimePayment() {
        when(repayment.getLoan()).thenReturn(loan);
        when(loan.getBorrower()).thenReturn(account);
        when(account.withdraw(anyDouble())).thenReturn(true);
        when(repayment.getStatus()).thenReturn(RepaymentStatus.DUE);
        when(repayment.getDueDate()).thenReturn(LocalDate.now().plusDays(1));

        loanService.makeRepayment(repayment);

        verify(account, times(1)).withdraw(anyDouble());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    public void testMakeRepaymentWithPaidLoan() {
        when(repayment.getLoan()).thenReturn(loan);
        when(loan.getBorrower()).thenReturn(account);
        when(account.withdraw(anyDouble())).thenReturn(true);
        when(repayment.getStatus()).thenReturn(RepaymentStatus.PAID);

        loanService.makeRepayment(repayment);

        verify(account, times(1)).withdraw(anyDouble());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }
}