package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RowTest {

    private static final String TOKEN = "T";

    private Row row = new Row();

    @Test
    public void initialRowShouldNotHaveAWinner() {
        assertThat(row.hasWinner(TOKEN)).isFalse();
    }

    @Test
    public void initialRowShouldBeEmpty() {
        assertThat(row.asString()).isEmpty();
    }

    @Test
    public void shouldAddToken() {
        row.add(TOKEN);

        assertThat(row.asString()).isEqualTo(TOKEN);
    }

    @Test
    public void shouldAddMultipleTokens() {
        row.add(TOKEN);
        row.add(TOKEN);
        row.add(TOKEN);

        assertThat(row.asString()).isEqualTo(TOKEN + " " + TOKEN + " " + TOKEN);
    }

    @Test
    public void shouldReturnWinnerIfContainsFourOfTheSameConsecutiveTokens() {
        row.add(TOKEN);
        row.add(TOKEN);
        row.add(TOKEN);
        assertThat(row.hasWinner(TOKEN)).isFalse();

        row.add(TOKEN);
        assertThat(row.hasWinner(TOKEN)).isTrue();
    }

    @Test
    public void shouldNotReturnWinnerIfContainsFourDifferentTokens() {
        row.add(TOKEN);
        row.add(TOKEN);
        row.add(TOKEN);
        assertThat(row.hasWinner(TOKEN)).isFalse();

        row.add("DIFFERENT TOKEN");
        assertThat(row.hasWinner(TOKEN)).isFalse();
    }

}
