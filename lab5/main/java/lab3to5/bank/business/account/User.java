package lab3to5.bank.business.account;

public class User {
    private String username;
    private String password;
    private String email;

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean register(String username, String password, String email) {
        if (this.username.equals(username) || this.email.equals(email)) {
            return false;
        }
        this.username = username;
        this.password = password;
        this.email = email;
        return true;
    }

    public boolean updateProfile(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        return true;
    }
}