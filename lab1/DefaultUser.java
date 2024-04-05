public class DefaultUser extends User
{
    public DefaultUser(String name)
    {
        super(name);
    }
    @Override
    public void work(int hours)
    {
        deposit(50 * hours);
    }
}