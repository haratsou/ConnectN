package com.company;
/*
Abstract class player is a superclass for human player and computer player,
and has methods to define each player's game token, the player's name
and the way they make a move.
 */

public abstract class Player {
    private final String token;
    private final String name;

    public Player(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public abstract int getMove() throws InvalidMoveException;
}