package lab3to5.bank.business.loan;

import lab3to5.bank.business.account.Account;
import lab3to5.bank.business.model.Repository;

import java.util.List;

public interface LoanRepository extends Repository<Loan, String>
{
    public List<Loan> findByBorrower(Account account);

}
