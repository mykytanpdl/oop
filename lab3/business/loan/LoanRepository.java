package com.banknew.business.loan;

import com.banknew.business.Account;

import java.util.List;

public interface LoanRepository
{
    public void save(Loan loan);
    public List<Loan> findByBorrower(Account account);
    public Loan findById(String loan);
}
