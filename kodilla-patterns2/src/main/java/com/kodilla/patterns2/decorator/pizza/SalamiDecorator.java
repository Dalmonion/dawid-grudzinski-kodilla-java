package com.kodilla.patterns2.decorator.pizza;

public class SalamiDecorator extends AbstractPizzaOrderDecorator {
    public SalamiDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }

    @Override
    public Long getCost() {
        return super.getCost() + 5L;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + salami";
    }
}
