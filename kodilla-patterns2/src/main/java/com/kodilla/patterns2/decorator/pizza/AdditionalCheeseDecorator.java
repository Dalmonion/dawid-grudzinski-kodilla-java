package com.kodilla.patterns2.decorator.pizza;

public class AdditionalCheeseDecorator extends AbstractPizzaOrderDecorator {
    public AdditionalCheeseDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }

    @Override
    public Long getCost() {
        return super.getCost() + 3L;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + additional cheese";
    }
}
