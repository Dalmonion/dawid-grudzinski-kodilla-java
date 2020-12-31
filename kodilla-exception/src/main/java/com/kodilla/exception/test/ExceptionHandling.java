package com.kodilla.exception.test;

public class ExceptionHandling {
    public static void main(String[] args) {
        SecondChallenge secondChallenge = new SecondChallenge();
        String result = null;
        try {
            result = secondChallenge.probablyIWillThrowException(1, 2);
        } catch (Exception e) {
            System.out.println("X has to be equals 1 and Y must be different from 1.5");
        } finally {
            System.out.println("Result= " + result);
        }
    }



}
