package lab3to5.bank.business.loan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.enums.LoanStatus;

public class Loan {
    private final String loanId;
    private double amount;
    private double interestRate;
    private int duration;
    @JsonBackReference
    private final Account borrower;
    private LoanStatus status;

    public Loan(Account borrower, double amount, double interestRate, int duration)
    {
        this.loanId = "LN" + System.currentTimeMillis();
        this.borrower = borrower;
        this.amount = amount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.status = LoanStatus.APPLIED;
        borrower.addLoan(this);
    }

    public String getLoanId() {
        return loanId;
    }
    public Account getBorrower() {
        return borrower;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}