package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.InvalidColumnException;
import com.github.michaelruocco.connectfour.model.Player;
import com.github.michaelruocco.connectfour.model.ConnectFour;
import com.github.michaelruocco.connectfour.model.ConnectFourException;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleConnectFour {

    private final ConnectFour connectFour;
    private final Scanner scanner;
    private final PrintStream printStream;

    public ConsoleConnectFour(ConnectFour connectFour, Scanner scanner, PrintStream printStream) {
        this.connectFour = connectFour;
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public void play() {
        while(!currentPlayerHasWon()) {
            playRound();
        }
    }

    public String getCurrentPlayerName() {
        Player currentPlayer = connectFour.getCurrentPlayer();
        return currentPlayer.getName();
    }

    public boolean currentPlayerHasWon() {
        return connectFour.currentPlayerHasWon();
    }

    private void playRound() {
        if (!takeTurn())
            return;

        if (connectFour.currentPlayerHasWon()) {
            printCurrentPlayerWins();
            return;
        }

        connectFour.switchCurrentPlayer();
    }

    private boolean takeTurn() {
        try {
            printGrid();
            printPlayerPrompt();
            int column = getColumnInput();
            connectFour.dropToken(column);
            return true;
        } catch (ConnectFourException e) {
            printStream.println("Error: " + e.getMessage());
            return false;
        }
    }

    private void printPlayerPrompt() {
        String name = getCurrentPlayerName();
        printStream.print(name + " player input column to drop token ");
    }

    private int getColumnInput() {
        String columnInput = scanner.next();
        try {
            return Integer.parseInt(columnInput);
        } catch (NumberFormatException e) {
            throw new InvalidColumnException("invalid column " + columnInput + " column must be integer value", e);
        }
    }

    private void printCurrentPlayerWins() {
        printGrid();
        String name = getCurrentPlayerName();
        printStream.println();
        printStream.println("*** " + name + " player wins! ***");
    }

    private void printGrid() {
        printStream.println();
        printStream.println("Current grid state is:");
        printStream.println(connectFour.getGridAsString());
    }

}
