package com.kodilla.patterns2.decorator.pizza;

public class GarlicSauceDecorator extends AbstractPizzaOrderDecorator {
    public GarlicSauceDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }

    @Override
    public Long getCost() {
        return super.getCost() + 1L;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + garlic sauce";
    }
}
