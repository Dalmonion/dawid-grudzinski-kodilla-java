package com.kodilla.testing.shape;

public class Triangle implements Shape {

    private String name;
    private double area;

    public Triangle(String name, double area) {
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

        Triangle triangle = (Triangle) o;

        if (Double.compare(triangle.area, area) != 0) return false;
        return name.equals(triangle.name);
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