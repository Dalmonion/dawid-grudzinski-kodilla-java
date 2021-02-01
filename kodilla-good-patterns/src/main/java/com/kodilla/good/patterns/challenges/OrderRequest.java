package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRequest {

    private User user;
    private Item item;

    public OrderRequest(final User user, final Item item) {
        this.user = user;
        this.item = item;
        log.info("OrderRequest for item \"" + item.getName() + "\" from user " + user.getUserName() + " has been created.");
    }

    public User getUser() {
        return user;
    }

    public Item getItem() {
        return item;
    }
}
