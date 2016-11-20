package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.controller.DropTokenActionListener;
import com.github.michaelruocco.connectfour.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static java.awt.event.WindowEvent.*;
import static javax.swing.JOptionPane.*;

public class GuiConnectFour extends JFrame implements WinnerListener, SwitchPlayerListener, DropTokenListener {

    private final ConnectFour connectFour;
    private final ButtonPanel buttonPanel;
    private final GridPanel gridPanel;
    private final CurrentPlayerPanel currentPlayerPanel;

    public GuiConnectFour(ConnectFour connectFour) {
        super("Connect Four");
        this.connectFour = connectFour;

        connectFour.addWinnerListener(this);
        connectFour.addSwitchPlayerListener(this);
        connectFour.addDropTokenListener(this);

        int numberOfColumns = connectFour.numberOfColumns();

        this.buttonPanel = new ButtonPanel(numberOfColumns);
        this.gridPanel = new GridPanel(connectFour);
        this.currentPlayerPanel = new CurrentPlayerPanel(connectFour);

        buttonPanel.addDropTokenListener(new DropTokenActionListener(connectFour));

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(buttonPanel, BorderLayout.PAGE_START);
        container.add(gridPanel, BorderLayout.CENTER);
        container.add(currentPlayerPanel, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(container);
    }

    @Override
    public void playerWins(Player player) {
        showPlayerWins(player);
    }

    @Override
    public void switchPlayer() {
        currentPlayerPanel.update();
    }

    @Override
    public void tokenDropped(int column, int row) {
        gridPanel.repaintSquare(column, row);
    }

    public void play() {
        pack();
        setVisible(true);
    }

    private void showPlayerWins(Player player) {
        String message = player.getName() + " player wins!";
        Object[] options = {"Close", "Reset"};
        int i = showOptionDialog(this, message, "Winner!", YES_NO_OPTION, INFORMATION_MESSAGE, null, options, options[1]);
        if (i == 1)
            reset();
        else
            close();
    }

    private void reset() {
        connectFour.reset();
        buttonPanel.reset();
        repaint();
    }

    private void close() {
        dispatchEvent(new WindowEvent(this, WINDOW_CLOSING));
    }

}
