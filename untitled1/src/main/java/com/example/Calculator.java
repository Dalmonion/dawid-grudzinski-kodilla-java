package com.example;

public class Calculator {
    public Long add(long a, long b) {
        return a + b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            return (double) 0;
        }
        return a/b;
    }
}
