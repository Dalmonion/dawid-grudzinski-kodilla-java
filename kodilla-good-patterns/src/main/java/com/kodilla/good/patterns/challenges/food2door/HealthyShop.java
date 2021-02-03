package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HealthyShop extends Company {

    public HealthyShop(String companyName) {
        super(companyName);
    }

    @Override
    public void process(Item item, int amount) {
        log.info("Completely different ordering logic " + item.getItemName() + " from " + this.getCompanyName());
    }
}
