package com.github.michaelruocco.connectfour.model;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private final List<Token> tokens = new ArrayList<>();
    private final StreakChecker checker = new StreakChecker();

    public void add(Token token) {
        tokens.add(token);
    }

    public boolean hasWinner(Token token) {
        return checker.containsStreak(tokens, token);
    }

    public String asString() {
        StringBuilder s = new StringBuilder();
        for (Token token : tokens) {
            s.append(token.toString());
            s.append(" ");
        }
        return s.toString().trim();
    }

}
