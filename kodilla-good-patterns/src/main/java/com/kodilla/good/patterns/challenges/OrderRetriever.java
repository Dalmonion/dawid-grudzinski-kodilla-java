package com.kodilla.good.patterns.challenges;

public class OrderRetriever {


    public OrderRequest retrieve() {
        User user = new User("admin1", 200.0);
        Item item1 = new Item("LCD", 199.0);

        return new OrderRequest(user, item1);
    }


}
