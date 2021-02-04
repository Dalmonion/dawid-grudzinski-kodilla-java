package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Company implements ProcessService {

    private final String companyName;
    private final Map<Item, Integer> companyGoods;

    public Company(final String companyName) {
        this.companyName = companyName;
        this.companyGoods = new HashMap<>();
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

    private void deductCountItem(Item item, int count) {
        for(Map.Entry<Item, Integer> entry : companyGoods.entrySet()) {
            if(item.getItemName().equals(entry.getKey().getItemName()) && count <= entry.getValue()) {
                log.info("Item " + item.getItemName() + "- ordered count: " + count + ", stock count: " + entry.getValue());
                entry.setValue(entry.getValue() - count);
                log.info("Stock count after buying "+ item.getItemName() + ": " + entry.getValue());
            }
        }

    }

    @Override
    public boolean process(OrderRequest orderRequest) {
        List<Boolean> canBuyAllList = new ArrayList<>();

        for (Map.Entry<Item, Integer> order : orderRequest.getOrderList().entrySet()) {
            if (getCompanyGoods().containsKey(order.getKey())) {
                if (getCompanyGoods().get(order.getKey()) >= order.getValue()) {
                    canBuyAllList.add(true);
                    deductCountItem(order.getKey(), order.getValue());
                } else {
                    canBuyAllList.add(false);
                }
            }
        }

        boolean canOrderAll = true;

            if(canBuyAllList.contains(false)) canOrderAll = false;

        return canOrderAll;
    }

}
