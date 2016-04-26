package com.github.michaelruocco.connectfour;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Scanner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ConnectFourTest {

    private static final String NEW_LINE = System.lineSeparator();
    private final ConnectFour connectFour = new ConnectFour();

    @Test
    public void redPlayerGoesFirst() {
        assertThat(connectFour.getCurrentPlayer().getName()).isEqualTo("Red");
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
        assertThat(connectFour.getCurrentPlayer().getName()).isEqualTo("Red");
        connectFour.switchCurrentPlayer();
        assertThat(connectFour.getCurrentPlayer().getName()).isEqualTo("Yellow");
        connectFour.switchCurrentPlayer();
        assertThat(connectFour.getCurrentPlayer().getName()).isEqualTo("Red");
        connectFour.switchCurrentPlayer();
        assertThat(connectFour.getCurrentPlayer().getName()).isEqualTo("Yellow");
    }

    @Test
    public void dropsToken() {
        connectFour.dropToken("1");
        connectFour.dropToken("1");
        connectFour.dropToken("2");
        connectFour.dropToken("7");
        connectFour.dropToken("7");

        String expected = "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "- - - - - - -" + NEW_LINE +
                "R - - - - - R" + NEW_LINE +
                "R R - - - - R";

        assertThat(connectFour.getGridAsString()).isEqualTo(expected);
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfTokenIsNotInteger() {
        connectFour.dropToken("test");
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfTokenIsGreaterThanNumberOfColumns() {
        connectFour.dropToken("8");
    }

    @Test
    public void returnsTopOfColumn() {
        int columnIndex = 1;
        Assertions.assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(0);

        connectFour.dropToken(Integer.toString(columnIndex));
        Assertions.assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(1);

        connectFour.dropToken(Integer.toString(columnIndex));
        Assertions.assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(2);

        connectFour.dropToken(Integer.toString(columnIndex));
        Assertions.assertThat(connectFour.getTopOfColumn(columnIndex)).isEqualTo(3);
    }

}
