public class Account
{
    final String number;
    double balance;

    public Account(String name)
    {
        this.number = generateAccountNumber(name);
        this.balance = 0;
    }

    private String generateAccountNumber(String name)
    {
        return Integer.toString(name.hashCode());
    }
}

