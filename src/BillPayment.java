import java.util.Date;
import java.util.Objects;
public class BillPayment implements Transaction
{
    User payer;
    double amount;
    String purchase;
    Date date;

    public BillPayment(User payer, String purchase, double amount)
    {
        this.payer = payer;
        this.purchase = purchase;
        this.amount = amount;
    }

    @Override
    public void confirm()
    {
        System.out.println("Are you sure you want to pay " + this.amount
                + " UAH for " + this.purchase + "?      (type 'yes' to confirm)");
        String response = sc.next();
        if (Objects.equals(response, "yes"))
        {
            System.out.println("Enter your password to proceed: ");
            String pw = sc.next();
            if (payer.bank.validPW(payer, pw))
            {
                if (execute())
                {
                    System.out.println("Paid successfully!");
                    payer.bank.recordTransaction(this);
                }
            }
        }
    }
    private boolean execute()
    {
        if (this.payer.withdraw(this.amount))
        {
            date = new Date();
            return true;
        }
        else return false;
    }

    @Override
    public void showInfo()
    {
        System.out.println(this.amount + " UAH\tfrom " + this.payer.getName() + "\tfor\t"
                + this.purchase + "\ton\t" + this.date);
    }
}
