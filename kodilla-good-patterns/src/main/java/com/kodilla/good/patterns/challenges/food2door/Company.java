package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Company implements ProcessService {

    private final String companyName;
    private final Map<Item, Integer> companyGoods;

    public Company(final String companyName) {
        this.companyName = companyName;
        this.companyGoods = new HashMap<>();
    }

    public Company(String companyName, Map<Item, Integer> companyGoods) {
        this.companyName = companyName;
        this.companyGoods = companyGoods;
    }

    public void addGoods(Item item, Integer quantity) {
        companyGoods.put(item, quantity);
    }

    public String getCompanyName() {
        return companyName;
    }

    public Map<Item, Integer> getCompanyGoods() {
        return new HashMap<>(companyGoods);
    }

    @Override
    public void process(Item item, int amount) {
        log.info("Basic logic for processing order inside the " + this.companyName + " company");
    }

}
