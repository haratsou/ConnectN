package com.company;

import java.util.concurrent.TimeUnit;

public class Game {
    private final Board board;
    private final Player player_1;
    private final Player player_2;
    private final Player player_3;
    private Player player;

    //constructor
    public Game (Board board, Player player_1, Player player_2, Player player_3) {
        this.board = board;
        this.player_1 = player_1;
        this.player_2 = player_2;
        this.player_3 = player_3;
        this.player = player_1;
    }

    /*
     * Gets the move and token from current player and adds it to the board.
     * Throws exception if the player's int input is outside the board limits.
     */
    public void makeMove() throws InvalidMoveException {
        boolean placed = false;
        while(!placed) {
            try {
                placed = board.addToBoard(player.getMove(), player.getToken());
                if (!placed && player instanceof HumanPlayer) {
                    System.out.println("This stack is full. Try again!");
                }
            }
            catch(ArrayIndexOutOfBoundsException e) {
                throw new InvalidMoveException("Enter a valid tile position.\n");
            }
        }
    }

    //swaps players
    public void nextPlayer () {
        if (player == player_1) {
            player = player_2;
        }
        else if (player == player_2){
            player = player_3;
        }
        else if (player == player_3) {
            player = player_1;
        }
    }

    /*
     * Plays each player's move and swaps the players until hasWon is true,
     * which means the game is over. Catches Invalid Move exceptions and only prints a warning
     * if the player is human.
     */
    public boolean playGame() {
        boolean hasWon = false;
        try {
            System.out.println(player.getName() + ", it's your turn.");
            makeMove();
            TimeUnit.SECONDS.sleep(2);
            System.out.println(board);
            hasWon = winner();
            nextPlayer();
        }
        catch (InvalidMoveException | InterruptedException e) {
            if (player instanceof HumanPlayer) {
                System.out.println(e.getMessage());
            }
        }
        return hasWon;
    }

    public boolean winner () {
        boolean hasWon = false;
        if (board.winner(player.getToken())) {
            hasWon = true;
            System.out.println("\u001B[31m" + "Congratulations "
                    + player.getName() + ", you have won!" + "\u001B[0m");
        }
        return hasWon;
    }
}
