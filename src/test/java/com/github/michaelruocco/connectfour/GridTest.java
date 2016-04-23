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
    public void gridHasMaxColums() {
        assertThat(grid.numberOfColumns()).isEqualTo(MAX_COLUMNS);
    }

    @Test
    public void canPlaceTokenOnGrid() {
        int column = 1;
        addTokensToColumn(RED_TOKEN, column, 1);

        assertThat(grid.getToken(column, 1)).isEqualTo(RED_TOKEN);
    }

    @Test
    public void canStackTokens() {
        int column = MAX_COLUMNS;
        addTokensToColumn(RED_TOKEN, column, 3);

        assertThat(grid.getToken(column, 1)).isEqualTo(RED_TOKEN);
        assertThat(grid.getToken(column, 2)).isEqualTo(RED_TOKEN);
        assertThat(grid.getToken(column, 3)).isEqualTo(RED_TOKEN);
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfColumnIndexLessThanOne() {
        grid.placeToken(0, RED_TOKEN);
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfColumnIndexGreaterThanMaxColumns() {
        grid.placeToken(MAX_COLUMNS + 1, RED_TOKEN);
    }

    @Test
    public void canFillColumn() {
        int column = 1;
        addTokensToColumn(RED_TOKEN, column, MAX_ROWS);
    }

    @Test(expected = ColumnFullException.class)
    public void throwsErrorWhenTokenIsAddedToAFullColumn() {
        int column = 1;
        addTokensToColumn(RED_TOKEN, column, MAX_ROWS + 1);
    }

    @Test
    public void returnsEmptyTokenIfSlotIsEmpty() {
        assertThat(grid.getToken(1, 1)).isEqualTo(EMPTY_TOKEN);
    }

    @Test
    public void shouldReturnGridStateAsString() {
        addTokensToColumn(RED_TOKEN, 2, 5);
        addTokensToColumn(YELLOW_TOKEN, 3, 4);
        addTokensToColumn(RED_TOKEN, 5, 2);
        addTokensToColumn(YELLOW_TOKEN, 5, 2);

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

    private void addTokensToColumn(String token, int column, int numberOfTokens) {
        for (int t = 0; t < numberOfTokens; t++) {
            grid.placeToken(column, token);
        }
    }

}
