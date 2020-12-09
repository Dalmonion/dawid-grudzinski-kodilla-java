package com.kodilla.testing.shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeCollector {

    private List<Shape> shapesList = new ArrayList<>();

    public void addFigure(Shape shape) {
        shapesList.add(shape);
    }

    public boolean removeFigure(Shape shape) {
        boolean result = false;
        if(shapesList.contains(shape)) {
            shapesList.remove(shape);
            result = true;
        }
        return result;
    }

    public Shape getFigure(int n) {
        Shape shape = null;
        if (n >= 0 && n < shapesList.size()) {
            shape = shapesList.get(n);
        }
        return shape;
    }

    public String showFigures() {
        return shapesList.toString();
    }

    public int getShapesListQuantity() {
        return shapesList.size();
    }
}
