package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlutenFreeShop extends Company {

    public GlutenFreeShop(String companyName) {
        super(companyName);
    }

    @Override
    public void process(Item item, int amount) {
        log.info("Different ordering logic " + item.getItemName() + " from " + this.getCompanyName());

    }
}
