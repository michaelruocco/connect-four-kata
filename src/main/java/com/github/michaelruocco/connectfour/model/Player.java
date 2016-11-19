package com.github.michaelruocco.connectfour.model;

import java.awt.*;

public class Player {

    private final String name;
    private final Token token;

    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return token;
    }

    public Color getColor() {
        return token.getColor();
    }

}
