package com.github.michaelruocco.connectfour;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private final List<String> tokens = new ArrayList<>();
    private final StreakChecker checker = new StreakChecker();

    public void add(String token) {
        tokens.add(token);
    }

    public boolean hasWinner(String token) {
        return checker.containsStreak(tokens, token);
    }

    public String asString() {
        StringBuilder s = new StringBuilder();
        for (String token : tokens) {
            s.append(token);
            s.append(" ");
        }
        return s.toString().trim();
    }

}
