package com.company;

import java.util.Arrays;

public class Board {
    private final String[][] board = new String [6][7];
    private final int tokenNumber;

    //constructor
    public Board(int tokenNumber) {
        for (String[] row : board) {
            Arrays.fill(row, " ");
        }
        this.tokenNumber = tokenNumber;
    }

    // creates a string that displays the 2d array board with | borders and display numbers
    public String toString () {
        StringBuilder boardToPrint = new StringBuilder();
        for (String[] row : board) {
            for (String tile : row) {
                boardToPrint.append(" | ").append(tile);
            }
            boardToPrint.append(" |\n");
        }
        for (int k = 1; k <= board[1].length; k++) {
            boardToPrint.append("   ").append(k);
        }
        boardToPrint.append("\n");
        return boardToPrint.toString();
    }

    /*
     * adds the player's token to the first empty row of their selected column on the board
     * and returns true if the token was placed or false if it wasn't because the stack was full
     */
    public boolean addToBoard (int number, String token) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][number].equals(" ")) {
                board[i][number] = token;
                return true;
            }
        }
        return false;
    }

    /*
     * iterates from bottom right to top left for efficiency and looks
     * for horizontal consecutive tokens and returns true if it finds n (tokenNumber)
     */
    public boolean checkHorizontal (String token) {
        int count;
        for (String[] row : board) {
            count = 0;
            for (String tile : row) {
                if (tile.equals(token)) {
                    count = count + 1;
                    if (count >= tokenNumber) {
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }
        return false;
    }

    //looks for vertical consecutive tokens and returns true if it finds n consecutive tokens.
    public boolean checkVertical (String token) {
        int count;
        for (int i = 0; i<board[0].length; i++) {
            count = 0;
            for (String[] row : board) {
                if (row[i].equals(token)) {
                    count = count + 1;
                    if (count >= tokenNumber) {
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }
        return false;
    }

    /*
     * Combines two methods to check for consecutive tokens both it the left and right diagonals.
     * Returns true if it finds n consecutive tokens.
     */
    public boolean checkDiagonal (String token) {
        int countRight;
        int countLeft;
        for (int i = board.length-1; i >= 0; i--) {
            for (int j = board[0].length-1; j >= 0; j--) {
                countRight = checkRightDiagonal(i, j, token);
                countLeft = checkLeftDiagonal(i, j, token);
                if (countRight >= tokenNumber || countLeft >= tokenNumber) {
                    return true;
                }
            }
        }
        return false;
    }

    public int checkRightDiagonal (int vertical, int horizontal, String token) {
        int count = 0;
        try {
            while (board[vertical][horizontal].equals(token)) {
                count = count + 1;
                vertical--;
                horizontal++;
            }
        }
        catch (IndexOutOfBoundsException ignored) {}
        return count;
    }

    public int checkLeftDiagonal (int vertical, int horizontal, String token) {
        int count = 0;
        try {
            while (board[vertical][horizontal].equals(token)) {
                count = count + 1;
                vertical--;
                horizontal--;
            }
        }
        catch (IndexOutOfBoundsException ignored) {}
        return count;
    }

    /*
     * Checks if there are empty tiles in the board. Once there aren't any, if returns true.
     * That means the board is full but no-one has won, so it's a tie.
     */
    public boolean isBoardFull () {
        for (String[] row : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (row[j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    //returns true if any of the consecutive token checks are true, meaning someone has won
    public boolean winner (String token) {
        return checkHorizontal(token) || checkVertical(token)
                || checkDiagonal(token);
    }
}
