package com.github.michaelruocco.connectfour.model;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static final String NEW_LINE = System.lineSeparator();

    private final StreakChecker checker = new StreakChecker();
    private final int numberOfRows;
    private final Column[] columns;
    private Column lastDroppedColumn;

    public Grid(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        columns = new Column[numberOfColumns];
        reset();
    }

    public int numberOfRows() {
        return numberOfRows;
    }

    public int numberOfColumns() {
        return columns.length;
    }

    public void dropToken(int column, Token token) {
        this.lastDroppedColumn = getColumn(column);
        lastDroppedColumn.dropToken(token);
    }

    public int getTopOfColumn(int index) {
        Column column = getColumn(index);
        return column.getTop();
    }

    public boolean isColumnFull(int index) {
        Column column = getColumn(index);
        return column.isFull();
    }

    public Token getToken(int column, int row) {
        return getColumn(column).getToken(row);
    }

    public String asString() {
        StringBuilder s = new StringBuilder();
        List<Row> rows = getRows();
        for (Row row : rows) {
            s.append(row.asString());
            s.append(NEW_LINE);
        }
        return StringUtils.chomp(s.toString());
    }

    public boolean hasWinner(Token token) {
        if (noTokensDropped())
            return false;

        if (hasVerticalWinner(token))
            return true;

        if (hasHorizontalWinner(token))
            return true;

        if (hasForwardSlashDiagonalWinner(token))
            return true;

        return hasBackSlashDiagonalWinner(token);

    }

    public void reset() {
        lastDroppedColumn = null;
        for (int c = 0; c < columns.length; c++) {
            int id = c + 1;
            columns[c] = new Column(id, numberOfRows);
        }
    }

    private boolean hasVerticalWinner(Token token) {
        return lastDroppedColumn.hasWinner(token);

    }

    private boolean hasHorizontalWinner(Token token) {
        Row row = getLastDroppedRow();
        return row.hasWinner(token);
    }

    private boolean hasForwardSlashDiagonalWinner(Token token) {
        List<Token> tokens = getForwardSlashDiagonalTokensFromLastDroppedColumn();
        return checker.containsStreak(tokens, token);
    }

    private boolean hasBackSlashDiagonalWinner(Token token) {
        List<Token> tokens = getBackSlashDiagonalTokensFromLastDroppedColumn();
        return checker.containsStreak(tokens, token);
    }

    private List<Token> getForwardSlashDiagonalTokensFromLastDroppedColumn() {
        Point startOfDiagonal = getStartOfForwardSlashDiagonalFromLastDroppedColumn();
        return getForwardSlashTokensFrom(startOfDiagonal);
    }

    private List<Token> getBackSlashDiagonalTokensFromLastDroppedColumn() {
        Point startOfDiagonal = getStartOfBackSlashDiagonalFromLastDroppedColumn();
        return getBackSlashTokensFrom(startOfDiagonal);
    }

    private Point getStartOfForwardSlashDiagonalFromLastDroppedColumn() {
        int row = lastDroppedColumn.getTop();
        int col = lastDroppedColumn.getId();
        while (row > 1 && col > 1) {
            row--;
            col--;
        }
        return new Point(row, col);
    }

    private Point getStartOfBackSlashDiagonalFromLastDroppedColumn() {
        int row = lastDroppedColumn.getTop();
        int col = lastDroppedColumn.getId();
        while (row < numberOfRows() && col > 1) {
            row++;
            col--;
        }
        return new Point(row, col);
    }

    private List<Token> getForwardSlashTokensFrom(Point point) {
        int row = point.x;
        int col = point.y;

        List<Token> tokens = new ArrayList<>();
        do {
            tokens.add(getToken(col, row));
            row++;
            col++;
        } while (row <= numberOfRows() && col <= numberOfColumns());

        return tokens;
    }

    private List<Token> getBackSlashTokensFrom(Point point) {
        int row = point.x;
        int col = point.y;

        List<Token> tokens = new ArrayList<>();
        do {
            tokens.add(getToken(col, row));
            row--;
            col++;
        } while (row >= 1 && col <= numberOfColumns());

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

}
