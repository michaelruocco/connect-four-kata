package com.github.michaelruocco.connectfour;

import org.junit.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleConnectFourTest {

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
        ConsoleConnectFour console = new ConsoleConnectFour(scanner, System.out);
        console.play();

        assertThat(console.currentPlayerHasWon()).isTrue();
        assertThat(console.getCurrentPlayerName()).isEqualTo("Red");
    }

}