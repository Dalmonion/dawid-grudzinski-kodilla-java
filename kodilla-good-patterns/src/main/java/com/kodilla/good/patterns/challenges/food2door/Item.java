package com.kodilla.good.patterns.challenges.food2door;

public class Item {

    private final String itemName;
    private final String supplier;

    public Item(final String itemName, final String supplier) {
        this.itemName = itemName;

        this.supplier = supplier;
    }

    public String getItemName() {
        return itemName;
    }


    public String getSupplier() {
        return supplier;
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
