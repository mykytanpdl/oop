package lab3to5.bank.business.transaction;

import lab3to5.bank.business.loan.*;
import lab3to5.bank.business.account.*;
import lab3to5.bank.business.enums.TransactionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final LoanService loanService;

    @Autowired
    private TransactionService(TransactionRepository repo, LoanService loanService)
    {
        this.transactionRepository = repo;
        this.loanService = loanService;
    }

    public Transaction makeTransaction(Transaction transaction)
    {
        switch (transaction.getTransactionType()) {
            case DEPOSIT:
                transaction.getSourceAccount().deposit(transaction.getAmount());
                break;
            case WITHDRAWAL:
                transaction.getSourceAccount().withdraw(transaction.getAmount());
                break;
            case TRANSFER:
                transaction.getSourceAccount().transfer(transaction.getDestinationAccount(), transaction.getAmount());
                break;
            case LOAN_REPAYMENT:
                loanService.makeRepayment(new Repayment(transaction.getSourceAccount().getLoans().get(0), transaction.getAmount()));
                break;
            case LOAN_APPLICATION:
                loanService.applyForLoan(transaction.getSourceAccount(), transaction.getAmount(), 0.05, 12);
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }
        return transactionRepository.save(transaction);
    }

    public List<Transaction> retrieveTransactionHistory(Account account)
    {
        return transactionRepository.findByAccount(account);
    }
}