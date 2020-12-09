package com.kodilla.testing.shape;

public class Circle implements Shape {

    private String name;
    private double area;

    public Circle(String name, double area) {
        this.name = name;
        this.area = area;
    }

    public String getShapeName() {
        return name;
    }

    public double getField() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (Double.compare(circle.area, area) != 0) return false;
        return name.equals(circle.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(area);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
