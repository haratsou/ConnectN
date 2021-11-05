package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Human Player extends player and defines the human's token to be "x".
 * It also introduces a scanner to get the player's input for column
 * in order to mark a selected tile, throwing an exception if the input
 * is not an int.
 */

public class HumanPlayer extends Player {
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String token, String name) {
        super(token, name);
    }

    @Override
    public int getMove() throws InvalidMoveException {
        int number;

        System.out.println("Where do you want to place your next token?");

        try {
            number = scanner.nextInt() - 1;
        }
        catch (InputMismatchException e) {
            scanner.next();
            throw new InvalidMoveException("Invalid input. Please enter a number in the board.\n");
        }
        return number;
    }
}