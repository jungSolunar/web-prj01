package com.atto.server.exception;

public class FailedCreateTokenException extends SecurityException {

    private static final long serialVersionUID = 4080569752919200453L;

    public FailedCreateTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCreateTokenException(String message) {
        super(message);
    }

    public FailedCreateTokenException() {
    }

}
