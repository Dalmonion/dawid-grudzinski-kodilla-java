package com.kodilla.good.patterns.challenges.food2door;

public class OrderRequest {

    private final Item item;
    private final int amount;

    public OrderRequest(final Item product, final int item) {
        this.item = product;
        this.amount = item;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
