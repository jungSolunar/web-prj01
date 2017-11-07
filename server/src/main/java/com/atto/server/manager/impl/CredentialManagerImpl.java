package com.atto.server.manager.impl;

import java.util.Date;

import com.atto.server.db.mapper.AccountMapper;
import com.atto.server.model.security.LoginIdPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atto.server.manager.CredentialManager;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by dhjung on 2017. 8. 30..
 */
@Component
public class CredentialManagerImpl implements CredentialManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //@Value("${jwt.token.secret}")
    private static final String SECRET = "atto";

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public boolean matchPassword(LoginIdPassword loginIdPassword) throws IllegalStateException, IllegalArgumentException {
        logger.trace("[CredentialManager] matchPassword");

        String storedPassword = accountMapper.selectPasswordByUserLoginId(loginIdPassword.getLoginId());

        if (loginIdPassword.getPassword() == null || storedPassword == null) {
            throw new IllegalArgumentException("ReuqestPassword and StoredPasword are required.");
        }

        String  encryptedPassword = encryptPassword(loginIdPassword.getPassword());

        if (storedPassword.equals(encryptedPassword)) {
            logger.debug("[CredentialManager] Password is same");
            return true;
        } else {
            logger.debug("[CredentialManager] Passwird is different");
            return false;
        }
    }

    @Override
    public String encryptPassword(String password) throws IllegalStateException, IllegalArgumentException {
        // TODO improve algorithm
        String encryptedPassword = Jwts.builder()
                .setIssuer("")
                .setSubject(password)
                .setIssuedAt(new Date(1L))
                .setExpiration(new Date(1L))
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();

        return encryptedPassword;
    }

}
