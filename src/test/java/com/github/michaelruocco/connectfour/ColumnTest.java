package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ColumnTest {

    private static final int ID = 1;
    private static final int SIZE = 6;
    private static final String TOKEN = "T";

    private final Column column = new Column(ID, SIZE);

    @Test
    public void shouldReturnId() {
        assertThat(column.getId()).isEqualTo(ID);
    }

    @Test
    public void initialColumnShouldBeEmpty() {
        assertThat(column.getTop()).isEqualTo(0);
    }

    @Test
    public void initialColumnShouldNotHaveAWinner() {
        assertThat(column.hasWinner(TOKEN)).isFalse();
    }

    @Test
    public void initialColumnShouldNotBeFull() {
        assertThat(column.isFull()).isFalse();
    }

    @Test(expected =  ArrayIndexOutOfBoundsException.class)
    public void shouldExceptionIfRowLessThanOneReferenced() {
        column.getToken(0);
    }

    @Test
    public void shouldReturnEmptyTokenIfNoTokenPresentOnRow() {
        assertThat(column.getToken(1)).isEqualTo("-");
    }

    @Test
    public void shouldReturnTokenIfPresent() {
        column.dropToken(TOKEN);
        column.dropToken(TOKEN);

        assertThat(column.getToken(1)).isEqualTo(TOKEN);
        assertThat(column.getToken(2)).isEqualTo(TOKEN);
    }

    @Test
    public void shouldBeFullWhenTokenDroppedOnEveryRow() {
        fillColumn(TOKEN);

        assertThat(column.isFull()).isTrue();
    }

    @Test(expected = ColumnFullException.class)
    public void shouldThrowColumnFullExceptionIfTokenIsDroppedWhenFull() {
        fillColumn(TOKEN);

        column.dropToken(TOKEN);
    }

    @Test
    public void shouldReturnWinnerIfContainsFourOfTheSameConsecutiveTokens() {
        column.dropToken(TOKEN);
        column.dropToken(TOKEN);
        column.dropToken(TOKEN);
        assertThat(column.hasWinner(TOKEN)).isFalse();

        column.dropToken(TOKEN);
        assertThat(column.hasWinner(TOKEN)).isTrue();
    }

    @Test
    public void shouldNotReturnWinnerIfContainsFourDifferentTokens() {
        column.dropToken(TOKEN);
        column.dropToken(TOKEN);
        column.dropToken(TOKEN);
        assertThat(column.hasWinner(TOKEN)).isFalse();

        column.dropToken("DIFFERENT TOKEN");
        assertThat(column.hasWinner(TOKEN)).isFalse();
    }


    private void fillColumn(String token) {
        for (int i = 0; i < SIZE; i++) {
            column.dropToken(token);
        }
    }

}
