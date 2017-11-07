package com.atto.server.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.atto.server.exception.ExpiredTokenException;
import com.atto.server.exception.FailedCreateTokenException;
import com.atto.server.exception.InvalidTokenException;
import com.atto.server.manager.TokenManager;
import com.atto.server.model.security.Subject;
import com.atto.server.model.security.UserToken;
import com.atto.server.util.TimeUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Handles Authentication Token between Server and Client
 *
 * Created by dhjung on 2017. 8. 30..
 */
@Component
public class TokenManagerImpl implements TokenManager {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //@Value("${jwt.token.expires.interval}")
    private long EXPIRES_INTERVAL = 1000L * 60 * 60; // default: 1 hour

    //@Value("${jwt.token.secret}")
    private String SECRET = "atto";

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    @Override
    public void createTokenAndUpdateSubject(Subject subject) throws FailedCreateTokenException, IllegalArgumentException {

        if (subject == null || subject.getUserUid() == null || subject.getLoginId() == null) {
            throw new IllegalArgumentException("Subject's userUid and loginId is required!");
        }

        logger.trace("[TokenManager] createTokenAndUpdateSubject subject = " + subject.toString());

        UserToken userToken = createNewUserToken(subject);

        subject.setToken(userToken.getToken());
        subject.setSaveDtm(userToken.getSaveDtm());
        subject.setExpirationDtm(userToken.getExpirationDtm());

        logger.trace("[TokenManager] createTokenAndUpdateSubject " + userToken.toString());
    }

    @Override
    public Subject getSubjectFromToken(String token) throws InvalidTokenException {
        return getSubjectFromTokenString(token);
    }

    private Subject getSubjectFromTokenString(String token) throws InvalidTokenException {

        Map<String, Object> subjectMap = getClaimsMapFromTokenString(token);

        Subject subject = new Subject(subjectMap);

        return subject;
    }

    private UserToken getUserTokenFromTokenString(String token) throws InvalidTokenException {

        Map<String, Object> userTokenMap = getClaimsMapFromTokenString(token);

        UserToken userToken = new UserToken(userTokenMap);

        return userToken;
    }

    private Map<String, Object> getClaimsMapFromTokenString(String token) throws InvalidTokenException {
        // decrypt token to user information

        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
        } catch (JwtException je) {
            //je.printStackTrace();
            throw new InvalidTokenException("Failed to parse the given token = " + token, je);
        }

        Map<String, Object> userTokenMap = new HashMap<>();
        for(Object key: claims.getBody().keySet()){
            userTokenMap.put(key.toString(), claims.getBody().get(key));
        }

        return userTokenMap;
    }

    private UserToken createNewUserToken(Subject subject) throws FailedCreateTokenException {

        UserToken newUserToken = new UserToken();

        long issue = TimeUtil.getCurrentTimeMillis();
        long expiration = TimeUtil.getExpirationMillis(EXPIRES_INTERVAL);

        Date issueDate = new Date(issue);
        Date expirationDate = new Date(expiration);


        newUserToken.setUserUid(subject.getUserUid());
        newUserToken.setLoginId(subject.getLoginId());
        newUserToken.setSaveDtm(issue);
        newUserToken.setExpirationDtm(expiration);

        try {
            String newToken = createNewToken(newUserToken, issueDate, expirationDate);
            newUserToken.setToken(newToken);
        } catch (IllegalStateException | IllegalArgumentException ie) {
            logger.warn("Failed to create a new token : userUid = " + subject.getUserUid() + " " + ie.getLocalizedMessage());
            throw new FailedCreateTokenException("Failed to create a new token for userUid = " + subject.getUserUid() + " " + ie.getLocalizedMessage(), ie);
        }

        logger.debug("[TokenManager] createNewUserToken userUid = " + newUserToken.getUserUid() +
                " issue: "+ issue + ", date: " + issueDate + " expiration: " + expiration + ", date: "+ expirationDate);

        return newUserToken;
    }

    private UserToken createUserTokenWithGivenIssueDateAndUpdatedExpirationDate(UserToken userToken) {

        UserToken newUserToken = new UserToken();

        long issue = userToken.getSaveDtm();
        long expiration = TimeUtil.getExpirationMillis(EXPIRES_INTERVAL);

        Date issueDate = new Date(issue);
        Date expirationDate = new Date(expiration);

        String newToken = createNewToken(userToken, issueDate, expirationDate);

        newUserToken.setUserUid(userToken.getUserUid());
        newUserToken.setLoginId(userToken.getLoginId());
        newUserToken.setToken(newToken);
        newUserToken.setExpirationDtm(expiration);
        newUserToken.setSaveDtm(issue);

        logger.debug("[TokenManager] createUserTokenWithGivenIssueDateAndUpdatedExpirationDate userUid = " + newUserToken.getUserUid() +
                " issue: "+ issue + ", date: " + issueDate +
                " expiration: " + expiration + ", date: "+ expirationDate);

        return newUserToken;
    }

    private String createNewToken(UserToken userToken, Date currentDate, Date expiratinDate) {
        // encrypt user information to token
        String newToken = Jwts.builder()
                .setIssuer(userToken.getLoginId())
                .setSubject(userToken.getUserUid())
                .setIssuedAt(currentDate)
                .setExpiration(expiratinDate)
                .setClaims(userToken.toMap())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();

        return newToken;
    }

    @Override
    public void validateToken(String token) {
        logger.trace("[TokenManager] validate token = " + token);

        UserToken userToken  = null;
        try {
            userToken = getUserTokenFromTokenString(token);
        } catch (JwtException je) {
            // JwtException includes ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException
            je.printStackTrace();
            throw new InvalidTokenException("Invalid token = " + token, je);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            throw new InvalidTokenException("Failed to parse and get the user information from given token", iae);
        }

        if (isExpired(userToken)) {
            throw new ExpiredTokenException("Token is Expired! userUid = " + userToken.getUserUid() +
                    " expiration date = " + new Date(userToken.getExpirationDtm()));
        }

        logger.debug("[TokenManager] validate userUid = " + userToken.getUserUid() + " is not expired.");
    }

    @Override
    public void updateExpirationDateFromSubuject(Subject currentSubject) {

        UserToken currentUserToken = new UserToken(currentSubject);
        UserToken newUserToken = createUserTokenWithGivenIssueDateAndUpdatedExpirationDate(currentUserToken);

        // update given subject's token and expiration date
        currentSubject.setToken(newUserToken.getToken());
        currentSubject.setExpirationDtm(newUserToken.getExpirationDtm());

        logger.debug("[TokenManager] updateExpirationDateFromSubuject ");
    }

    private boolean isExpired(UserToken userToken) {
        long currentTime = TimeUtil.getCurrentTimeMillis();
        long expirationDate = userToken.getExpirationDtm();

        logger.debug("[TokenManager] isExpired userUid = " + userToken.getUserUid() +
                " current: "+ currentTime + ", date: " + new Date(currentTime) +
                " expiration: " + expirationDate + ", date: "+ new Date(expirationDate));

        if (currentTime < userToken.getExpirationDtm()) {
            logger.trace("[TokenManager] isExpired == false : userUid = " + userToken.getUserUid());
            return false;
        } else {
            logger.trace("[TokenManager] isExpired == true : userUid = " + userToken.getUserUid());
            return true;
        }
    }
}
