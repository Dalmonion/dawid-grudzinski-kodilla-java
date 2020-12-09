package com.kodilla.testing;

import com.kodilla.testing.calculator.Calculator;
import com.kodilla.testing.user.SimpleUser;

public class TestingMain {
    public static void main(String[] args) {
        SimpleUser simpleUser = new SimpleUser("theForumUser");

        String result = simpleUser.getUsername();

        if (result.equals("theForumUser")) {
            System.out.println("test OK");
        } else {
            System.out.println("Error!");
        }

        Calculator calculator = new Calculator();

        int addResult = calculator.add(5, 5);

        System.out.println((addResult == 10) ? "test Ok" : "Error!");

        int subtractResult = calculator.subtract(10, 5);

        System.out.println((subtractResult == 5) ? "test Ok" : "Error!");
    }
}
