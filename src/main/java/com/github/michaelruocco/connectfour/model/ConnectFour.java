package com.github.michaelruocco.connectfour.model;

import java.util.ArrayList;
import java.util.Collection;

public class ConnectFour {

    private static final int DEFAULT_NUMBER_OF_COLUMNS = 7;
    private static final int DEFAULT_NUMBER_OF_ROWS = 6;
    private static final Player PLAYER_ONE = new PlayerOne();
    private static final Player PLAYER_TWO = new PlayerTwo();

    private final Grid grid;
    private final GridChecker gridChecker;

    private final Collection<WinnerListener> winnerListeners = new ArrayList<>();
    private final Collection<SwitchPlayerListener> switchPlayerListeners = new ArrayList<>();
    private final Collection<DropTokenListener> dropTokenListeners = new ArrayList<>();

    private Player currentPlayer = PLAYER_ONE;

    public ConnectFour() {
        this(DEFAULT_NUMBER_OF_ROWS, DEFAULT_NUMBER_OF_COLUMNS);
    }

    public ConnectFour(int numberOfRows, int numberOfColumns) {
        this.grid = new Grid(numberOfRows, numberOfColumns);
        this.gridChecker = new GridChecker(grid);
    }

    public int numberOfRows() {
        return grid.numberOfRows();
    }

    public int numberOfColumns() {
        return grid.numberOfColumns();
    }

    public boolean currentPlayerHasWon() {
        return gridChecker.hasWinner(currentPlayer.getToken());
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
        firePlayerSwitched();
    }

    public void dropToken(int column) {
        Token token = currentPlayer.getToken();
        grid.dropToken(column, token);
        fireTokenDropped(column, grid.getTopOfColumn(column));
        System.out.println("has winner " + gridChecker.hasWinner(currentPlayer.getToken()));
        if (gridChecker.hasWinner(currentPlayer.getToken()))
            firePlayerWins(currentPlayer);
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

    public void addWinnerListener(WinnerListener listener) {
        winnerListeners.add(listener);
    }

    public void addSwitchPlayerListener(SwitchPlayerListener listener) {
        switchPlayerListeners.add(listener);
    }

    public void addDropTokenListener(DropTokenListener listener) {
        dropTokenListeners.add(listener);
    }

    private void firePlayerWins(Player player) {
        winnerListeners.forEach(l -> l.playerWins(player));
    }

    private void firePlayerSwitched() {
        switchPlayerListeners.forEach(SwitchPlayerListener::switchPlayer);
    }

    private void fireTokenDropped(int column, int row) {
        dropTokenListeners.forEach(l -> l.tokenDropped(column, row));
    }

}
