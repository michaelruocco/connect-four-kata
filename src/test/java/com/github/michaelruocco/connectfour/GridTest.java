package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {

    private Grid grid = new Grid();

    @Test
    public void gridHasSixRows() {
        assertThat(grid.numberOfRows()).isEqualTo(6);
    }

    @Test
    public void gridHasSevenColums() {
        assertThat(grid.numberOfColumns()).isEqualTo(7);
    }

    @Test
    public void canPlaceTokenOnGrid() {
        int column = 0;
        String token = "R";
        grid.placeToken(column, token);

        int row = 0;
        assertThat(grid.getToken(column, row)).isEqualTo("R");
    }

}
