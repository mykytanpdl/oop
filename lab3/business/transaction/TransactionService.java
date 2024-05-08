package com.banknew.business.transaction;

import com.banknew.business.account.Account;
import com.banknew.business.enums.TransactionType;
import com.banknew.business.loan.LoanService;
import com.banknew.business.loan.Repayment;
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

    public void makeTransaction(Account sourceAccount, Account destinationAccount, double amount, TransactionType transactionType)
    {
        Transaction transaction = new Transaction(sourceAccount, destinationAccount, amount, transactionType);

        switch (transactionType) {
            case DEPOSIT:
                sourceAccount.deposit(amount);
                break;
            case WITHDRAWAL:
                sourceAccount.withdraw(amount);
                break;
            case TRANSFER:
                sourceAccount.transfer(destinationAccount, amount);
                break;
            case LOAN_REPAYMENT:
                // Assuming you have a method in LoanService to handle loan repayment
                loanService.makeRepayment(new Repayment(sourceAccount.getLoans().get(0), amount));
                break;
            case LOAN_APPLICATION:
                // Assuming you have a method in LoanService to apply for a loan
                loanService.applyForLoan(sourceAccount, amount, 0.05, 12);
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }
        transactionRepository.save(transaction);
    }

    public List<Transaction> retrieveTransactionHistory(Account account)
    {
        return transactionRepository.findByAccount(account);
    }
}