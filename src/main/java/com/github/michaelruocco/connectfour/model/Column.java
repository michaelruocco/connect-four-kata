package com.github.michaelruocco.connectfour.model;

import java.util.Stack;

import static java.awt.Color.WHITE;

public class Column {

    private static final Token EMPTY_TOKEN = new Token(WHITE, "-");

    private final Stack<Token> tokens = new Stack<>();
    private final StreakChecker checker = new StreakChecker();
    private final int id;
    private final int size;

    public Column(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void dropToken(Token token) {
        if (isFull())
            throw new ColumnFullException("column " + id + " is already full");
        tokens.add(token);
    }

    public boolean isFull() {
        return tokens.size() >= size;
    }

    public Token getToken(int row) {
        int index = getRowIndex(row);
        if (tokens.size() <= index) {
            return EMPTY_TOKEN;
        }
        return tokens.get(index);
    }

    public boolean hasWinner(Token token) {
        return checker.containsStreak(tokens, token);
    }

    public int getTop() {
        return tokens.size();
    }

    private int getRowIndex(int row) {
        return row - 1;
    }

}
