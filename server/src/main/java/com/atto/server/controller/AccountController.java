package com.atto.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atto.server.model.account.UserRole;
import com.atto.server.model.security.Role;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atto.server.model.account.User;
import com.atto.server.service.AccountService;

/**
 * User, User Group Management REST Controller
 *
 * Created by dhjung on 2017. 8. 30..
 */
@RestController
public class AccountController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountService accountService;

    @RequestMapping(value = "/accounts/users", method = RequestMethod.GET)
    public List<User> getUsers(HttpServletRequest request, HttpServletResponse response) {

        logger.trace("[REQUEST] GET /accounts/users");

        return accountService.getUsers();
    }

	@RequestMapping(value = "/accounts/users/{userSid:.*}", method = RequestMethod.GET)
	public User getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userSid) {

		logger.trace("[REQUEST] GET /accounts/user/{userSid} : userId = " + userSid);

		User user = accountService.getUserByUid(userSid);

		if (null == user) {
			logger.debug("[RESPONSE] GET /accounts/users/" + userSid + " data = NOT FOUND");
			response.setStatus(HttpStatus.SC_NOT_FOUND);
		} else {
			logger.debug("[RESPONSE] GET /accounts/users/" + userSid + " data = " + user.toString());
		}

		return user;
	}

    @RequestMapping(value = "/accounts/users", method = RequestMethod.POST)
    public User addUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User requestUser) {

        logger.trace("[REQUEST] POST /accounts/users " + requestUser.toString());

        String loginId = requestUser.getUserLoginId();

        /* validate request */
        if (loginId == null) {
            logger.info("[REQUEST] POST /accounts/user : loginId is required! " + requestUser.toString());
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return null;
        }

        User newUser = null;
        try {
            newUser = accountService.addUser(requestUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("[RESPONSE] POST /accounts/user : failed " + requestUser.toString());
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        if (newUser == null) {
            logger.debug("[RESPONSE] POST /account/user : failed to create new User " + requestUser.toString());
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(HttpStatus.SC_CREATED);
        }

        return newUser;
    }

    @RequestMapping(value = "/accounts/users/{userSid:.*}", method = RequestMethod.PUT)
    public User updateUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userSid, @RequestBody User requestUser) {

        logger.trace("[REQUEST] PUT /accounts/user/" + userSid);

        if (requestUser.getUserSid() == null) {
            requestUser.setUserSid(userSid);
        }

        User updatedUser = null;
        try {
            updatedUser = accountService.modifyUser(requestUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("[RESPONSE] PUT /accounts/user/{userUid} : failed " + userSid + " " + e.getLocalizedMessage());
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }

        if (updatedUser == null) {
            logger.debug("[RESPONSE] PUT /accounts/user/{userUid} : failed to find User [userId=" + userSid + "]" );
            response.setStatus(HttpStatus.SC_NOT_FOUND);
        }

        return updatedUser;
    }

    @RequestMapping(value = "/accounts/users/{userSid}", method = RequestMethod.DELETE)
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userSid) {

        logger.trace("[REQUEST] DELETE /accounts/user/{userUid} : userId = " + userSid);

        response.setStatus(HttpStatus.SC_NO_CONTENT);

        accountService.removeUser(userSid);
    }

    @RequestMapping(value = "/acciunts/users/{userSid}/assign", method = RequestMethod.POST)
    public Role assignRole(HttpServletRequest request, HttpServletResponse response, @PathVariable String userSid, @RequestBody Map<String, String> role){
        logger.trace("[REQUEST] POST /accounts/user/{userUid}/assign : userId = " + userSid);

        UserRole userRole = new UserRole(userSid, role.get("roleSid"));

        return accountService.assignRole(userRole);
    }

}
