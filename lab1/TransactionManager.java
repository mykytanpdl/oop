import java.util.List;
import java.util.ArrayList;
public class TransactionManager
{
    public List<Transaction> transactionList;

    public TransactionManager()
    {
        this.transactionList = new ArrayList<>();
    }
    public void recordTransaction(Transaction tr)
    {
        transactionList.add(tr);
    }

    public void printLastTransactions(int number, User requester)
    {
        if (requester instanceof Admin)
        {
            for (int i = 0; i < number && i < this.transactionList.size(); i++)
            {
                this.transactionList.get(i).showInfo();
            }
        }
        else
            System.out.println("Only administrators can access transactions!");
    }
    public void printLastTransactions(User requester)
    {
        printLastTransactions(this.transactionList.size(), requester);
    }
}
