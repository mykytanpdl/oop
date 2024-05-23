package lab3to5.bank.business.transaction.strategy;

import lab3to5.bank.business.loan.LoanService;
import lab3to5.bank.business.loan.Repayment;
import lab3to5.bank.business.transaction.Transaction;
public class LoanRepaymentStrategy implements TransactionStrategy {
    private final LoanService loanService;

    public LoanRepaymentStrategy(LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public void execute(Transaction transaction) {
        loanService.makeRepayment(new Repayment(transaction.getSourceAccount().getLoans().get(0), transaction.getAmount()));
    }
}