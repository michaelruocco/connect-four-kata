package com.github.michaelruocco.connectfour;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private final List<String> tokens = new ArrayList<>();

    public void add(String token) {
        tokens.add(token);
    }

    public boolean hasWinner(String token) {
        StreakChecker checker = new StreakChecker(tokens);
        return checker.containsStreak(token);
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
