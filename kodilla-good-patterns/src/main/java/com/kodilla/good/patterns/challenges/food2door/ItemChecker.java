package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemChecker {

    public boolean isOnList(CompaniesRepository repository, Item item) {
        long count = repository.getCompanyList().stream()
                .flatMap(e -> e.getCompanyGoods().entrySet().stream())
                .filter(e -> e.getKey().getItemName().equals(item.getItemName()))
                .map(e -> 1)
                .count();
        return count > 0 ? true : false;
    }

    public void processExecutor(CompaniesRepository repository, Item item, int amount) {
        int index = -1;
        for (int i = 0; i < repository.getCompanyList().size(); i++) {
            if (repository.getCompanyList().get(i).getCompanyGoods().containsKey(item)) index = i;
        }

        repository.getCompanyList().get(index).process(item, amount);
    }
}
