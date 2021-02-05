package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OrderProcessor {

    private final CompaniesRepository database;

    public OrderProcessor(final CompaniesRepository database) {
        this.database = database;
    }

    public void processOrder(OrderRequest orderRequest) {
        boolean canBuy;

        for (Company company : database.getCompanyList()) {
            for (Map.Entry<Item, Integer> order : orderRequest.getOrderList().entrySet()) {
                if (company.getCompanyGoods().containsKey(order.getKey())) {
                    canBuy = company.process(orderRequest);
                    if (canBuy) {
                        log.info("Proceeding with order in " + company.getCompanyName());
                    } else {
                        log.info("Order process interrupted");
                    }
                }
            }
        }
    }
}
