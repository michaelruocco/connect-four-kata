package com.github.michaelruocco.connectfour.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class GridTest {

    private static final int MAX_COLUMNS = 7;
    private static final int MAX_ROWS = 6;
    private static final Token EMPTY_TOKEN = new EmptyToken();
    private static final Token RED_TOKEN = new RedToken();
    private static final Token YELLOW_TOKEN = new YellowToken();
    private static final String NEW_LINE = System.lineSeparator();

    private Grid grid = new Grid(MAX_ROWS, MAX_COLUMNS);

    @Test
    public void gridHasMaxRows() {
        assertThat(grid.numberOfRows()).isEqualTo(MAX_ROWS);
    }

    @Test
    public void gridHasMaxColumns() {
        assertThat(grid.numberOfColumns()).isEqualTo(MAX_COLUMNS);
    }

    @Test
    public void canDropTokenIntoGrid() {
        int column = 1;
        dropTokensInColumn(RED_TOKEN, column, 1);

        assertThat(grid.getToken(column, 1)).isEqualTo(RED_TOKEN);
    }

    @Test
    public void canStackTokens() {
        int column = MAX_COLUMNS;
        dropTokensInColumn(RED_TOKEN, column, 3);

        assertThat(grid.getToken(column, 1)).isEqualTo(RED_TOKEN);
        assertThat(grid.getToken(column, 2)).isEqualTo(RED_TOKEN);
        assertThat(grid.getToken(column, 3)).isEqualTo(RED_TOKEN);
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfColumnIndexLessThanOne() {
        grid.dropToken(0, RED_TOKEN);
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfColumnIndexGreaterThanMaxColumns() {
        int column = MAX_COLUMNS + 1;
        grid.dropToken(column, RED_TOKEN);
    }

    @Test
    public void canFillColumn() {
        int column = 1;
        dropTokensInColumn(RED_TOKEN, column, MAX_ROWS);
        assertThat(grid.isColumnFull(column)).isTrue();
    }

    @Test(expected = ColumnFullException.class)
    public void throwsErrorWhenTokenIsDroppedIntoAFullColumn() {
        int column = 1;
        dropTokensInColumn(RED_TOKEN, column, MAX_ROWS + 1);
        fail("expected ColumnFullException to be thrown");
    }

    @Test
    public void returnsEmptyTokenIfSlotIsEmpty() {
        assertThat(grid.getToken(1, 1)).isEqualTo(EMPTY_TOKEN);
    }

    @Test
    public void returnsGridStateAsString() {
        dropTokensInColumn(RED_TOKEN, 2, 5);
        dropTokensInColumn(YELLOW_TOKEN, 3, 4);
        dropTokensInColumn(RED_TOKEN, 5, 2);
        dropTokensInColumn(YELLOW_TOKEN, 5, 2);

        StringBuilder expected = new StringBuilder();
        expected.append("- - - - - - -");
        expected.append(NEW_LINE);
        expected.append("- R - - - - -");
        expected.append(NEW_LINE);
        expected.append("- R Y - Y - -");
        expected.append(NEW_LINE);
        expected.append("- R Y - Y - -");
        expected.append(NEW_LINE);
        expected.append("- R Y - R - -");
        expected.append(NEW_LINE);
        expected.append("- R Y - R - -");

        assertThat(grid.asString()).isEqualTo(expected.toString());
    }

    @Test
    public void returnsTopOfColumn() {
        int columnIndex = 1;
        assertThat(grid.getTopOfColumn(columnIndex)).isEqualTo(0);

        grid.dropToken(columnIndex, RED_TOKEN);
        assertThat(columnIndex).isEqualTo(1);

        grid.dropToken(columnIndex, RED_TOKEN);
        assertThat(grid.getTopOfColumn(columnIndex)).isEqualTo(2);

        grid.dropToken(columnIndex, RED_TOKEN);
        assertThat(grid.getTopOfColumn(columnIndex)).isEqualTo(3);
    }

    private void dropTokensInColumn(Token token, int column, int numberOfTokens) {
        for (int t = 0; t < numberOfTokens; t++) {
            grid.dropToken(column, token);
        }
    }

}