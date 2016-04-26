package com.github.michaelruocco.connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;
import static java.awt.event.WindowEvent.*;
import static javax.swing.JOptionPane.*;

public class GuiConnectFour extends JFrame {

    private final ConnectFour connectFour;
    private List<Column> columns;

    public GuiConnectFour(ConnectFour connectFour) {
        super("Connect Four");
        this.connectFour = connectFour;
        int numberOfColumns = connectFour.numberOfColumns();
        int numberOfRows = connectFour.numberOfRows();

        columns = new ArrayList<>();
        for (int c = 1; c <= numberOfColumns; c++) {
            Column column = new Column(connectFour, c);
            for (int r = 0; r < numberOfRows; r++) {
                column.add(new Square());
            }
            columns.add(column);
        }

        CurrentPlayerPanel currentPlayerPanel = new CurrentPlayerPanel(connectFour);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, numberOfColumns));
        for (Column column : columns) {
            JButton button = column.getButton();
            buttonPanel.add(button);
            ActionListener listener = new DropTokenActionListener(connectFour, this, column, currentPlayerPanel);
            button.addActionListener(listener);
        }

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        for (int r = numberOfRows - 1; r >= 0; r--) {
            for (Column column : columns) {
                gridPanel.add(column.getSquare(r));
            }
        }

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

    public void showPlayerWins(Player player) {
        String message = player.getName() + " player wins!";
        Object[] options = {"Close", "Reset"};
        int i = showOptionDialog(this, message, "Winner!", YES_NO_OPTION, INFORMATION_MESSAGE, null, options, options[1]);
        if (i == 1)
            reset();
        else
            close();
    }

    private void reset() {
        for (Column column : columns)
            column.reset();
        connectFour.reset();
        repaint();
    }

    private void close() {
        dispatchEvent(new WindowEvent(this, WINDOW_CLOSING));
    }

    private static class Square extends JPanel {

        private Color circleColor;

        public Square() {
            this.setPreferredSize(new Dimension(75, 75));
            reset();
        }

        public void setCircleColor(Color circleColor) {
            this.circleColor = circleColor;
        }

        public void reset() {
            setCircleColor(WHITE);
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

    public static class CurrentPlayerPanel extends JPanel {

        private final ConnectFour connectFour;
        private final JLabel playerLabel;

        public CurrentPlayerPanel(ConnectFour connectFour) {
            this.connectFour = connectFour;
            this.playerLabel = new JLabel();
            setUp();
            displayCurrentPlayer();
        }

        public void displayCurrentPlayer() {
            Player player = connectFour.getCurrentPlayer();
            playerLabel.setText(player.getName());
            playerLabel.setForeground(player.getColor());
        }

        private void setUp() {
            JLabel label = new JLabel();
            label.setText("Current Player: ");
            add(label);
            add(playerLabel);
        }

    }

    public static class Column {

        private final List<Square> squares = new ArrayList<>();
        private final int index;
        private final JButton button;
        private final ConnectFour connectFour;

        public Column(ConnectFour connectFour, int index) {
            this.connectFour = connectFour;
            this.button = new JButton(Integer.toString(index));
            this.index = index;
        }

        public JButton getButton() {
            return button;
        }

        public void add(Square square) {
            squares.add(square);
        }

        public int getIndex() {
            return index;
        }

        public void reset() {
            for (Square square : squares)
                square.reset();
        }

        private Square getTopSquare() {
            int topOfColumn = connectFour.getTopOfColumn(index);
            return getSquare(topOfColumn);
        }

        private Square getSquare(int rowIndex) {
            return squares.get(rowIndex);
        }

    }

    private static class DropTokenActionListener implements ActionListener {

        private final ConnectFour connectFour;
        private final Column column;
        private final CurrentPlayerPanel currentPlayerPanel;
        private final GuiConnectFour guiConnectFour;

        public DropTokenActionListener(ConnectFour connectFour, GuiConnectFour guiConnectFour, Column column, CurrentPlayerPanel currentPlayerPanel) {
            this.connectFour = connectFour;
            this.column = column;
            this.currentPlayerPanel = currentPlayerPanel;
            this.guiConnectFour = guiConnectFour;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Square square = column.getTopSquare();

            Player player = connectFour.getCurrentPlayer();
            square.setCircleColor(player.getColor());
            square.repaint();

            connectFour.dropToken(Integer.toString(column.getIndex()));

            if (connectFour.currentPlayerHasWon()) {
                guiConnectFour.showPlayerWins(player);
                return;
            }

            connectFour.switchCurrentPlayer();

            currentPlayerPanel.displayCurrentPlayer();
            currentPlayerPanel.repaint();
        }

    }

}