package lab3to5.bank.business.transaction.strategy;

import lab3to5.bank.business.transaction.Transaction;
import lab3to5.bank.business.loan.LoanService;

public class LoanApplicationStrategy implements TransactionStrategy {
    private final LoanService loanService;

    public LoanApplicationStrategy(LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public void execute(Transaction transaction) {
        loanService.applyForLoan(transaction.getSourceAccount(), transaction.getAmount(), 0.05, 12);
    }
}