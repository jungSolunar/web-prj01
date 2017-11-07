package com.atto.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atto.server.service.AccountService;
import com.atto.server.service.PermissionService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atto.server.exception.LoginIdOrPasswordNotExistException;
import com.atto.server.model.DefaultRequestResult;
import com.atto.server.model.security.LoginIdPassword;
import com.atto.server.service.SecurityService;
import com.atto.server.util.HttpUtil;
import com.atto.server.util.SecurityContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Authentication (Login/Logout) REST Controller
 *
 * Created by dhjung on 2017. 9. 1..
 */
@RestController
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject reqBody){

        logger.trace("[LOGIN] " + request.getRequestURI() + " " + request.getMethod() + " request body = " + reqBody.toJSONString());
        try {
            LoginIdPassword loginIdPassword = createLoginIdPasswordFromRequestBody(reqBody);
            securityService.login(loginIdPassword);
        } catch (SecurityException se) {
            //se.printStackTrace();
            logger.warn("[LOGIN] /login " + request.getMethod() + " request body = " + reqBody.toJSONString() + " failed. " + se.getLocalizedMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new HashMap<>();
        }

        logger.info("[LOGIN] login loginId = " + SecurityContext.get().getLoginId());

        HttpUtil.addAuthorizationHttpHeaderFromSecurityContextToHttpServletResponse(response);
        response.setStatus(HttpServletResponse.SC_OK);

        Map<String,Object> jBody = new JSONObject();
        jBody.put("permissions", permissionService.getAccessPermissions(SecurityContext.get().getUserUid()));
        jBody.put("user", accountService.getUserByUid(SecurityContext.get().getUserUid()));
        return jBody;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public DefaultRequestResult logout(HttpServletRequest request, HttpServletResponse response) {

        logger.trace("[LOGIN] " + request.getRequestURI() + " " + request.getMethod());

        try {
            String token = HttpUtil.getTokenFromRequest(request);
            securityService.logout(token);
        } catch (SecurityException se) {
            //se.printStackTrace();
            logger.warn("[LOGIN] logout is not valid! request from " + request.getRemoteAddr() + ":" + request.getRemotePort() + " " + se.getLocalizedMessage());
            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            return new DefaultRequestResult("logout succeeded");
        }

        logger.info("[LOGIN] logout loginId = " + SecurityContext.get().getLoginId());

        response.setStatus(HttpServletResponse.SC_OK);
        return new DefaultRequestResult("logout succeeded");
    }

    private LoginIdPassword createLoginIdPasswordFromRequestBody(JSONObject reqBody) throws LoginIdOrPasswordNotExistException {
        String userLoginId = (String) reqBody.get(HttpUtil.ID_KEY);
        String userPassword = (String) reqBody.get(HttpUtil.PW_KEY);

        validateIdPassword(userLoginId, userPassword);

        LoginIdPassword loginIdPassword = new LoginIdPassword(userLoginId, userPassword);
        return loginIdPassword;
    }

    // TODO id/password more detail policy
    private void validateIdPassword(String userLoginId, String userPassword) throws LoginIdOrPasswordNotExistException {
        if (userLoginId == null || userLoginId.isEmpty() || userPassword == null || userPassword.isEmpty()) {
            throw new LoginIdOrPasswordNotExistException("Login ID and Password is required.");
        }
    }

}
