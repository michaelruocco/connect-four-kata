package com.github.michaelruocco.connectfour;

import java.util.Stack;

public class Grid {

    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 7;

    private final Column[] columns;

    public Grid() {
        columns = new Column[NUMBER_OF_COLUMNS];
        for (int c = 0; c < columns.length; c ++) {
            columns[c] = new Column();
        }
    }

    public int numberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public int numberOfColumns() {
        return NUMBER_OF_COLUMNS;
    }

    public void placeToken(int column, String token) {
        getColumn(column).placeToken(token);
    }

    public String getToken(int column, int row) {
        return getColumn(column).getToken(row);
    }

    private Column getColumn(int column) {
        return columns[column];
    }

    private static class Column {

        private final Stack<String> tokens = new Stack<>();

        public void placeToken(String token) {
            tokens.add(token);
        }

        public String getToken(int row) {
            return tokens.get(row);
        }

    }

}
