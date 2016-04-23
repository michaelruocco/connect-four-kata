package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {

    private static final int MAX_COLUMNS = 7;
    private static final int MAX_ROWS = 6;

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
        String token = "R";
        grid.placeToken(column, token);

        int row = 0;
        assertThat(grid.getToken(column, row)).isEqualTo("R");
    }

    @Test
    public void canStackTokens() {
        int column = MAX_COLUMNS;
        String token = "R";
        grid.placeToken(column, token);
        grid.placeToken(column, token);
        grid.placeToken(column, token);

        assertThat(grid.getToken(column, 0)).isEqualTo(token);
        assertThat(grid.getToken(column, 1)).isEqualTo(token);
        assertThat(grid.getToken(column, 2)).isEqualTo(token);
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfColumnIndexLessThanOne() {
        grid.placeToken(0, "R");
    }

    @Test(expected = InvalidColumnException.class)
    public void throwsErrorIfColumnIndexGreaterThanMaxColumns() {
        grid.placeToken(MAX_COLUMNS + 1, "R");
    }

}
