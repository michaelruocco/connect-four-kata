package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.Player;
import com.github.michaelruocco.connectfour.model.ConnectFour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static java.awt.event.WindowEvent.*;
import static javax.swing.JOptionPane.*;

public class GuiConnectFour extends JFrame {

    private final ConnectFour connectFour;
    private final ButtonPanel buttonPanel;
    private final CurrentPlayerPanel currentPlayerPanel;

    public GuiConnectFour(ConnectFour connectFour) {
        super("Connect Four");
        this.connectFour = connectFour;
        int numberOfColumns = connectFour.numberOfColumns();
        int numberOfRows = connectFour.numberOfRows();

        this.buttonPanel = new ButtonPanel(this, numberOfColumns);
        JPanel gridPanel = new GridPanel(connectFour);
        this.currentPlayerPanel = new CurrentPlayerPanel(connectFour);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(buttonPanel, BorderLayout.PAGE_START);
        container.add(gridPanel, BorderLayout.CENTER);
        container.add(currentPlayerPanel, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(container);
    }

    public void play() {
        pack();
        setVisible(true);
    }

    public void showPlayerWins() {
        Player player = connectFour.getCurrentPlayer();
        String message = player.getName() + " player wins!";
        Object[] options = {"Close", "Reset"};
        int i = showOptionDialog(this, message, "Winner!", YES_NO_OPTION, INFORMATION_MESSAGE, null, options, options[1]);
        if (i == 1)
            reset();
        else
            close();
    }

    public boolean currentPlayerHasWon() {
        return connectFour.currentPlayerHasWon();
    }

    public void switchPlayer() {
        connectFour.switchCurrentPlayer();
        currentPlayerPanel.update();
    }

    public void dropToken(int columnIndex) {
        connectFour.dropToken(columnIndex);
        if (connectFour.isColumnFull(columnIndex))
            buttonPanel.disableButton(columnIndex);
        repaint();
    }

    private void reset() {
        connectFour.reset();
        repaint();
    }

    private void close() {
        dispatchEvent(new WindowEvent(this, WINDOW_CLOSING));
    }

}
