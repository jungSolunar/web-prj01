package com.atto.server.service;

import javax.servlet.http.HttpServletRequest;

import com.atto.server.model.security.LoginIdPassword;

/**
 * Security (Authentication/Authorization) Service Main Class
 *
 * Created by dhjung on 2017. 8. 29..
 */
public interface SecurityService {
    void login(LoginIdPassword loginIdPassword) throws SecurityException;
    void logout(String token) throws SecurityException;
    void authorize(HttpServletRequest request) throws SecurityException;
}
