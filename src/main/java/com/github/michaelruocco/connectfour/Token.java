package com.github.michaelruocco.connectfour;

import java.awt.*;

public class Token {

    private final Color color;
    private final String value;

    public Token(Color color, String value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof Token) {
            Token otherToken = (Token) object;
            return value.equals(otherToken.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public Color getColor() {
        return color;
    }

}
