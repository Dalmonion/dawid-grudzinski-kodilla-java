package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemBuyRepository implements BuyRepository {
    @Override
    public void createOrder(User user, Item item) {
      log.info("Order created in repository.");
    }
}
