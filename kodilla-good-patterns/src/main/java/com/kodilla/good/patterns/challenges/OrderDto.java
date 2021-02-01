package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDto {

    private final User user;
    private  final boolean wasPurchased;
    private final double deductMoneyAmount;

    public OrderDto(final User user, final boolean wasPurchased, final double deductMoneyAmount) {
        this.user = user;
        this.wasPurchased = wasPurchased;
        this.deductMoneyAmount = deductMoneyAmount;
        log.info("Object OrderDto has been created and transferred.");
    }

    public User getUser() {
        return user;
    }

    public boolean wasPurchased() {
        return wasPurchased;
    }
}
