import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
public class BillPayment implements Transaction
{
    private User payer;
    private double amount;
    private String purchase;
    private Date date;
    private Scanner sc = new Scanner(System.in);
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
            for (int i = 0; i < 3; i++)
            {
                System.out.println("Enter your password to proceed: ");
                String pw = sc.next();
                if (payer.bank.validPassword(payer, pw))
                    if (execute())
                    {
                        System.out.println("Paid successfully!");
                        payer.bank.recordTransaction(this);
                        break;
                    }
                else System.out.println("Invalid password. Try again.");
            }
        }
        else System.out.println("Invalid input. Transaction cancelled.");
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
