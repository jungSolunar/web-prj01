package com.atto.server.manager;

import com.atto.server.model.security.Subject;

/**
 * Created by dhjung on 2017. 9. 5..
 */
public interface AuthorityManager {
    Boolean isPermitted(Subject subject, String restUrl, String method);
}