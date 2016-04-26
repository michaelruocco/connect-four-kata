package com.github.michaelruocco.connectfour;

import org.junit.Test;

import static java.awt.Color.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player redPlayer = new Player("Red", RED);
    private Player yellowPlayer = new Player("Yellow", YELLOW);

    @Test
    public void shouldReturnName() {
        assertThat(redPlayer.getName()).isEqualTo("Red");
        assertThat(yellowPlayer.getName()).isEqualTo("Yellow");
    }

    @Test
    public void shouldReturnToken() {
        assertThat(redPlayer.getToken()).isEqualTo("R");
        assertThat(yellowPlayer.getToken()).isEqualTo("Y");
    }

    @Test
    public void shouldReturnColor() {
        assertThat(redPlayer.getColor()).isEqualTo(RED);
        assertThat(yellowPlayer.getColor()).isEqualTo(YELLOW);
    }

}
