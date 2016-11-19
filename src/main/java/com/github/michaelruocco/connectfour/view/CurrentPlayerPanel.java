package com.github.michaelruocco.connectfour.view;

import com.github.michaelruocco.connectfour.model.Player;
import com.github.michaelruocco.connectfour.model.ConnectFour;

import javax.swing.*;

public class CurrentPlayerPanel extends JPanel {

    private final ConnectFour connectFour;
    private final JLabel playerLabel;

    public CurrentPlayerPanel(ConnectFour connectFour) {
        this.connectFour = connectFour;
        this.playerLabel = new JLabel();
        setUp();
        update();
    }

    public void update() {
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
