package com.github.michaelruocco.connectfour.model;

import com.github.michaelruocco.connectfour.model.*;
import org.junit.Test;

import static java.awt.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player playerOne = new PlayerOne();
    private Player playerTwo = new PlayerTwo();

    @Test
    public void shouldReturnName() {
        assertThat(playerOne.getName()).isEqualTo("Player 1");
        assertThat(playerTwo.getName()).isEqualTo("Player 2");
    }

    @Test
    public void shouldReturnToken() {
        assertThat(playerOne.getToken()).isEqualTo(new RedToken());
        assertThat(playerTwo.getToken()).isEqualTo(new YellowToken());
    }

    @Test
    public void shouldReturnColor() {
        assertThat(playerOne.getColor()).isEqualTo(RED);
        assertThat(playerTwo.getColor()).isEqualTo(YELLOW);
    }

}
