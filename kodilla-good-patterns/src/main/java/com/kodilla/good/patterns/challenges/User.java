package com.kodilla.good.patterns.challenges;

public class User {
    private String userName;
    private double balance;

    public User(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }
}
