package com.atto.server.util;

import com.atto.server.model.security.Subject;

/**
 * Created by dhjung on 2017. 9. 6..
 */
public class SecurityContext {
    private final static ThreadLocal<Subject> securityContext = new ThreadLocal<Subject>();

    public static Subject get() {
        return securityContext.get();
    }

    public static void set(Subject subject) {
        securityContext.set(subject);
    }
}
