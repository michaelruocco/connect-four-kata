package com.github.michaelruocco.connectfour.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class GridCheckerTest {

    private static final int MAX_COLUMNS = 7;
    private static final int MAX_ROWS = 6;
    private static final Token RED_TOKEN = new RedToken();
    private static final Token YELLOW_TOKEN = new YellowToken();

    private final Grid grid = new Grid(MAX_ROWS, MAX_COLUMNS);
    private final GridChecker gridChecker = new GridChecker(grid);

    @Test
    public void returnsNoWinnerIfGridIsEmpty() {
        assertThat(gridChecker.hasWinner(RED_TOKEN)).isFalse();
    }

    @Test
    public void returnsVerticalWinner() {
        dropTokensInColumn(RED_TOKEN, 1, 4);
        assertThat(gridChecker.hasWinner(RED_TOKEN)).isTrue();
        grid.reset();

        dropTokensInColumn(YELLOW_TOKEN, 6, 2);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(RED_TOKEN, 6, 4);
        assertThat(gridChecker.hasWinner(RED_TOKEN)).isTrue();
    }

    @Test
    public void returnsHorizontalWinner() {
        dropTokensInColumn(RED_TOKEN, 1, 1);
        dropTokensInColumn(RED_TOKEN, 2, 1);
        dropTokensInColumn(RED_TOKEN, 3, 1);
        dropTokensInColumn(RED_TOKEN, 4, 1);
        assertThat(gridChecker.hasWinner(RED_TOKEN)).isTrue();
        grid.reset();

        dropTokensInColumn(RED_TOKEN, 3, 1);
        dropTokensInColumn(YELLOW_TOKEN, 4, 1);
        dropTokensInColumn(RED_TOKEN, 5, 1);
        dropTokensInColumn(YELLOW_TOKEN, 6, 1);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(RED_TOKEN, 3, 1);
        dropTokensInColumn(YELLOW_TOKEN, 4, 1);
        dropTokensInColumn(RED_TOKEN, 5, 1);
        dropTokensInColumn(YELLOW_TOKEN, 6, 1);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(YELLOW_TOKEN, 3, 1);
        dropTokensInColumn(YELLOW_TOKEN, 4, 1);
        dropTokensInColumn(YELLOW_TOKEN, 5, 1);
        dropTokensInColumn(YELLOW_TOKEN, 6, 1);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isTrue();
    }

    @Test
    public void resetShouldClearWinners() {
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isFalse();
        dropTokensInColumn(YELLOW_TOKEN, 1, 4);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isTrue();

        grid.reset();
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isFalse();
    }

    @Test
    public void returnsForwardSlashDiagonalWinner() {
        dropTokensInColumn(YELLOW_TOKEN, 3, 1);
        dropTokensInColumn(YELLOW_TOKEN, 4, 2);
        dropTokensInColumn(YELLOW_TOKEN, 5, 3);
        dropTokensInColumn(RED_TOKEN, 6, 2);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(YELLOW_TOKEN, 6, 2);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isTrue();
        grid.reset();
    }

    @Test
    public void returnsBackSlashDiagonalWinner() {
        dropTokensInColumn(RED_TOKEN, 2, 2);
        dropTokensInColumn(YELLOW_TOKEN, 2, 2);
        dropTokensInColumn(YELLOW_TOKEN, 3, 3);
        dropTokensInColumn(YELLOW_TOKEN, 4, 2);
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isFalse();

        dropTokensInColumn(YELLOW_TOKEN, 5, 1);
        System.out.println(grid.asString());
        assertThat(gridChecker.hasWinner(YELLOW_TOKEN)).isTrue();
        grid.reset();
    }

    private void dropTokensInColumn(Token token, int column, int numberOfTokens) {
        for (int t = 0; t < numberOfTokens; t++) {
            grid.dropToken(column, token);
        }
    }

}