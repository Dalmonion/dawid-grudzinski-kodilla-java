package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDto {

    private final String companyName;
    private final String product;
    private final int amount;
    private final boolean bought;

    public OrderDto(final String companyName, final String product, final int amount, final boolean bought) {
        this.product = product;
        this.amount = amount;
        this.bought = bought;
        this.companyName = companyName;
        log.info("Creating object OrderDto with vital information about order");
    }
}
