package com.github.michaelruocco.connectfour;

import static java.awt.Color.*;

public class ConnectFour {

    private static final int DEFAULT_NUMBER_OF_COLUMNS = 7;
    private static final int DEFAULT_NUMBER_OF_ROWS = 6;
    private static final Player RED_PLAYER = new Player("Red", RED);
    private static final Player YELLOW_PLAYER = new Player("Yellow", YELLOW);

    private final Grid grid;

    private Player currentPlayer = RED_PLAYER;

    public ConnectFour() {
        this(DEFAULT_NUMBER_OF_ROWS, DEFAULT_NUMBER_OF_COLUMNS);
    }

    public ConnectFour(int numberOfRows, int numberOfColumns) {
        this.grid = new Grid(numberOfRows, numberOfColumns);
    }

    public int numberOfRows() {
        return grid.numberOfRows();
    }

    public int numberOfColumns() {
        return grid.numberOfColumns();
    }

    public boolean currentPlayerHasWon() {
        return grid.hasWinner(currentPlayer.getToken());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getGridAsString() {
        return grid.asString();
    }

    public void switchCurrentPlayer() {
        if (currentPlayer.equals(RED_PLAYER))
            currentPlayer = YELLOW_PLAYER;
        else
            currentPlayer = RED_PLAYER;
    }

    public void dropToken(String column) {
        String token = currentPlayer.getToken();
        grid.dropToken(column, token);
    }

    public int getTopOfColumn(int index) {
        return grid.getTopOfColumn(index);
    }

    public void reset() {
        grid.reset();
    }

    public boolean isColumnFull(int index) {
        return grid.isColumnFull(index);
    }

}
