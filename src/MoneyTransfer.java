import java.util.Date;
import java.util.Objects;
public class MoneyTransfer implements Transaction
{
    User sender;
    User receiver;
    double amount;
    Date date;
    MoneyTransfer(User sender, User receiver, double amount)
    {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    @Override
    public void confirm()
    {
        System.out.println("Are you sure you want to send " + this.amount
                        + " UAH to " + receiver.getName() + "?      (type 'yes' to confirm)");
        String response = sc.next();
        if (Objects.equals(response, "yes"))
        {
            if (execute())
            {
                System.out.println("Money transferred successfully");
                sender.bank.recordTransaction(this);
            }
        }
    }

    private boolean execute()
    {
        if (this.sender.withdraw(this.amount))
        {
            this.receiver.deposit(this.amount);
            date = new Date();
            return true;
        }
        else return false;
    }

    @Override
    public void showInfo()
    {
        System.out.println(this.amount + " UAH\tfrom " + this.sender.getName() + "\tto\t"
                            + this.receiver.getName() + "\ton\t" + this.date);
    }
}
