package com.github.michaelruocco.connectfour;

import org.junit.Test;

import java.util.Scanner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ConnectFourTest {

    @Test
    public void canPlayGame() {
        String input = "3\n" + //red
                "4\n" + //yellow
                "3\n" + //red
                "4\n" + //yellow
                "3\n" + //red
                "test\n4\n" + //yellow error then retry
                "3\n"; //red

        Scanner scanner = new Scanner(input);
        ConnectFour connectFour = new ConnectFour(scanner, System.out);
        connectFour.play();

        assertThat(connectFour.currentPlayerHasWon()).isTrue();
        assertThat(connectFour.getCurrentPlayer().getName()).isEqualTo("Red");
    }


}
