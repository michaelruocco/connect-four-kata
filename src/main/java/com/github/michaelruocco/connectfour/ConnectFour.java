package com.github.michaelruocco.connectfour;

public class ConnectFour {

    private static final int DEFAULT_NUMBER_OF_COLUMNS = 7;
    private static final int DEFAULT_NUMBER_OF_ROWS = 6;
    private static final Player PLAYER_ONE = new PlayerOne();
    private static final Player PLAYER_TWO = new PlayerTwo();

    private final Grid grid;

    private Player currentPlayer = PLAYER_ONE;

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

    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }

    public String getGridAsString() {
        return grid.asString();
    }

    public void switchCurrentPlayer() {
        if (currentPlayer.equals(PLAYER_ONE))
            currentPlayer = PLAYER_TWO;
        else
            currentPlayer = PLAYER_ONE;
    }

    public void dropToken(int column) {
        Token token = currentPlayer.getToken();
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

    public Token getToken(int column, int row) {
        return grid.getToken(column, row);
    }

}
