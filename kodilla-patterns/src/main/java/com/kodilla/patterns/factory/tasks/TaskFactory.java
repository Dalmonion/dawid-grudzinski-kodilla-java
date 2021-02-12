package com.kodilla.patterns.factory.tasks;

public class TaskFactory {
    public static final String SHOPPING = "SHOPPING";
    public static final String PAINTING = "PAINTING";
    public static final String DRIVING = "DRIVING";

    public final Task createTask(final String taskType) {
        switch (taskType) {
            case SHOPPING:
                return new ShoppingTask("Shopping task", "Bananas", 10);
            case PAINTING:
                return new PaintingTask("Painting task", "RED", "Canvas");
            case DRIVING:
                return new DrivingTask("Driving task", "Dakota", "Car");
            default:
                return null;
        }
    }
}
