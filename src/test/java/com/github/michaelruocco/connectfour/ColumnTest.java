package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ColumnTest {

    private static final int ID = 1;
    private static final int SIZE = 6;
    private static final Token RED_TOKEN = new RedToken();
    private static final Token EMPTY_TOKEN = new EmptyToken();

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
        assertThat(column.hasWinner(RED_TOKEN)).isFalse();
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
        assertThat(column.getToken(1)).isEqualTo(EMPTY_TOKEN);
    }

    @Test
    public void shouldReturnTokenIfPresent() {
        column.dropToken(RED_TOKEN);
        column.dropToken(RED_TOKEN);

        assertThat(column.getToken(1)).isEqualTo(RED_TOKEN);
        assertThat(column.getToken(2)).isEqualTo(RED_TOKEN);
    }

    @Test
    public void shouldBeFullWhenTokenDroppedOnEveryRow() {
        fillColumn(RED_TOKEN);

        assertThat(column.isFull()).isTrue();
    }

    @Test(expected = ColumnFullException.class)
    public void shouldThrowColumnFullExceptionIfTokenIsDroppedWhenFull() {
        fillColumn(RED_TOKEN);

        column.dropToken(RED_TOKEN);
    }

    @Test
    public void shouldReturnWinnerIfContainsFourOfTheSameConsecutiveTokens() {
        column.dropToken(RED_TOKEN);
        column.dropToken(RED_TOKEN);
        column.dropToken(RED_TOKEN);
        assertThat(column.hasWinner(RED_TOKEN)).isFalse();

        column.dropToken(RED_TOKEN);
        assertThat(column.hasWinner(RED_TOKEN)).isTrue();
    }

    @Test
    public void shouldNotReturnWinnerIfContainsFourDifferentTokens() {
        column.dropToken(RED_TOKEN);
        column.dropToken(RED_TOKEN);
        column.dropToken(RED_TOKEN);
        assertThat(column.hasWinner(RED_TOKEN)).isFalse();

        column.dropToken(new YellowToken());
        assertThat(column.hasWinner(RED_TOKEN)).isFalse();
    }


    private void fillColumn(Token token) {
        for (int i = 0; i < SIZE; i++) {
            column.dropToken(token);
        }
    }

}
