package com.github.michaelruocco.connectfour;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLUE;

public class Square extends JPanel {

    private final int column;
    private final int row;
    private final ConnectFour connectFour;

    public Square(ConnectFour connectFour, int column, int row) {
        this.connectFour = connectFour;
        this.column = column;
        this.row = row;
        setPreferredSize(new Dimension(75, 75));
    }

    @Override
    public void paint(Graphics g) {
        fillSquare(g);
        drawCenteredCircle(g);
    }

    private void fillSquare(Graphics g) {
        g.setColor(BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawCenteredCircle(Graphics g) {
        g.setColor(getCircleColor());
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = getRadius();
        x = x - (radius / 2);
        y = y - (radius / 2);
        g.fillOval(x, y, radius, radius);
    }

    private Color getCircleColor() {
        Token token = connectFour.getToken(column, row);
        return token.getColor();
    }

    private int getRadius() {
        if (getWidth() > getHeight())
            return getHeight() / 2;
        return getWidth() / 2;
    }

}
