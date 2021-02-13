package com.kodilla.patterns.builder.bigmac;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BigmacTestSuite {
    @Test
    void BigmacTestSuite() {
        //Given
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .bun("Regular")
                .burgers(2)
                .sauce("standard")
                .ingredient("bacon")
                .ingredient("onion")
                .ingredient("cucumber")
                .build();
        System.out.println(bigmac);
        //When
        int howManyIngredients = bigmac.getIngredients().size();
        boolean hasOnion = bigmac.getIngredients().contains("onion");
        boolean hasChilli = bigmac.getIngredients().contains("chilli");

        //Then
        assertEquals(3, howManyIngredients);
        assertTrue(hasOnion);
        assertFalse(hasChilli);

    }
}
