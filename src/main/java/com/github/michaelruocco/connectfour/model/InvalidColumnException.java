package com.github.michaelruocco.connectfour.model;

public class InvalidColumnException extends ConnectFourException {

    public InvalidColumnException(String message) {
        super(message);
    }

    public InvalidColumnException(String message, Throwable cause) {
        super(message, cause);
    }

}
