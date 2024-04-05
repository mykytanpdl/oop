import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
public class MoneyTransfer implements Transaction
{
    private User sender;
    private User receiver;
    private double amount;
    private Date date;
    private Scanner sc = new Scanner(System.in);
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
        else
            System.out.println("Invalid input. Transaction cancelled.");
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
