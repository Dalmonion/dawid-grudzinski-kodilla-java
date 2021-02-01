package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderItemService implements OrderService {

    public boolean order(User user, Item item) {
        return user.getBalance() >= item.getPrice();
    }
}
