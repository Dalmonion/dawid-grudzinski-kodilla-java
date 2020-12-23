package com.kodilla.stream.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayOperationsTestSuite {

    @Test
    void testGetAverage() {
        //Given
        int [] intArray = new int[10];
        int number  = 2;
        for (int i=0; i < intArray.length; i++) {
            intArray[i] = number;
            number ++;
        }

        //When
        double average = ArrayOperations.getAverage(intArray);

        //Then
        assertEquals(6.5 , average);
    }
}
