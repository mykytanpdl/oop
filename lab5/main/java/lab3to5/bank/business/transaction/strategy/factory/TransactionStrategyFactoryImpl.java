package lab3to5.bank.business.transaction.strategy.factory;

import lab3to5.bank.business.enums.TransactionType;
import lab3to5.bank.business.loan.LoanService;
import lab3to5.bank.business.transaction.strategy.*;
import lab3to5.bank.business.transaction.strategy.TransactionStrategy;
import org.springframework.stereotype.Component;

@Component
public class TransactionStrategyFactoryImpl implements TransactionStrategyFactory {
    private final LoanService loanService;

    public TransactionStrategyFactoryImpl(LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public TransactionStrategy createStrategy(TransactionType type) {
        return switch (type) {
            case DEPOSIT -> new DepositStrategy();
            case WITHDRAWAL -> new WithdrawStrategy();
            case TRANSFER -> new TransferStrategy();
            case LOAN_REPAYMENT -> new LoanRepaymentStrategy(loanService);
            case LOAN_APPLICATION -> new LoanApplicationStrategy(loanService);
            default -> throw new IllegalArgumentException("Invalid transaction type");
        };
    }
}