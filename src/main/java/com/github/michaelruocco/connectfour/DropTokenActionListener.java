package com.github.michaelruocco.connectfour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropTokenActionListener implements ActionListener {

    private final GuiConnectFour guiConnectFour;
    private final int columnIndex;

    public DropTokenActionListener(GuiConnectFour guiConnectFour, int columnIndex) {
        this.columnIndex = columnIndex;
        this.guiConnectFour = guiConnectFour;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        takeTurn();
        if (guiConnectFour.currentPlayerHasWon()) {
            guiConnectFour.showPlayerWins();
        } else {
            guiConnectFour.switchPlayer();
        }
    }

    private void takeTurn() {
        guiConnectFour.dropToken(columnIndex);
    }

}
