package com.github.michaelruocco.connectfour.model;

import com.github.michaelruocco.connectfour.model.RedToken;
import com.github.michaelruocco.connectfour.model.Row;
import com.github.michaelruocco.connectfour.model.Token;
import com.github.michaelruocco.connectfour.model.YellowToken;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RowTest {

    private static final Token RED_TOKEN = new RedToken();

    private Row row = new Row();

    @Test
    public void initialRowShouldNotHaveAWinner() {
        assertThat(row.hasWinner(RED_TOKEN)).isFalse();
    }

    @Test
    public void initialRowShouldBeEmpty() {
        assertThat(row.asString()).isEmpty();
    }

    @Test
    public void shouldAddToken() {
        row.add(RED_TOKEN);

        assertThat(row.asString()).isEqualTo(RED_TOKEN.toString());
    }

    @Test
    public void shouldAddMultipleTokens() {
        row.add(RED_TOKEN);
        row.add(RED_TOKEN);
        row.add(RED_TOKEN);

        assertThat(row.asString()).isEqualTo(RED_TOKEN + " " + RED_TOKEN + " " + RED_TOKEN);
    }

    @Test
    public void shouldReturnWinnerIfContainsFourOfTheSameConsecutiveTokens() {
        row.add(RED_TOKEN);
        row.add(RED_TOKEN);
        row.add(RED_TOKEN);
        assertThat(row.hasWinner(RED_TOKEN)).isFalse();

        row.add(RED_TOKEN);
        assertThat(row.hasWinner(RED_TOKEN)).isTrue();
    }

    @Test
    public void shouldNotReturnWinnerIfContainsFourDifferentTokens() {
        row.add(RED_TOKEN);
        row.add(RED_TOKEN);
        row.add(RED_TOKEN);
        assertThat(row.hasWinner(RED_TOKEN)).isFalse();

        row.add(new YellowToken());
        assertThat(row.hasWinner(RED_TOKEN)).isFalse();
    }

}
