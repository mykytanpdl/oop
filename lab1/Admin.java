public class Admin extends DefaultUser
{
    Admin(String name)
    {
        super(name);
    }
    @Override
    public void work(int hours)
    {
        deposit(100 * hours);
    }
}
