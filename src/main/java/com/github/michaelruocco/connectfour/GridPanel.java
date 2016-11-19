package com.github.michaelruocco.connectfour;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    public GridPanel(ConnectFour connectFour) {
        int numberOfRows = connectFour.numberOfRows();
        int numberOfColumns = connectFour.numberOfColumns();
        setLayout(new GridLayout(numberOfRows, numberOfColumns));
        for (int r = numberOfRows - 1; r > -1; r--) {
            for (int c = 1; c <= numberOfColumns; c++) {
                add(new Square(connectFour, c, r + 1));
            }
        }
    }

}
