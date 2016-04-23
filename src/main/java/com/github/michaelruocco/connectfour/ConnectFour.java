package com.github.michaelruocco.connectfour;

import java.util.Scanner;

public class ConnectFour {

    private static final Player RED_PLAYER = new Player("Red", "R");
    private static final Player YELLOW_PLAYER = new Player("Yellow", "Y");

    private final Scanner scanner = new Scanner(System.in);
    private final Grid grid = new Grid();

    private Player currentPlayer = RED_PLAYER;

    public void play() {
        while(!currentPlayerHasWon()) {
            playRound();
        }
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

    private boolean currentPlayerHasWon() {
        return grid.hasWinner(currentPlayer.getToken());
    }

    private void printCurrentPlayerWins() {
        printGrid();
        String name = currentPlayer.getName();
        System.out.println();
        System.out.println("*** " + name + " player wins! ***");
    }

    private void printGrid() {
        System.out.println();
        System.out.println("Current grid state is:");
        System.out.println(grid.asString());
    }

    private void switchCurrentPlayer() {
        if (currentPlayer.equals(RED_PLAYER))
            currentPlayer = YELLOW_PLAYER;
        else
            currentPlayer = RED_PLAYER;
    }

    private void printPlayerPrompt() {
        String name = currentPlayer.getName();
        System.out.print(name + " player input column to drop token ");
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
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private static class Player {

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
