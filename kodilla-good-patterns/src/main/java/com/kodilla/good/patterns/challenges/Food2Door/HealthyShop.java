package com.kodilla.good.patterns.challenges.Food2Door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HealthyShop extends Company {

    public HealthyShop(String companyName) {
        super(companyName);
    }

    @Override
    public void process() {
        log.info("Basic logic for processing order inside the HealthyShop company");
    }
}
