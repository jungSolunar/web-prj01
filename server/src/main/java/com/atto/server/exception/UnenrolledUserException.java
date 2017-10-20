package com.atto.server.exception;

public class UnenrolledUserException extends SecurityException {

    private static final long serialVersionUID = -5860726363429920898L;

    public UnenrolledUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnenrolledUserException(String message) {
        super(message);
    }

    public UnenrolledUserException() {
    }

}
