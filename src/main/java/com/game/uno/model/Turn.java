package com.game.uno.model;

public class Turn {
    private Player currentPlayer;
    private Player nextPlayer;

    public Turn(Player player1, Player player2) {
        this.currentPlayer = player1;
        this.nextPlayer = player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void shiftChange() {
        Player temp = currentPlayer;
        currentPlayer = nextPlayer;
        nextPlayer = temp;
    }
}

