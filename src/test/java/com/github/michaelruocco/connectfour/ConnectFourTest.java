package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ConnectFourTest {

    private static final String NEW_LINE = System.lineSeparator();
    private final ConnectFour connectFour = new ConnectFour();

    @Test
    public void returnsNumberOfRows() {
        assertThat(connectFour.numberOfRows()).isEqualTo(6);
    }

    @Test
    public void returnsNumberOfColumns() {
        assertThat(connectFour.numberOfColumns()).isEqualTo(7);
    }

    @Test
    public void playerOneGoesFirst() {
        assertThat(connectFour.getCurrentPlayerName()).isEqualTo("Player 1");
    }

    @Test
    public void currentPlayerHasntWonAtStart() {
        assertThat(connectFour.currentPlayerHasWon()).isFalse();
    }

    @Test
    public void returnsGridAsString() {
        String expected = "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -";

        assertThat(connectFour.getGridAsString()).isEqualTo(expected);
    }

    @Test
    public void switchesCurrentPlayer() {
        assertThat(connectFour.getCurrentPlayerName()).isEqualTo("Player 1");
        connectFour.switchCurrentPlayer();
        assertThat(connectFour.getCurrentPlayerName()).isEqualTo("Player 2");
        connectFour.switchCurrentPlayer();
        assertThat(connectFour.getCurrentPlayerName()).isEqualTo("Player 1");
        connectFour.switchCurrentPlayer();
        assertThat(connectFour.getCurrentPlayerName()).isEqualTo("Player 2");
    }

    @Test
    public void dropsToken() {
        connectFour.dropToken(1);
        connectFour.dropToken(1);
        connectFour.dropToken(2);
        connectFour.dropToken(7);
        connectFour.dropToken(7);

        String expected = "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "R - - - - - R" + NEW_LINE +
                "R R - - - - R";

        assertThat(connectFour.getGridAsString()).isEqualTo(expected);
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfTokenIsGreaterThanNumberOfColumns() {
        connectFour.dropToken(8);
    }

    @Test
    public void returnsTopOfColumn() {
        int columnIndex = 1;
        assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(0);

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(1);

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(2);

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(3);
    }

    @Test
    public void shouldResetGame() {
        connectFour.dropToken(2);
        connectFour.dropToken(2);
        connectFour.dropToken(2);

        String expected = "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- R - - - - -" + NEW_LINE +
                "- R - - - - -" + NEW_LINE +
                "- R - - - - -";
        assertThat(connectFour.getGridAsString()).isEqualTo(expected);

        connectFour.reset();

        expected = "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -";
        assertThat(connectFour.getGridAsString()).isEqualTo(expected);
    }

    @Test
    public void shouldReturnWhenColumnIsFull() {
        int columnIndex = 2;
        assertThat(connectFour.isColumnFull(columnIndex)).isFalse();

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.isColumnFull(columnIndex)).isFalse();

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.isColumnFull(columnIndex)).isFalse();

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.isColumnFull(columnIndex)).isFalse();

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.isColumnFull(columnIndex)).isFalse();

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.isColumnFull(columnIndex)).isFalse();

        connectFour.dropToken(columnIndex);
        assertThat(connectFour.isColumnFull(columnIndex)).isTrue();
    }

}
