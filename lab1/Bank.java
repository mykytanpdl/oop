public class Bank implements eBank
{
    private final String name;
    private final UserManager userManager;
    private final TransactionManager transactionManager;

    public Bank(String name)
    {
        this.name = name;
        this.userManager = new UserManager(this);
        this.transactionManager = new TransactionManager();
    }

    public String getName()
    {
        return name;
    }

    public User newUser(String name, String password)
    {
        return userManager.newUser(name, password);
    }

    public User newAdmin(String name, String password)
    {
        return userManager.newAdmin(name, password);
    }
    public User newAdmin(User user)
    {
        return userManager.newAdmin(user);
    }
    public void removeUser(User user)
    {
        userManager.removeUser(user);
    }
    public int getUsersCount()
    {
        return userManager.getUsersCount();
    }
    public void logIn(User user)
    {
        userManager.logIn(user);
    }
    public void logOut(User user)
    {
        userManager.logOut(user);
    }
    public boolean validPassword(User user, String password)
    {
        return userManager.validPassword(user, password);
    }


    public void recordTransaction(Transaction tr)
    {
        transactionManager.recordTransaction(tr);
    }

    public void printLastTransactions(User requester)
    {
        transactionManager.printLastTransactions(requester);
    }
    public void printLastTransactions(int number, User requester)
    {
        transactionManager.printLastTransactions(number, requester);
    }
}
