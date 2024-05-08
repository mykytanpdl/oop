package com.banknew.business.loan;

import com.banknew.business.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanList implements LoanRepository
{
    private final List<Loan> loans = new ArrayList<>();

    public void save(Loan loan) {
        if (!loans.contains(loan)) {
            loans.add(loan);
        }
    }

    public List<Loan> findByBorrower(Account account) {
        return loans.stream()
                .filter(loan -> loan.getBorrower().equals(account))
                .collect(Collectors.toList());
    }

    public Loan findById(String loanId) {
        return loans.stream()
                .filter(loan -> loan.getLoanId().equals(loanId))
                .findFirst()
                .orElse(null);
    }
}
