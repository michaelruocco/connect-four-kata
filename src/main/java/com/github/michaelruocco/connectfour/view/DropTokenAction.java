package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.ConnectFour;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DropTokenAction extends AbstractAction {

    private final ConnectFour connectFour;
    private final int column;

    public DropTokenAction(ConnectFour connectFour, int column) {
        super(Integer.toString(column));
        this.connectFour = connectFour;
        this.column = column;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        connectFour.dropToken(column);
        if (connectFour.isColumnFull(column)) {
            setEnabled(false);
        }
        connectFour.switchCurrentPlayer();
    }

}
