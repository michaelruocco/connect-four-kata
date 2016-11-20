package com.github.michaelruocco.connectfour.controller;

import com.github.michaelruocco.connectfour.model.ConnectFour;
import com.github.michaelruocco.connectfour.view.DropTokenButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropTokenActionListener implements ActionListener {

    private final ConnectFour connectFour;

    public DropTokenActionListener(ConnectFour connectFour) {
        this.connectFour = connectFour;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!shouldProcess(e))
            return;

        int columnIndex = getColumnIndex(e);
        takeTurn(columnIndex);
    }

    private boolean shouldProcess(ActionEvent e) {
        return e.getSource() instanceof DropTokenButton;
    }

    private int getColumnIndex(ActionEvent e) {
        DropTokenButton button = (DropTokenButton) e.getSource();
        return button.getColumnIndex();
    }

    private void takeTurn(int columnIndex) {
        connectFour.dropToken(columnIndex);
        connectFour.switchCurrentPlayer();
    }

}
