package com.kodilla.rps.old;

import java.util.Random;

public class computer1 {
    private int CompWinnings = 0;

    public int draw() {
        Random random = new Random();
        int compChoice = random.nextInt(3);
        switch (compChoice) {
            case 0:
                System.out.println("Komputer zagrał kamień");
                break;
            case 1:
                System.out.println("Komputer zagrał papier");
                break;
            case 2:
                System.out.println("Komputer zagrał nożyce");
        }
        return compChoice;
    }

    public int getCompWinnings() {
        return CompWinnings;
    }

    public void addCompWinnings() {
        this.CompWinnings ++;
    }
}
