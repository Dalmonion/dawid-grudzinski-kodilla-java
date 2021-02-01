package com.kodilla.good.patterns.challenges;

import java.util.HashMap;
import java.util.Map;

public class WarehouseFiller {

    private final Map<Item, Integer> filler;

    public WarehouseFiller() {
        this.filler = new HashMap<>();
        filler.put(new Item("LCD", 199), 2);
    }

    public Map<Item, Integer> fillWarehouse() {
        return filler;
    }
}
