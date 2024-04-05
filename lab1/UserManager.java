import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class UserManager
{
    private int usersCount;
    private final Map<String, User> users;
    private final Map<User, String> passwords;
    private final eBank bank;
    private Scanner sc = new Scanner(System.in);
    public UserManager(eBank bank)
    {
        usersCount = 0;
        users = new HashMap<>();
        passwords = new HashMap<>();
        this.bank = bank;
    }
    private User createUser(User user, String password)
    {
        user.bank = this.bank;
        users.put(user.getAccountNumber(), user);
        passwords.put(user, password);
        user.logged = true;
        usersCount++;
        return user;
    }
    public User newUser(String name, String password)
    {
        User user = new DefaultUser(name);
        return createUser(user, password);
    }
    public User newAdmin(String name, String password)
    {
        User user = new Admin(name);
        return createUser(user, password);
    }

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

    public void removeUser(User user)
    {
        users.remove(user.getAccountNumber());
        passwords.remove(user);
        usersCount--;
    }

    public int getUsersCount()
    {
        return usersCount;
    }

    public void logIn(User user)
    {
        int attempts = 0;
        while(!user.logged && attempts < 3)
        {
            System.out.println("Enter your password:                    (Press TAB to go back)");
            String password = sc.next();
            if (Objects.equals(password, passwords.get(user)))
                user.logged = true;
            else if (Objects.equals(password, "\t"))
                break;
            else
            {
                System.out.println("Wrong password. Try again");
                attempts++;
            }
        }
        if (attempts == 3)
            System.out.println("Too many failed attempts. Please try again later.");
    }

    public void logOut(User user)
    {
        System.out.println("Are you sure you want to log out?               (type 'yes' to confirm)");
        String input = sc.next();
        if (Objects.equals(input, "yes"))
            user.logged = false;
    }
    public boolean validPassword(User user, String password)
    {
        return Objects.equals(password, passwords.get(user));
    }
}
