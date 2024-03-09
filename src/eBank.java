import java.util.Scanner;
public interface eBank
{
    Scanner sc = new Scanner(System.in);

    User newUser(String name, String password);
    User newAdmin(String name, String password);
    User newAdmin(User user);
    void removeUser(User user);
    int getUsersCount();
    void logIn(User user);
    void logOut(User user);
    void recordTransaction(Transaction tr);
    void printLastTransactions(int number, User requester);
    void printLastTransactions(User requester);
    boolean validPassword(User user, String password);
}
