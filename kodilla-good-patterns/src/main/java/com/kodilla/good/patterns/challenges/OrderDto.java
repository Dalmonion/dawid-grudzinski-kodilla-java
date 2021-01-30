package com.kodilla.good.patterns.challenges;

public class OrderDto {

    private User user;
    private  boolean wasPurchased;

    public OrderDto(final User user, final boolean wasPurchased) {
        this.user = user;
        this.wasPurchased = wasPurchased;
    }

    public User getUser() {
        return user;
    }

    public boolean wasPurchased() {
        return wasPurchased;
    }
}
