public class Main
{
    public static void main(String[] args)
    {
        eBank bank = new Bank("LPBank");
        User user1 = bank.newUser("Oleh Kovalenko", "123456");
        User user2 = bank.newUser("Maksym Bilyi", "102030");
        System.out.println("There are " + bank.getUsersCount() + " users in the bank");
        bank.logOut(user1);
        bank.logIn(user1);

        user2.work(1);
        User admin2 = bank.newAdmin(user2);

        user1.work(8);
        admin2.work(1);
        Transaction MTransfer = new MoneyTransfer(user1, admin2, 170);
        MTransfer.confirm();
        bank.printLastTransactions(user1);
        user1.printBalance();
        admin2.printBalance();

        Transaction Payment = new BillPayment(admin2, "Cheeseburger", 80);
        Payment.confirm();
        bank.printLastTransactions(admin2);

        user1.printBalance();
        admin2.printBalance();
    }
}