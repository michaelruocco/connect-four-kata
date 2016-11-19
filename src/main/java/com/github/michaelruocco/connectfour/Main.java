package com.github.michaelruocco.connectfour;

import com.github.michaelruocco.connectfour.model.ConnectFour;
import com.github.michaelruocco.connectfour.view.ConsoleConnectFour;
import com.github.michaelruocco.connectfour.view.GuiConnectFour;

import java.util.Scanner;

import static javax.swing.SwingUtilities.invokeLater;

public class Main {

    public static void main(String[] args) {
        ConnectFour connectFour = new ConnectFour();
        if (shouldPlayGuiVersion(args)) {
            playGuiVersion(connectFour);
        } else {
            playConsoleVersion(connectFour);
        }
    }

    private static boolean shouldPlayGuiVersion(String[] args) {
        return args.length < 1 || args[0].equals("gui");
    }

    private static void playConsoleVersion(ConnectFour connectFour) {
        ConsoleConnectFour console = new ConsoleConnectFour(connectFour, new Scanner(System.in), System.out);
        console.play();
    }

    private static void playGuiVersion(ConnectFour connectFour) {
        invokeLater(() -> {
            GuiConnectFour gui = new GuiConnectFour(connectFour);
            gui.play();
        });
    }

}
