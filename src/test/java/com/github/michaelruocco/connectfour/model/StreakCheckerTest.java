package com.github.michaelruocco.connectfour.model;

import com.github.michaelruocco.connectfour.model.RedToken;
import com.github.michaelruocco.connectfour.model.StreakChecker;
import com.github.michaelruocco.connectfour.model.Token;
import com.github.michaelruocco.connectfour.model.YellowToken;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StreakCheckerTest {

    private static final Token RED_TOKEN = new RedToken();

    private final StreakChecker streakChecker = new StreakChecker();

    @Test
    public void shouldReturnFalseIfNotTokensPassed() {
        List<Token> tokens = Collections.emptyList();

        assertThat(streakChecker.containsStreak(tokens, RED_TOKEN)).isFalse();
    }

    @Test
    public void shouldReturnFalseIfFourConsecutiveTokensNotFound() {
        List<Token> tokens = Arrays.asList(RED_TOKEN,
                RED_TOKEN,
                RED_TOKEN);

        assertThat(streakChecker.containsStreak(tokens, RED_TOKEN)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfFourConsecutiveTokensFound() {
        List<Token> tokens = Arrays.asList(RED_TOKEN,
                RED_TOKEN,
                RED_TOKEN,
                RED_TOKEN);

        assertThat(streakChecker.containsStreak(tokens, RED_TOKEN)).isTrue();
    }

    @Test
    public void shouldReturnTrueIfFourConsecutiveTokensFoundAfterDifferentTokenFound() {
        List<Token> tokens = Arrays.asList(RED_TOKEN,
                new YellowToken(),
                RED_TOKEN,
                RED_TOKEN,
                RED_TOKEN,
                RED_TOKEN);

        assertThat(streakChecker.containsStreak(tokens, RED_TOKEN)).isTrue();
    }


}
