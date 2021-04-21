package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatoTestSuite {

    @Test
    public void testAdd() {
        //given
        Calculator calculator = new Calculator();
        //when
        Long result = calculator.add(123L, 234);
        double result2 = calculator.divide(3.0, 0);

        //then
        assertEquals(357, result);
        assertEquals(result2, 0);
    }

}
