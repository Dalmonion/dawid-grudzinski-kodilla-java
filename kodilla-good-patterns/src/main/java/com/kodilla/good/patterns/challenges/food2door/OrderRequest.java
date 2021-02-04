package com.kodilla.good.patterns.challenges.food2door;

import java.util.HashMap;
import java.util.Map;

public class OrderRequest {
    private final Map<Item, Integer> orderList;

    public OrderRequest() {

        orderList = new HashMap<>();
    }

    public Map<Item, Integer> getOrderList() {
        return orderList;
    }

    public void addItem(Item item, int count) {
        orderList.put(item, count);
    }
}
