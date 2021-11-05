package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! What's your name? ");
        String name = scanner.nextLine();
        System.out.println("\nWelcome to ConnectN " + name + "!");

        int tokenNumber;

        /*
        * try to take tokenNumber from command line argument,
        * set it to 4 if it isn't a number between 2 and 7
        */
        try {
            tokenNumber = Integer.parseInt(args[0]);
            if (tokenNumber > 7 || tokenNumber < 2) {
                tokenNumber = 4;
                }
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            tokenNumber = 4;
        }

        Board board = new Board(tokenNumber);

        System.out.println("\nGreat! The goal of this game is to connect " + tokenNumber
                + " tokens horizontally, vertically or diagonally.");
        System.out.println("You are playing against two computer players, C.H.E.E.S.E. and Marvin.");
        System.out.println("Your tokens are red, C.H.E.E.S.E.'s are blue and Marvin's yellow.");
        System.out.println("Good luck!\n");

        //player's token is red
        Player player = new HumanPlayer("\u001B[31m"+ "X" + "\u001B[0m", name);

        //computer player 1 token is blue
        Player computer1 =
                new ComputerPlayer("\u001B[36m" + "O" + "\u001B[0m", "C.H.E.E.S.E.");

        //computer player 2 token is yellow
        Player computer2 =
                new ComputerPlayer("\u001B[33m" + "Y" + "\u001B[0m", "Marvin");

        Game game = new Game(board, player, computer1, computer2);
        boolean hasWon = false;
        System.out.println(board);
        while (!hasWon) {
            hasWon = game.playGame();

            if (board.isBoardFull()) {
                System.out.println("It's a tie! Better luck next time!");
                break;
            }
        }
        scanner.close();
    }
}
