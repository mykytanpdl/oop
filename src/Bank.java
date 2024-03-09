import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bank implements eBank
{
    private final String name;
    private int usersCount;
    private Map<String, User> users;
    private Map<User, String> passwords;
//    private Transaction transaction;
    public List<Transaction> transactionList;

    public Bank(String name)
    {
        this.name = name;
        usersCount = 0;
        users = new HashMap<>();
        passwords = new HashMap<>();
        transactionList = new ArrayList<>();
    }

    @Override
    public User newUser(String name, String password)
    {
        User user = new DefaultUser(name);
        user.bank = this;
        users.put(user.getAccountNumber(), user);
        passwords.put(user, password);
        user.logged = true;
        usersCount++;
        return user;
    }
    @Override
    public User newAdmin(String name, String password)
    {
        User user = new Admin(name);
        user.bank = this;
        users.put(user.getAccountNumber(), user);
        passwords.put(user, password);
        user.logged = true;
        usersCount++;
        return user;
    }

    @Override
    public User newAdmin(User user)
    {
        if (!(user instanceof Admin)) {
            User admin = newAdmin(user.getName(), passwords.get(user));
            admin.deposit(user.getBalance());
            removeUser(user);
            return admin;
        }
        else
            return user;
    }

    @Override
    public void removeUser(User user)
    {
        users.remove(user.getAccountNumber());
        passwords.remove(user);
        usersCount--;
    }

    @Override
    public int getUsersCount()
    {
        return usersCount;
    }

    @Override
    public void logIn(User user)
    {
        while(!user.logged)
        {
            System.out.println("Enter your password:             (Press TAB to go back)");
            String password = sc.next();
            if (Objects.equals(password, passwords.get(user)))
                user.logged = true;
            else if (Objects.equals(password, "\t"))
                break;
            else System.out.println("Wrong password. Try again");
        }
    }

    @Override
    public void logOut(User user)
    {
        System.out.println("Are you sure you want to log out?               (type 'y' to confirm)");
        String input = sc.next();
        if (Objects.equals(input, "y"))
            user.logged = false;
    }

    @Override
    public void recordTransaction(Transaction tr)
    {
        transactionList.add(tr);
    }

    @Override
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
    @Override
    public void printLastTransactions(User requester)
    {
        printLastTransactions(this.transactionList.size(), requester);
    }

    public boolean validPW(User user, String password)
    {
        return Objects.equals(passwords.get(user), password);
    }
}
