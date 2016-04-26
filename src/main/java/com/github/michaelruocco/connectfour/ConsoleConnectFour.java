package com.github.michaelruocco.connectfour;

import java.io.PrintStream;
import java.util.Scanner;

import static com.github.michaelruocco.connectfour.ConnectFour.*;

public class ConsoleConnectFour {

    public static void main(String[] args) {
        ConsoleConnectFour console = new ConsoleConnectFour(new Scanner(System.in), System.out);
        console.play();
    }

    private final ConnectFour connectFour = new ConnectFour();
    private final Scanner scanner;
    private final PrintStream printStream;

    public ConsoleConnectFour(Scanner scanner, PrintStream printStream) {
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
            String column = getColumnInput();
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

    private String getColumnInput() {
        return scanner.next();
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
