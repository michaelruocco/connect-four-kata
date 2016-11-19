package com.github.michaelruocco.connectfour;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StreakCheckerTest {

    private static final String TOKEN_TO_FIND = "T";

    private final StreakChecker streakChecker = new StreakChecker();

    @Test
    public void shouldReturnFalseIfNotTokensPassed() {
        List<String> tokens = Collections.emptyList();

        assertThat(streakChecker.containsStreak(tokens, TOKEN_TO_FIND)).isFalse();
    }

    @Test
    public void shouldReturnFalseIfFourConsecutiveTokensNotFound() {
        List<String> tokens = Arrays.asList(TOKEN_TO_FIND,
                TOKEN_TO_FIND,
                TOKEN_TO_FIND);

        assertThat(streakChecker.containsStreak(tokens, TOKEN_TO_FIND)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfFourConsecutiveTokensFound() {
        List<String> tokens = Arrays.asList(TOKEN_TO_FIND,
                TOKEN_TO_FIND,
                TOKEN_TO_FIND,
                TOKEN_TO_FIND);

        assertThat(streakChecker.containsStreak(tokens, TOKEN_TO_FIND)).isTrue();
    }

    @Test
    public void shouldReturnTrueIfFourConsecutiveTokensFoundAfterDifferentTokenFound() {
        List<String> tokens = Arrays.asList(TOKEN_TO_FIND,
                "Different Token",
                TOKEN_TO_FIND,
                TOKEN_TO_FIND,
                TOKEN_TO_FIND,
                TOKEN_TO_FIND);

        assertThat(streakChecker.containsStreak(tokens, TOKEN_TO_FIND)).isTrue();
    }


}
