package com.atto.server.exception;

public class IncorrectPasswordException extends SecurityException {

    private static final long serialVersionUID = 5801850619098005316L;

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException() {
    }

}
