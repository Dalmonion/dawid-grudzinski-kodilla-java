package com.kodilla.patterns2.observer.homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExercisesQueueTestSuite {

    @Test
    void testUpdate() {
        ExercisesQueue studentA = new StudentA();
        ExercisesQueue studentB = new StudentB();
        Mentor mentorA = new Mentor("mentorUsername");
        studentA.registerObserver(mentorA);
        studentB.registerObserver(mentorA);
        //When
        studentA.addExercise("Exercise1");
        studentA.addExercise("Exercise2");
        studentA.addExercise("Exercise3");
        studentB.addExercise("Exercise2");
        studentB.addExercise("Exercise3");
        //Then
        assertEquals(5, mentorA.getUpdateCount());
    }
}
