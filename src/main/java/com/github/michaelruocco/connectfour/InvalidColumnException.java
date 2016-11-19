package com.github.michaelruocco.connectfour;

public class InvalidColumnException extends ConnectFourException {

    public InvalidColumnException(String message) {
        super(message);
    }

    public InvalidColumnException(String message, Throwable cause) {
        super(message, cause);
    }

}
