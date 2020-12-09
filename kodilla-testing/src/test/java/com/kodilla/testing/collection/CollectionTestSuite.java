package com.kodilla.testing.collection;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Collection Test Suite")
public class CollectionTestSuite {

    @BeforeEach
    public void before() {
        System.out.println("Test Case: begin");
    }

    @AfterEach
    public void after() {
        System.out.println("Test Case: end");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Test Suite: begin");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Test Suite: end");
    }

    @DisplayName("when called exterminator method with empty list as parameter, return list should be empty")

    @Test
    void testOddNumbersExterminatorEmptyList() {
        //Given
        List<Integer> emptyList = new ArrayList<>();
        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();
        //When
        List<Integer> resultList = oddNumbersExterminator.exterminate(emptyList);
        List<Integer> checkList = new ArrayList<>();
        System.out.println("Testing if the list is empty");
        //Then
        Assertions.assertEquals(resultList, checkList);
    }

    @DisplayName("when created OddNumbersExterminator, method called exterminator should return list without odd numbers")

    @Test
    void testOddNumbersExterminatorNormalList() {
        //Given
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> integers = new ArrayList<>(Arrays.asList(intArray));//
        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();
        //When
        List<Integer> resultList = oddNumbersExterminator.exterminate(integers);
        Integer [] intCheckArray = {2, 4, 6};
        List<Integer> checkList = new ArrayList<>(Arrays.asList(intCheckArray));
        System.out.println("Testing if the list has any odd numbers");
        //Then
        Assertions.assertEquals(resultList, checkList);
    }
}
