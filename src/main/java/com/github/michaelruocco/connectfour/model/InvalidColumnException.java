package com.github.michaelruocco.connectfour.model;

import com.github.michaelruocco.connectfour.model.ConnectFourException;

public class InvalidColumnException extends ConnectFourException {

    public InvalidColumnException(String message) {
        super(message);
    }

    public InvalidColumnException(String message, Throwable cause) {
        super(message, cause);
    }

}
