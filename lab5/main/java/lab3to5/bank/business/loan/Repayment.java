package lab3to5.bank.business.loan;

import lab3to5.bank.business.enums.RepaymentStatus;

import java.time.LocalDate;

public class Repayment
{
    private final String repaymentId;
    private final Loan loan;
    private double amount;
    private LocalDate dueDate;
    private RepaymentStatus status;

    public Repayment(Loan loan, double amount) {
        this.repaymentId = "RP" + System.currentTimeMillis();
        this.loan = loan;
        this.amount = amount;
        this.dueDate = LocalDate.now().plusMonths(1);
        this.status = RepaymentStatus.DUE;
    }



    public String getRepaymentId()
    {
        return repaymentId;
    }
    public Loan getLoan() {
        return loan;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public RepaymentStatus getStatus() {
        return status;
    }
    public void setStatus(RepaymentStatus status) {
        this.status = status;
    }
}
