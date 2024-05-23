package lab3to5.bank.business.loan;


import lab3to5.bank.business.account.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LoanList implements LoanRepository
{
    private final List<Loan> loans = new ArrayList<>();

    @Override
    public Loan save(Loan loan) {
        if (!loans.contains(loan)) {
            loans.add(loan);
        }
        return loan;
    }

    @Override
    public Loan findById(String loanId) {
        return loans.stream()
                .filter(loan -> loan.getLoanId().equals(loanId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public void update(Loan loan) {
        int index = loans.indexOf(loan);
        if (index != -1) {
            loans.set(index, loan);
        }
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(loans);
    }


    public List<Loan> findByBorrower(Account account) {
        return loans.stream()
                .filter(loan -> loan.getBorrower().equals(account))
                .collect(Collectors.toList());
    }
}
