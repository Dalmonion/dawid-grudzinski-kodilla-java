package com.kodilla.patterns2.decorator.pizza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PizzaOrderTestSuite {

    @Test
    void testPizzaOrderWithCheeseGetCostAndDescription() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new AdditionalCheeseDecorator(theOrder);
        //When
        Long theCost = theOrder.getCost();
        String description = theOrder.getDescription();
        //Then
        assertEquals(18, theCost);
        assertEquals("Bottom with tomato sauce and cheese + additional cheese", description);
    }

    @Test
    void testPizzaOrderWithAllToppingsGetCostAndDescription() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new AdditionalCheeseDecorator(theOrder);
        theOrder = new SalamiDecorator(theOrder);
        theOrder = new BlackOlivesDecorator(theOrder);
        theOrder = new GarlicSauceDecorator(theOrder);
        System.out.println(theOrder.getDescription());
        //When
        Long theCost = theOrder.getCost();
        String description = theOrder.getDescription();
        //Then
        assertEquals(26, theCost);
        assertEquals("Bottom with tomato sauce and cheese + additional cheese + salami + olives + garlic sauce", description);
    }
}
