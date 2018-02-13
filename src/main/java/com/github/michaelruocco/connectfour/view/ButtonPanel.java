package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.ConnectFour;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ButtonPanel extends JPanel {

    private final List<AbstractAction> actions = new ArrayList<>();

    public ButtonPanel(ConnectFour connectFour) {
        int numberOfColumns = connectFour.numberOfColumns();
        setLayout(new GridLayout(1, numberOfColumns));
        for (int column = 1; column <= numberOfColumns; column++) {
            DropTokenAction action = new DropTokenAction(connectFour, column);
            actions.add(action);
            add(toButton(action));
        }
    }

    public void reset() {
        for (AbstractAction action : actions)
            action.setEnabled(true);
    }

    private JButton toButton(AbstractAction action) {
        JButton button = new JButton(action);
        button.setName("DropTokenButton" + action.getValue(Action.NAME));
        return button;
    }

}
