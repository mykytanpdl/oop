public interface eBank
{
    String getName();
    User newUser(String name, String password);
    User newAdmin(String name, String password);
    User newAdmin(User user);
    void removeUser(User user);
    int getUsersCount();
    void logIn(User user);
    void logOut(User user);
    boolean validPassword(User user, String password);
    void recordTransaction(Transaction tr);
    void printLastTransactions(User requester);
    void printLastTransactions(int number, User requester);
}