package com.atto.server.manager;

import com.atto.server.model.LoginIdPassword;

/**
 * Created by dhjung on 2017. 8. 29..
 */
public interface CredentialManager {
    boolean matchPassword(LoginIdPassword loginIdPassword) throws IllegalStateException, IllegalArgumentException;
    String encryptPassword(String password) throws IllegalStateException, IllegalArgumentException;
}
