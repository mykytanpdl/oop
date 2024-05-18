package lab3to5.bank.business.loan;

import lab3to5.bank.business.account.Account;

import java.util.List;

public interface LoanRepository
{
    public void save(Loan loan);
    public List<Loan> findByBorrower(Account account);
    public Loan findById(String loan);
}
