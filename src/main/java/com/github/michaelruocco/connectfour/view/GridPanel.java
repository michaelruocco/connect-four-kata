package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.ConnectFour;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel {

    private Square[][] squares;

    public GridPanel(ConnectFour connectFour) {
        int numberOfRows = connectFour.numberOfRows();
        int numberOfColumns = connectFour.numberOfColumns();
        squares = new Square[numberOfRows][numberOfColumns];
        setLayout(new GridLayout(numberOfRows, numberOfColumns));
        for (int r = numberOfRows - 1; r > -1; r--) {
            for (int c = 1; c <= numberOfColumns; c++) {
                Square square = new Square(connectFour, c, r + 1);
                add(square);
                squares[r][c - 1] = square;
            }
        }
    }

    public void repaintSquare(int column, int row) {
        Square square = squares[row - 1][column - 1];
        square.repaint();
    }

}
