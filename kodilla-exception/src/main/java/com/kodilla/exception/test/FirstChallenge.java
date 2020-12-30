package com.kodilla.exception.test;

public class FirstChallenge {

    public double divide(double a, double b) throws ArithmeticException{
        if (b == 0){
            throw new ArithmeticException();
        }
        return a / b;
    }

    public static void main(String[] args) {
        String fruit = "strawberries";
        String message = fruit.substring(2,5 ) + fruit.substring(5,2) ;
        System.out.println(fruit.substring(2, 5));

        FirstChallenge firstChallenge = new FirstChallenge();

        double result = Double.MIN_VALUE;

        try {
           result = firstChallenge.divide(3, 0);
        } catch (ArithmeticException e) {
            System.out.println("You can't divide by 0");
        } finally {
            System.out.println(result);
        }


    }
}
