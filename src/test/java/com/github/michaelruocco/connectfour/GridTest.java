package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {

    private static final int MAX_COLUMNS = 7;
    private static final int MAX_ROWS = 6;
    private static final String RED_TOKEN = "R";

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

    private void addTokensToColumn(String token, int column, int numberOfTokens) {
        for (int t = 0; t < numberOfTokens; t++) {
            grid.placeToken(column, token);
        }
    }

}
