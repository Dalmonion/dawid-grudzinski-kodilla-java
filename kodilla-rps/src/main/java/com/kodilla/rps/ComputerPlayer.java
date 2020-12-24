package com.kodilla.rps;

import java.util.Random;

public class ComputerPlayer extends Player {
    public ComputerPlayer() {
        super("Komputer");
    }

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public int getMove() {
        Random random = new Random();
        return IOService.computerChoice(random.nextInt(5), this);
    }

    @Override
    public int getEnhancedMove(int humanChoice) {
        Random random = new Random();
        int computerChoice = -1;

        boolean win = Math.random() >= 0.5;
        boolean draw = Math.random() <= 0.25;

        int [] winningArray;
        int [] loosingArray;
        int rnd;

        switch (humanChoice) {
            case 1:
                if (win) {
                    winningArray = new int[]{2,5};
                    rnd = random.nextInt(winningArray.length);
                    computerChoice = winningArray[rnd];
                    break;
                } else if (draw) {
                    computerChoice = 1;
                    break;
                } else {
                    loosingArray = new int[]{3,4};
                    rnd = random.nextInt(loosingArray.length);
                    computerChoice = loosingArray[rnd];
                    break;
                }
            case 2:
                if (win) {
                    winningArray = new int[]{3,4};
                    rnd = random.nextInt(winningArray.length);
                    computerChoice = winningArray[rnd];
                    break;
                } else if (draw) {
                    computerChoice = 2;
                    break;
                } else {
                    loosingArray = new int[]{1,5};
                    rnd = random.nextInt(loosingArray.length);
                    computerChoice = loosingArray[rnd];
                    break;
                }
            case 3:
                if (win) {
                    winningArray = new int[]{1,5};
                    rnd = random.nextInt(winningArray.length);
                    computerChoice = winningArray[rnd];
                    break;
                } else if (draw) {
                    computerChoice =  3;
                    break;
                } else {
                    loosingArray = new int[]{2,4};
                    rnd = random.nextInt(loosingArray.length);
                    computerChoice =  loosingArray[rnd];
                    break;
                }
            case 4:
                if (win) {
                    winningArray = new int[]{1,3};
                    rnd = random.nextInt(winningArray.length);
                    computerChoice = winningArray[rnd];
                    break;
                } else if (draw) {
                    computerChoice =  4;
                    break;
                } else {
                    loosingArray = new int[]{2,5};
                    rnd = random.nextInt(loosingArray.length);
                    computerChoice = loosingArray[rnd];
                    break;
                }
            case 5:
                if (win) {
                    winningArray = new int[]{2,4};
                    rnd = random.nextInt(winningArray.length);
                    computerChoice = winningArray[rnd];
                    break;
                } else if (draw) {
                    computerChoice = 5;
                    break;
                } else {
                    loosingArray = new int[]{1,3};
                    rnd = random.nextInt(loosingArray.length);
                    computerChoice = loosingArray[rnd];
                    break;
                }
        }

        return computerChoice;
    }
}
