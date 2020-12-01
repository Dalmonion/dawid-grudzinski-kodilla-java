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
        return IOService.computerChoice(random.nextInt(3), this);
    }
}
