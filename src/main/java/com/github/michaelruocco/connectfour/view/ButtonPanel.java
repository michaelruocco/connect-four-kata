package com.github.michaelruocco.connectfour.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ButtonPanel extends JPanel {

    private final List<JButton> buttons = new ArrayList<>();

    public ButtonPanel(int numberOfColumns) {
        setLayout(new GridLayout(1, numberOfColumns));
        for (int c = 1; c <= numberOfColumns; c++) {
            JButton button = new DropTokenButton(c);
            buttons.add(button);
            add(button);
        }
    }

    public void addDropTokenListener(ActionListener dropTokenListener) {
        for (JButton button : buttons)
            button.addActionListener(dropTokenListener);
    }

    public void disableButton(int columnIndex) {
        JButton button = buttons.get(columnIndex - 1);
        button.setEnabled(false);
    }

    public void reset() {
        for (JButton button : buttons)
            button.setEnabled(true);
    }

}
