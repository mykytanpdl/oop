package lab3to5.bank.business.loan;

import java.time.LocalDate;
import java.util.List;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.enums.LoanStatus;
import lab3to5.bank.business.enums.RepaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService
{
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository repo) {
        this.loanRepository = repo;
    }

    public void applyForLoan(Account borrower, double amount, double interestRate, int duration) {
        Loan loan = new Loan(borrower, amount, interestRate, duration);
        borrower.deposit(amount);
        loanRepository.save(loan);

        approveLoan(loan);
    }

    private void approveLoan(Loan loan) {
        loan.setStatus(LoanStatus.APPROVED);
        loan.setAmount(loan.getAmount() + calculateInterest(loan));
        loanRepository.save(loan);
    }

    public void rejectLoan(Loan loan) {
        loan.setStatus(LoanStatus.REJECTED);
        loanRepository.save(loan);
    }

    public void makeRepayment(Repayment repayment) {
        Loan loan = repayment.getLoan();
        applyLatePaymentPenalty(repayment);
        if (loan.getBorrower().withdraw(repayment.getAmount()))
        {
            repayment.setStatus(RepaymentStatus.PAID);
            loan.setAmount(loan.getAmount() - repayment.getAmount());
            if (loan.getAmount() <= 0) {
                loan.setStatus(LoanStatus.PAID);
                loan.getBorrower().getLoans().remove(loan);
            }
        }

        loanRepository.save(loan);
    }

    public Loan retrieveLoan(String loanId) {
        return loanRepository.findById(loanId);
    }

    public List<Loan> retrieveLoans(Account borrower)
    {
        return loanRepository.findByBorrower(borrower);
    }


    private double calculateInterest(Loan loan) {
        double principal = loan.getAmount();
        double rate = loan.getInterestRate();
        int time = loan.getDuration();

        return (principal * rate * time) / 100;
    }

    private void applyLatePaymentPenalty(Repayment repayment)
    {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(repayment.getDueDate()) && repayment.getStatus() == RepaymentStatus.DUE) {
            double penalty = repayment.getAmount() * 0.1;
            Loan loan = repayment.getLoan();
            loan.setAmount(loan.getAmount() + penalty);
            loanRepository.save(loan);
        }
    }
}