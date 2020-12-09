package com.kodilla.testing.shape;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD: Forum Test Suite")
public class ShapeCollectorTestSuite {

    private static int testCounter = 0;

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("This is the beginning of tests.");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("All tests are finished");
    }

    @BeforeEach
    public void beforeEachTest() {
        testCounter ++;
        System.out.println("Preparing to execute test #" + testCounter);
    }

    @Nested
    @DisplayName("Tests for collector list modification")
    class TestModification {

        @Test
        void testAddShape() {
            ShapeCollector shapeCollector = new ShapeCollector();
            Shape shape1 = new Triangle("trójkąt", 20.0);

            //When
            shapeCollector.addFigure(shape1);

            //Then
            assertEquals(1,shapeCollector.getShapesListQuantity() );
        }

        @Test
        void testRemoveShape() {
            ShapeCollector shapeCollector = new ShapeCollector();
            Shape shape1 = new Circle("kółeczko", 20.1);
            shapeCollector.addFigure(shape1);

            //When
            boolean result = shapeCollector.removeFigure(shape1);

            //Then
            assertTrue(result);
            assertEquals(0, shapeCollector.getShapesListQuantity());
        }
    }

    @Nested
    @DisplayName("Tests for showing results")
    class TestDisplay {

        @Test
        void testGetFigure() {
            //Given
            ShapeCollector shapeCollector = new ShapeCollector();
            Shape shape1 = new Square("kwadracik", 36.0);
            shapeCollector.addFigure(shape1);

            //When
            Shape retrievedFigure = shapeCollector.getFigure(0);

            //Then
            assertEquals(shape1, retrievedFigure);
        }

        @Test
        void testShowFigures() {
            //Given
            ShapeCollector shapeCollector = new ShapeCollector();

            Shape shape1 = new Triangle("trójkąt", 20.0);
            Shape shape2 = new Circle("kółeczko", 20.1);
            Shape shape3 = new Square("kwadracik", 36.0);

            shapeCollector.addFigure(shape1);
            shapeCollector.addFigure(shape2);
            shapeCollector.addFigure(shape3);

            //When
            String result = shapeCollector.showFigures();

            Shape [] shapes = {shape1, shape2, shape3};
            String expectedResult = Arrays.toString(shapes);

            //Then
            assertEquals(result, expectedResult);

        }
    }
}
