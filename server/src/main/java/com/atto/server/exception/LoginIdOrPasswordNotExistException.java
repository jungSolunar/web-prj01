package com.atto.server.exception;

public class LoginIdOrPasswordNotExistException extends SecurityException {

    private static final long serialVersionUID = -601701545898519126L;

    public LoginIdOrPasswordNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginIdOrPasswordNotExistException(String message) {
        super(message);
    }

    public LoginIdOrPasswordNotExistException() {
    }

}
