package com.kodilla.patterns2.decorator.pizza;

public class BasicPizzaOrder implements PizzaOrder {
    @Override
    public Long getCost() {
        return 15L;
    }

    @Override
    public String getDescription() {
        return "Bottom with tomato sauce and cheese";
    }
}
