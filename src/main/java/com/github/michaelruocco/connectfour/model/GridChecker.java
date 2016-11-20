package com.github.michaelruocco.connectfour.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridChecker {

    private final StreakChecker checker = new StreakChecker();

    private Grid grid;

    public GridChecker(Grid grid) {
        this.grid = grid;
    }

    public boolean hasWinner(Token token) {
        if (grid.isEmpty())
            return false;

        if (hasVerticalWinner(token))
            return true;

        if (hasHorizontalWinner(token))
            return true;

        if (hasForwardSlashDiagonalWinner(token))
            return true;

        return hasBackSlashDiagonalWinner(token);
    }

    private boolean hasVerticalWinner(Token token) {
        Column lastDroppedColumn = grid.getLastDroppedColumn();
        return lastDroppedColumn.hasWinner(token);
    }

    private boolean hasHorizontalWinner(Token token) {
        Row row = grid.getLastDroppedRow();
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
        Column lastDroppedColumn = grid.getLastDroppedColumn();
        int row = lastDroppedColumn.getTop();
        int col = lastDroppedColumn.getId();
        while (row > 1 && col > 1) {
            row--;
            col--;
        }
        return new Point(row, col);
    }

    private Point getStartOfBackSlashDiagonalFromLastDroppedColumn() {
        Column lastDroppedColumn = grid.getLastDroppedColumn();
        int row = lastDroppedColumn.getTop();
        int col = lastDroppedColumn.getId();
        while (row < grid.numberOfRows() && col > 1) {
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
            tokens.add(grid.getToken(col, row));
            row++;
            col++;
        } while (row <= grid.numberOfRows() && col <= grid.numberOfColumns());

        return tokens;
    }

    private List<Token> getBackSlashTokensFrom(Point point) {
        int row = point.x;
        int col = point.y;

        List<Token> tokens = new ArrayList<>();
        do {
            tokens.add(grid.getToken(col, row));
            row--;
            col++;
        } while (row >= 1 && col <= grid.numberOfColumns());

        return tokens;
    }


}
