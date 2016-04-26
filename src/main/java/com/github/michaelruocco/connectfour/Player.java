package com.github.michaelruocco.connectfour;

import java.awt.*;

public class Player {

    private final String name;
    private final Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return Character.toString(name.charAt(0));
    }

    public Color getColor() { return color; }

}
