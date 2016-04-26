package com.github.michaelruocco.connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.github.michaelruocco.connectfour.ConnectFour.*;
import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;
import static javax.swing.SwingUtilities.invokeLater;

public class Gui {

    private static final int NUMBER_OF_COLUMNS = 7;
    private static final int NUMBER_OF_ROWS = 6;

    public static void main(String[] args) {
        invokeLater(Gui::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        ConnectFour connectFour = new ConnectFour(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);

        List<Column> columns = new ArrayList<>();
        for (int c = 1; c <= NUMBER_OF_COLUMNS; c++) {
            Column column = new Column(connectFour, c);
            for (int r = 0; r < NUMBER_OF_ROWS; r++) {
                column.add(new Square());
            }
            columns.add(column);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, NUMBER_OF_COLUMNS));
        for(Column column : columns) {
            buttonPanel.add(column.getButton());
        }

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
        for (int r = NUMBER_OF_ROWS - 1; r >= 0; r--) {
            for(Column column : columns) {
                gridPanel.add(column.getSquare(r));
            }
        }

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(buttonPanel, BorderLayout.PAGE_START);
        container.add(gridPanel, BorderLayout.CENTER);

        JFrame frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(container);
        frame.pack();
        frame.setVisible(true);
    }

    private static class Square extends JPanel {

        private Color circleColor = WHITE;

        public Square() {
            this.setPreferredSize(new Dimension(75, 75));
        }

        public void setCircleColor(Color circleColor) {
            this.circleColor = circleColor;
        }

        @Override
        public void paint(Graphics g) {
            fillSquare(g);
            drawCenteredCircle(g);
        }

        private void fillSquare(Graphics g) {
            g.setColor(BLUE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        private void drawCenteredCircle(Graphics g) {
            g.setColor(circleColor);
            int x = getWidth() / 2;
            int y = getHeight() / 2;
            int radius = getWidth() / 2;
            if (getWidth() > getHeight()) {
                radius = getHeight() / 2;
            }
            x = x - (radius / 2);
            y = y - (radius / 2);
            g.fillOval(x, y, radius, radius);
        }

    }

    public static class Column implements ActionListener {

        private final List<Square> squares = new ArrayList<>();
        private final int index;
        private final JButton button;
        private final ConnectFour connectFour;

        public Column(ConnectFour connectFour, int index) {
            this.connectFour = connectFour;
            this.button = new JButton(Integer.toString(index));
            this.button.addActionListener(this);
            this.index = index;
        }

        public JButton getButton() {
            return button;
        }

        public void add(Square square) {
            squares.add(square);
        }

        public Square getSquare(int rowIndex) {
            return squares.get(rowIndex);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Player player = connectFour.getCurrentPlayer();
            int topOfColumn = connectFour.getTopOfColumn(index);
            connectFour.dropToken(Integer.toString(index));
            if (connectFour.currentPlayerHasWon()) {

            }
            Square square = squares.get(topOfColumn);
            square.setCircleColor(player.getColor());
            square.repaint();
            connectFour.switchCurrentPlayer();
        }

    }

}
