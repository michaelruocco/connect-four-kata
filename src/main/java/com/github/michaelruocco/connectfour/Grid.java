package com.github.michaelruocco.connectfour;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Grid {

    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 7;
    private static final String EMPTY_TOKEN = "-";
    private static final String NEW_LINE = System.lineSeparator();

    private final Column[] columns;

    public Grid() {
        columns = new Column[NUMBER_OF_COLUMNS];
        for (int c = 0; c < columns.length; c ++) {
            int id = c + 1;
            columns[c] = new Column(id, NUMBER_OF_ROWS);
        }
    }

    public int numberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public int numberOfColumns() {
        return columns.length;
    }

    public void placeToken(int column, String token) {
        getColumn(column).placeToken(token);
    }

    public String getToken(int column, int row) {
        return getColumn(column).getToken(row);
    }

    public String asString() {
        StringBuilder s = new StringBuilder();
        List<Row> rows = getRows();
        for (Row row : rows) {
            s.append(row.asString());
            s.append(NEW_LINE);
        }
        s.append(NEW_LINE);
        return s.toString();
    }

    private Column getColumn(int column) {
        int index = getColumnIndex(column);
        return columns[index];
    }

    private int getColumnIndex(int column) {
        int index = column - 1;

        if (index < 0)
            throw new InvalidColumnException("column must be between 1 and " + numberOfColumns());

        if (index >= numberOfColumns())
            throw new InvalidColumnException("column must be between 1 and " + numberOfColumns());

        return index;
    }

    private List<Row> getRows() {
        List<Row> rows = new ArrayList<>();
        for (int r = numberOfRows(); r >= 1; r--) {
            Row row = new Row();
            for (int c = 1; c < numberOfColumns() + 1; c++) {
                row.add(getToken(c, r));
            }
            rows.add(row);
        }
        return rows;
    }

    private static class Column {

        private final Stack<String> tokens = new Stack<>();
        private final int id;
        private final int numberOfRows;

        public Column(int id, int numberOfRows) {
            this.id = id;
            this.numberOfRows = numberOfRows;
        }

        public void placeToken(String token) {
            if (tokens.size() >= numberOfRows)
                throw new ColumnFullException("column " + id + " is already full");
            tokens.add(token);
        }

        public String getToken(int row) {
            int index = getRowIndex(row);
            if (tokens.size() <= index) {
                return EMPTY_TOKEN;
            }
            return tokens.get(index);
        }

        private int getRowIndex(int row) {
            return row - 1;
        }

    }

    private static class Row {

        private final List<String> tokens = new ArrayList<>();

        public void add(String token) {
            tokens.add(token);
        }

        private String asString() {
            StringBuilder s = new StringBuilder();
            for (String token : tokens) {
                s.append(token);
                s.append(" ");
            }
            return s.toString().trim();
        }

    }

}
