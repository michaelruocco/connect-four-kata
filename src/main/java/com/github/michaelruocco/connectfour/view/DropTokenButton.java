package com.github.michaelruocco.connectfour.view;

import javax.swing.*;

public class DropTokenButton extends JButton {

    private final int columnIndex;

    public DropTokenButton(int columnIndex) {
        super(Integer.toString(columnIndex));
        this.setName("DropTokenButton" + columnIndex);
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

}
