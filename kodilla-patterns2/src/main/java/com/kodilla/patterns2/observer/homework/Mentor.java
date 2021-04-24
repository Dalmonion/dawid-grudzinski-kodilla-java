package com.kodilla.patterns2.observer.homework;

public class Mentor implements Observer {
    private final String mentorsUsername;
    private int updateCount;

    public Mentor(String mentorsUsername) {
        this.mentorsUsername = mentorsUsername;
    }

    @Override
    public void update(ExercisesQueue exercisesQueue) {
        System.out.println(mentorsUsername + ": New exercises in student: " + exercisesQueue.getName() + "\n" +
                exercisesQueue.getExercises() + "\n" + "(total: " + exercisesQueue.getExercises().size() + " exercises)");
        updateCount++;
    }

    public String getMentorsUsername() {
        return mentorsUsername;
    }

    public int getUpdateCount() {
        return updateCount;
    }
}
