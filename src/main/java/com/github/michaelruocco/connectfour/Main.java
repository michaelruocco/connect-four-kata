package com.github.michaelruocco.connectfour;

import java.util.Scanner;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {

    public static void main(String[] args) {
        ConnectFour connectFour = new ConnectFour();
        if (args.length < 1 || args[0].equals("console")) {
            ConsoleConnectFour console = new ConsoleConnectFour(connectFour, new Scanner(System.in), System.out);
            console.play();
        } else {
            invokeLater(() -> {
                GuiConnectFour gui = new GuiConnectFour(connectFour);
                gui.play();
            });
        }
    }

}
