package com.github.michaelruocco.connectfour;

import java.io.PrintStream;
import java.util.Scanner;

public class ConnectFour {

    private static final Player RED_PLAYER = new Player("Red", "R");
    private static final Player YELLOW_PLAYER = new Player("Yellow", "Y");

    private final Grid grid = new Grid();
    private final Scanner scanner;
    private final PrintStream printStream;

    private Player currentPlayer = RED_PLAYER;

    public ConnectFour() {
        this(new Scanner(System.in), System.out);
    }

    public ConnectFour(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    public void play() {
        while(!currentPlayerHasWon()) {
            playRound();
        }
    }

    public boolean currentPlayerHasWon() {
        return grid.hasWinner(currentPlayer.getToken());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void playRound() {
        if (!takeTurn())
            return;

        if (currentPlayerHasWon()) {
            printCurrentPlayerWins();
            return;
        }

        switchCurrentPlayer();
    }

    private boolean takeTurn() {
        printGrid();
        printPlayerPrompt();
        String column = getColumnInput();
        return dropToken(column);
    }

    private void printCurrentPlayerWins() {
        printGrid();
        String name = currentPlayer.getName();
        printStream.println();
        printStream.println("*** " + name + " player wins! ***");
    }

    private void printGrid() {
        printStream.println();
        printStream.println("Current grid state is:");
        printStream.println(grid.asString());
    }

    private void switchCurrentPlayer() {
        if (currentPlayer.equals(RED_PLAYER))
            currentPlayer = YELLOW_PLAYER;
        else
            currentPlayer = RED_PLAYER;
    }

    private void printPlayerPrompt() {
        String name = currentPlayer.getName();
        printStream.print(name + " player input column to drop token ");
    }

    private String getColumnInput() {
        return scanner.next();
    }

    private boolean dropToken(String column) {
        try {
            String token = currentPlayer.getToken();
            grid.dropToken(column, token);
            return true;
        } catch (ConnectFourException e) {
            printStream.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static class Player {

        private final String name;
        private final String token;

        public Player(String name, String token) {
            this.name = name;
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public String getToken() {
            return token;
        }

    }
}
