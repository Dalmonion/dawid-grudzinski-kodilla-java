package com.kodilla.rps;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int getMove() {
        return IOService.humanChoice(this);
    }

    @Override
    public int getEnhancedMove(int number) {
        return 0;
    }
}
