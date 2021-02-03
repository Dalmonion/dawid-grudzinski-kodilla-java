package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtraFoodShop extends Company {

    public ExtraFoodShop(String companyName) {
        super(companyName);
    }

    @Override
    public void process(Item item, int amount) {
        log.info("Logic with ordering " + item.getItemName() + " in amount of " + amount + " from " + getCompanyName());
    }
}
