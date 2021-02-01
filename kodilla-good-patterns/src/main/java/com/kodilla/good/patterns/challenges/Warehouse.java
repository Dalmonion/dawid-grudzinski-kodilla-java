package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Warehouse {

    private Map<Item, Integer> goods;

    public Warehouse() {
        final WarehouseFiller filler = new WarehouseFiller();
        goods = filler.fillWarehouse();
        log.info("Warehouse been created");
    }

    public boolean itemInStock(Item item) {
        boolean isInStock = false;
        for(Map.Entry<Item, Integer> entry : goods.entrySet()) {
            if (item.getName().equals(entry.getKey().getName()) && entry.getValue() > 0) {
                isInStock = true;
            }
        }
        log.info("Checking if item is in stock: " + isInStock);
        return isInStock;
    }
}
