package com.company;
import java.util.Random;

/*
 * ComputerPlayer extends Player. It introduces a random generator, which the computer uses
 * to get a number from 0 to 6, in order for the computer to select a column to place its token.
 */

public class ComputerPlayer extends Player {
    private final Random random = new Random();

    public ComputerPlayer(String token, String name) {
        super(token, name);
    }

    @Override
    public int getMove() {
        return random.nextInt(7);
    }
}