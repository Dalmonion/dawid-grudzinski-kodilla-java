package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRetriever {


    public OrderRequest retrieve() {
        User user = new User("admin1", 200.0);
        Item item1 = new Item("LCD", 199.0);
        log.info("Customer has been created");

        return new OrderRequest(user, item1);
    }


}
