package com.atto.server.service.Impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atto.server.exception.ExpiredTokenException;
import com.atto.server.exception.FailedCreateTokenException;
import com.atto.server.exception.IncorrectPasswordException;
import com.atto.server.exception.InvalidTokenException;
import com.atto.server.exception.NoTokenExistException;
import com.atto.server.exception.NotPermittedException;
import com.atto.server.exception.UnenrolledUserException;
import com.atto.server.manager.AuthorityManager;
import com.atto.server.manager.CredentialManager;
import com.atto.server.manager.TokenManager;
import com.atto.server.model.LoginIdPassword;
import com.atto.server.model.Subject;
import com.atto.server.model.User;
import com.atto.server.service.AccountService;
import com.atto.server.service.SecurityService;
import com.atto.server.util.HttpUtil;
import com.atto.server.util.SecurityContext;


/**
 * Security Service Main Implement Class
 *
 * Created by dhjung on 2017. 8. 29..
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private CredentialManager credentialManager;

    @Autowired
    private AuthorityManager authorityManager;

    @Autowired
    private AccountService accountService;

    @Override
    public void login(LoginIdPassword loginIdPassword) throws SecurityException {
        logger.trace("[SecurityService] login reqBody = " + loginIdPassword.toString());

        try {
            Subject subject = authenticateWithLoginIdPassword(loginIdPassword);
            SecurityContext.set(subject);
        } catch (UnenrolledUserException | IncorrectPasswordException | FailedCreateTokenException ae) {
            throw new SecurityException("Authenticate is failed. loginIdPassword = " + loginIdPassword.toString() + " " + ae.getLocalizedMessage(), ae);
        } catch (IllegalStateException | IllegalArgumentException ie) {
            throw new SecurityException("Authenticate is failed. loginIdPassword = " + loginIdPassword.toString() + " " + ie.getLocalizedMessage(), ie);
        }

        logger.debug("[SecurityService] Authenticate is succeeded. loginIdPassword = " + loginIdPassword.toString());
    }

    @Override
    public void logout(String token) throws SecurityException {
        logger.trace("[SecurityService] logout token = " + token);

        Subject subject = null;
        try {
            subject = tokenManager.getSubjectFromToken(token);
            SecurityContext.set(subject);
        } catch (InvalidTokenException ite) {
            throw new SecurityException("Failed to get user information from given token = " + token +  " " + ite.getLocalizedMessage(), ite);
        }

        logger.debug("[SecurityService] logout userUid = " + subject.getUserUid() + " loginId = " + subject.getLoginId());
    }

    @Override
    public void authorize(HttpServletRequest request) throws SecurityException, NotPermittedException {
        // 1. authenticate with given token
        // 2. authorize with given token and request url, method

        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod().toLowerCase();

        String token = null;
        Subject currentSubject = null;

        try {
            token = HttpUtil.getTokenFromRequest(request);
            authenticateWithToken(token); // Update expiration date
        } catch (NoTokenExistException | InvalidTokenException | ExpiredTokenException ae) {
            throw new SecurityException("Failed to get a proper token from request path = " + requestUri + " " + requestMethod + ". " + ae.getLocalizedMessage(), ae);
        }

        try {
            currentSubject = tokenManager.getSubjectFromToken(token);
            tokenManager.updateExpirationDateFromSubuject(currentSubject);
            currentSubject.setGroupId(accountService.getUserGroupUidByUserUid(currentSubject.getUserUid()));
            SecurityContext.set(currentSubject);
        } catch (InvalidTokenException ite) {
            throw new SecurityException("Failed to get a subject and update eixpiration date for given token = " + token + " . " + ite.getLocalizedMessage(), ite);
        }

        if (!authorizeWithSubjectAndRequest(currentSubject, requestUri, requestMethod)) {
            NotPermittedException npe = new NotPermittedException("Not Permitted Request LoginId = " + currentSubject.getLoginId() + " request = " + requestUri + " " + requestMethod);
//            throw new SecurityException("Failed to authroize the reuqest path = " + requestUri + " " + requestMethod + ". " + npe.getLocalizedMessage(), npe);
            throw npe;
        }

        logger.debug("[SecurityService] authorize = true" +
                " userUid = " + currentSubject.getUserUid() + " request path = " + requestUri + " method = " + requestMethod);
    }

    private Subject authenticateWithLoginIdPassword(LoginIdPassword loginIdPassword) throws UnenrolledUserException, IncorrectPasswordException, FailedCreateTokenException {
        logger.trace("[SecurityService] authenticate loginIdPassword = " + loginIdPassword.toString());

        String userLoginId = loginIdPassword.getLoginId();
        String userPassword = loginIdPassword.getPassword();

        if(!credentialManager.matchPassword(loginIdPassword)) {
            throw new IncorrectPasswordException("Incorrect password : loginId = " + userLoginId);
        }

        // TODO add more informations to save on token for the request client
        User registeredUser = accountService.getUserByLoginId(userLoginId);

        Subject subject = new Subject();
        subject.setUserUid(registeredUser.getUserUid());
        subject.setLoginId(registeredUser.getLoginId());
        subject.setGroupId(accountService.getUserGroupUidByUserUid(registeredUser.getUserUid()));
        // TODO ADD GROUP UID

        tokenManager.createTokenAndUpdateSubject(subject);

        logger.debug("[SecurityService] Create new subject : " + subject.toString());

        return subject;
    }

    private void authenticateWithToken(String token) throws InvalidTokenException, ExpiredTokenException {
        tokenManager.validateToken(token);
    }

    private Boolean authorizeWithSubjectAndRequest(Subject subject, String restUrl, String method) {
        return authorityManager.isPermitted(subject, restUrl, method);
    }

}
