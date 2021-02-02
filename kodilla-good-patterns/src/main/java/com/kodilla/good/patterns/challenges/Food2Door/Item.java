package com.kodilla.good.patterns.challenges.Food2Door;

public class Item {

    private final String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return itemName.equals(item.itemName);
    }

    @Override
    public int hashCode() {
        return itemName != null ? itemName.hashCode() : 0;
    }
}
