package com.github.michaelruocco.connectfour.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static final String NEW_LINE = System.lineSeparator();

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

    public boolean isEmpty() {
        return lastDroppedColumn == null;
    }

    public Column getLastDroppedColumn() {
        return lastDroppedColumn;
    }

    public Row getLastDroppedRow() {
        return getRow(lastDroppedColumn.getTop());
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
        GridChecker gridChecker = new GridChecker(this);
        return gridChecker.hasWinner(token);
    }

    public void reset() {
        lastDroppedColumn = null;
        for (int c = 0; c < columns.length; c++) {
            int id = c + 1;
            columns[c] = new Column(id, numberOfRows);
        }
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

}
