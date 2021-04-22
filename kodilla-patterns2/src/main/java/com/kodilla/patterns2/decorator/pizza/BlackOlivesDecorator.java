package com.kodilla.patterns2.decorator.pizza;

public class BlackOlivesDecorator extends AbstractPizzaOrderDecorator {
    public BlackOlivesDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }

    @Override
    public Long getCost() {
        return super.getCost() + 2L;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + olives";
    }
}
