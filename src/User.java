public abstract class User
{
    private final String name;
    private String email;
    private String phoneNumber;
    public eBank bank;
    private final Account account;
    boolean logged;


    User(String name)
    {
        this.name = name;
        account = new Account(name);
        logged = false;
    }

    String getName()
    {
        return this.name;
    }
    String getEmail()
    {
        return this.email;
    }
    void setEmail(String email)
    {
        this.email = email;
    }
    String getPhoneNum()
    {
        return this.phoneNumber;
    }
    void setPhoneNum(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    String getAccountNumber()
    {
        return account.number;
    }
    double getBalance()
    {
        return account.balance;
    }
    abstract void work(int hours);
    void deposit(double money)
    {
        account.balance += money;
    }
    boolean withdraw(double money)
    {
        if (account.balance >= money)
        {
            account.balance -= money;
            return true;
        }
        else
        {
            System.out.println("Insufficient funds on the " + this.name + "'s account");
            return false;
        }
    }
}