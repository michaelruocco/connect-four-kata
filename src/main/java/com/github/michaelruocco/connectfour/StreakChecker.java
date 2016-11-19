package com.github.michaelruocco.connectfour;

import java.util.List;

public class StreakChecker {

    private static final int STREAK_SIZE = 4;
    private int streak;

    public boolean containsStreak(List<String> tokens, String tokenToFind) {
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