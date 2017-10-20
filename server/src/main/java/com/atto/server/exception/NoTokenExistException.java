package com.atto.server.exception;

public class NoTokenExistException extends SecurityException {

    private static final long serialVersionUID = 2408134318844663650L;

    public NoTokenExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTokenExistException(String message) {
        super(message);
    }

    public NoTokenExistException() {
    }

}
