package com.atto.server.manager;

import com.atto.server.exception.ExpiredTokenException;
import com.atto.server.exception.FailedCreateTokenException;
import com.atto.server.exception.InvalidTokenException;
import com.atto.server.model.security.Subject;

/**
 * Create/Validate/Update Token
 *
 * Created by dhjung on 2017. 8. 29..
 */
public interface TokenManager {
    void createTokenAndUpdateSubject(Subject subject) throws IllegalArgumentException, FailedCreateTokenException;
    void validateToken(String token) throws InvalidTokenException, ExpiredTokenException;
    Subject getSubjectFromToken(String token) throws InvalidTokenException;
    void updateExpirationDateFromSubuject(Subject subject);
}
