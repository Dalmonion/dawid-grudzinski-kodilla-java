package com.kodilla.spring.portfolio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
public class BoardTestSuite {

    @Test
    void testContainsTasks() {
        //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("com.kodilla.spring");

        //When
        boolean toDoList = context.containsBean("taskList1");
        boolean inProgressList = context.containsBean("taskList2");
        boolean doneList = context.containsBean("taskList3");

        //Then
        assertTrue(toDoList);
        assertTrue(inProgressList);
        assertTrue(doneList);

    }

    @Test
    void testTaskAdd() {
        //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("com.kodilla.spring");
        Board board = context.getBean(Board.class);

        //When
        board.getToDoList().addTask("Create portfolio");
        board.getInProgressList().addTask("Finish course");
        board.getDoneList().addTask("Start learning");

        //Then
        assertEquals("Create portfolio", board.getToDoList().getTasks().get(0));
        assertEquals("Finish course", board.getInProgressList().getTasks().get(0));
        assertEquals("Start learning", board.getDoneList().getTasks().get(0));
    }
}
