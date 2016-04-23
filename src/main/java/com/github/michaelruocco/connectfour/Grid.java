package com.github.michaelruocco.connectfour;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Grid {

    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 7;
    private static final String EMPTY_TOKEN = "-";
    private static final String NEW_LINE = System.lineSeparator();

    private final Column[] columns;
    private Column lastDroppedColumn;

    public Grid() {
        columns = new Column[NUMBER_OF_COLUMNS];
        reset();
    }

    public int numberOfRows() {
        return NUMBER_OF_ROWS;
    }

    public int numberOfColumns() {
        return columns.length;
    }

    public void dropToken(int column, String token) {
        this.lastDroppedColumn = getColumn(column);
        lastDroppedColumn.placeToken(token);
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
        return s.toString();
    }

    public boolean hasWinner(String token) {
        if (noTokensDropped())
            return false;

        if (hasVerticalWinner(token))
            return true;

        if (hasHorizontalWinner(token))
            return true;

        if (hasForwardSlashDiagonalWinner(token))
            return true;

        if (hasBackSlashDiagonalWinner(token))
            return true;

        return false;
    }

    public void reset() {
        lastDroppedColumn = null;
        for (int c = 0; c < columns.length; c ++) {
            int id = c + 1;
            columns[c] = new Column(id, NUMBER_OF_ROWS);
        }
    }

    private boolean hasVerticalWinner(String token) {
        if (lastDroppedColumn.hasWinner(token))
            return true;

        return false;
    }

    private boolean hasHorizontalWinner(String token) {
        Row row = getLastDroppedRow();
        if (row.hasWinner(token))
            return true;

        return false;
    }

    private boolean hasForwardSlashDiagonalWinner(String token) {
        List<String> tokens = getForwardSlashDiagonalTokensFromLastDroppedColumn();
        StreakChecker checker = new StreakChecker(tokens);
        return checker.containsStreak(token);
    }

    private boolean hasBackSlashDiagonalWinner(String token) {
        List<String> tokens = getBackSlashDiagonalTokensFromLastDroppedColumn();
        StreakChecker checker = new StreakChecker(tokens);
        return checker.containsStreak(token);
    }

    private List<String> getForwardSlashDiagonalTokensFromLastDroppedColumn() {
        Point startOfDiagonal = getStartOfForwardSlashDiagonalFromLastDroppedColumn();
        return getForwardSlashTokensFrom(startOfDiagonal);
    }

    private List<String> getBackSlashDiagonalTokensFromLastDroppedColumn() {
        Point startOfDiagonal = getStartOfBackSlashDiagonalFromLastDroppedColumn();
        return getBackSlashTokensFrom(startOfDiagonal);
    }

    private Point getStartOfForwardSlashDiagonalFromLastDroppedColumn() {
        int row = lastDroppedColumn.getTop();
        int col = lastDroppedColumn.getId();
        while(row > 1 && col > 1) {
            row--;
            col--;
        }
        return new Point(row, col);
    }

    private Point getStartOfBackSlashDiagonalFromLastDroppedColumn() {
        int row = lastDroppedColumn.getTop();
        int col = lastDroppedColumn.getId();
        while(row < numberOfRows() && col > 1) {
            row++;
            col--;
        }
        return new Point(row, col);
    }

    private List<String> getForwardSlashTokensFrom(Point point) {
        int row = point.x;
        int col = point.y;

        List<String> tokens = new ArrayList<>();
        do {
            tokens.add(getToken(col, row));
            row++;
            col++;
        } while(row <= numberOfRows() && col <= numberOfColumns());

        return tokens;
    }

    private List<String> getBackSlashTokensFrom(Point point) {
        int row = point.x;
        int col = point.y;

        List<String> tokens = new ArrayList<>();
        do {
            tokens.add(getToken(col, row));
            row--;
            col++;
        } while(row >= 1 && col <= numberOfColumns());

        return tokens;
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
            Row row = getRow(r);
            rows.add(row);
        }
        return rows;
    }

    private Row getRow(int r) {
        Row row = new Row();
        for (int c = 1; c < numberOfColumns() + 1; c++)
            row.add(getToken(c, r));
        return row;
    }

    private Row getLastDroppedRow() {
        return getRow(lastDroppedColumn.getTop());
    }

    private boolean noTokensDropped() {
        return lastDroppedColumn == null;
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

        public boolean hasWinner(String token) {
            StreakChecker checker = new StreakChecker(tokens);
            return checker.containsStreak(token);
        }

        public int getTop() {
            return tokens.size();
        }

        public int getId() {
            return id;
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

        public boolean hasWinner(String token) {
            StreakChecker checker = new StreakChecker(tokens);
            return checker.containsStreak(token);
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

    private static class StreakChecker {

        private static final int STREAK_SIZE = 4;
        private final List<String> tokens;
        private int streak;

        public StreakChecker(List<String> tokens) {
            this.tokens = tokens;
            resetStreak();
        }

        public boolean containsStreak(String tokenToFind) {
            resetStreak();
            for (String token : tokens) {
                if (token.equals(tokenToFind)) {
                    incrementStreak();
                    if (hasStreak()) {
                        return true;
                    }
                } else {
                    resetStreak();
                }
            }
            return false;
        }

        private boolean hasStreak() {
            return streak >= STREAK_SIZE;
        }

        private void resetStreak() {
            streak = 0;
        }

        private void incrementStreak() {
            streak++;
        }

    }

}
