package com.atto.server.exception;

public class ExpiredTokenException extends SecurityException {

    private static final long serialVersionUID = 8859720271248407800L;

    public ExpiredTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpiredTokenException(String message) {
        super(message);
    }

    public ExpiredTokenException() {
    }

}
