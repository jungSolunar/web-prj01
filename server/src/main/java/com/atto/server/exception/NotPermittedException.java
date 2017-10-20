package com.atto.server.exception;

public class NotPermittedException extends SecurityException {

    private static final long serialVersionUID = -6765837668596092563L;

    public NotPermittedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPermittedException(String message) {
        super(message);
    }

    public NotPermittedException() {
    }

}
