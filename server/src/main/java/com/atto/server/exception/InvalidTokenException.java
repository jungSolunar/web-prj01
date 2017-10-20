package com.atto.server.exception;

public class InvalidTokenException extends SecurityException {

    private static final long serialVersionUID = -2812192475506170247L;

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException() {
    }

}
