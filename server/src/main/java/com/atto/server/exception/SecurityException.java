package com.atto.server.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SecurityException extends RuntimeException {

    private static final long serialVersionUID = -9043869057946971789L;

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException() {

    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getDebugMessage() {
        String retval;
        StringWriter sw = new StringWriter();
        super.printStackTrace(new PrintWriter(sw));
        retval = sw.toString();
        return retval;
    }
}
