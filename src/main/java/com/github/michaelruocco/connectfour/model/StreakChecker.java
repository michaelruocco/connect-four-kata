package com.github.michaelruocco.connectfour.model;

import java.util.List;

public class StreakChecker {

    private static final int STREAK_SIZE = 4;
    private int streak;

    public boolean containsStreak(List<Token> tokens, Token tokenToFind) {
        resetStreak();
        for (Token token : tokens) {
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