package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {

    private static final int MAX_COLUMNS = 7;
    private static final int MAX_ROWS = 6;
    private static final String EMPTY_TOKEN = "-";
    private static final String RED_TOKEN = "R";
    private static final String YELLOW_TOKEN = "Y";
    private static final String NEW_LINE = System.lineSeparator();

    private Grid grid = new Grid();

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
        grid.dropToken(MAX_COLUMNS + 1, RED_TOKEN);
    }

    @Test
    public void canFillColumn() {
        int column = 1;
        dropTokensInColumn(RED_TOKEN, column, MAX_ROWS);
    }

    @Test(expected = ColumnFullException.class)
    public void throwsErrorWhenTokenIsDroppedIntoAFullColumn() {
        int column = 1;
        dropTokensInColumn(RED_TOKEN, column, MAX_ROWS + 1);
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
        expected.append(NEW_LINE);
        expected.append(NEW_LINE);

        assertThat(grid.asString()).isEqualTo(expected.toString());
    }

    @Test
    public void returnsNoWinnerIfGridIsEmpty() {
        assertThat(grid.hasWinner(RED_TOKEN)).isFalse();
    }

    @Test
    public void returnsVerticalWinner() {
        dropTokensInColumn(RED_TOKEN, 1, 4);
        assertThat(grid.hasWinner(RED_TOKEN)).isTrue();
        grid.reset();

        dropTokensInColumn(YELLOW_TOKEN, 6, 2);
        assertThat(grid.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(RED_TOKEN, 6, 4);
        assertThat(grid.hasWinner(RED_TOKEN)).isTrue();
    }

    @Test
    public void returnsHorizontalWinner() {
        dropTokensInColumn(RED_TOKEN, 1, 1);
        dropTokensInColumn(RED_TOKEN, 2, 1);
        dropTokensInColumn(RED_TOKEN, 3, 1);
        dropTokensInColumn(RED_TOKEN, 4, 1);
        assertThat(grid.hasWinner(RED_TOKEN)).isTrue();
        grid.reset();

        dropTokensInColumn(RED_TOKEN, 3, 1);
        dropTokensInColumn(YELLOW_TOKEN, 4, 1);
        dropTokensInColumn(RED_TOKEN, 5, 1);
        dropTokensInColumn(YELLOW_TOKEN, 6, 1);
        assertThat(grid.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(RED_TOKEN, 3, 1);
        dropTokensInColumn(YELLOW_TOKEN, 4, 1);
        dropTokensInColumn(RED_TOKEN, 5, 1);
        dropTokensInColumn(YELLOW_TOKEN, 6, 1);
        assertThat(grid.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(YELLOW_TOKEN, 3, 1);
        dropTokensInColumn(YELLOW_TOKEN, 4, 1);
        dropTokensInColumn(YELLOW_TOKEN, 5, 1);
        dropTokensInColumn(YELLOW_TOKEN, 6, 1);
        assertThat(grid.hasWinner(YELLOW_TOKEN)).isTrue();
    }

    @Test
    public void resetShouldClearWinners() {
        assertThat(grid.hasWinner(YELLOW_TOKEN)).isFalse();
        dropTokensInColumn(YELLOW_TOKEN, 1, 4);
        assertThat(grid.hasWinner(YELLOW_TOKEN)).isTrue();

        grid.reset();
        assertThat(grid.hasWinner(YELLOW_TOKEN)).isFalse();
    }

    private void dropTokensInColumn(String token, int column, int numberOfTokens) {
        for (int t = 0; t < numberOfTokens; t++) {
            grid.dropToken(column, token);
        }
    }

}
