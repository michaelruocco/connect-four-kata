package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.controller.DropTokenActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ButtonPanel extends JPanel {

    private final List<JButton> buttons = new ArrayList<>();

    public ButtonPanel(GuiConnectFour guiConnectFour, int numberOfColumns) {
        setLayout(new GridLayout(1, numberOfColumns));
        for (int c = 1; c <= numberOfColumns; c++) {
            JButton button = createButton(guiConnectFour, c);
            buttons.add(button);
            add(button);
        }
    }

    public void disableButton(int columnIndex) {
        JButton button = buttons.get(columnIndex - 1);
        button.setEnabled(false);
    }

    public void reset() {
        for (JButton button : buttons)
            button.setEnabled(true);
    }

    private JButton createButton(GuiConnectFour guiConnectFour, int columnIndex) {
        JButton button = new JButton(Integer.toString(columnIndex));
        button.addActionListener(new DropTokenActionListener(guiConnectFour, columnIndex));
        return button;
    }

}
