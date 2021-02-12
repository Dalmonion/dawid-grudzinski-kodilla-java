package com.kodilla.patterns.factory.tasks;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskFactoryTestSuite {
    @Test
    void testFactoryShopping() {
        //Given
        TaskFactory factory = new TaskFactory();
        //When
        Task shopping = factory.createTask(TaskFactory.SHOPPING);
        shopping.executeTask();
        //Then
        assertEquals("Shopping task", shopping.getTaskName());
        assertEquals(true, shopping.isTaskExecuted());
    }

    @Test
    void testFactoryPainting() {
        //Given
        TaskFactory factory = new TaskFactory();
        //When
        Task painting = factory.createTask(TaskFactory.PAINTING);
        painting.executeTask();
        //Then
        assertEquals("Painting task", painting.getTaskName());
        assertEquals(true, painting.isTaskExecuted());
    }

    @Test
    void testFactoryDriving() {
        //Given
        TaskFactory factory = new TaskFactory();
        //When
        Task driving = factory.createTask(TaskFactory.DRIVING);
        driving.executeTask();
        //Then
        assertEquals("Driving task", driving.getTaskName());
        assertEquals(true, driving.isTaskExecuted());
    }
}
