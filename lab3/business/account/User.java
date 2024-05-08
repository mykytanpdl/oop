package com.banknew.business.account;

import com.banknew.business.account.Account;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private final List<Account> accounts = new ArrayList<>();

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<Account> getAccounts() {
        return accounts;
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }
}