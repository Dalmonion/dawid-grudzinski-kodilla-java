package com.kodilla.patterns2.observer.homework;

import java.util.*;

public class ExercisesQueue implements Observable {
    private final String name;
    private final Deque<String> unrevisedExercises;
    private final List<Observer> observers;

    public ExercisesQueue(String name) {
        this.name = name;
        this.unrevisedExercises = new ArrayDeque<>();
        this.observers = new ArrayList<>();
    }

    public void addExercise(String exercise) {
        unrevisedExercises.offerLast(exercise);
        notifyObservers();
    }

    public List<String> getExercises() {
        return new ArrayList<>(unrevisedExercises);
    }

    public String getName() {
        return name;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }

    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
