package com.atto.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.atto.server.model.User;
import com.atto.server.model.UserGroup;
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

	@RequestMapping(value = "/accounts/users/{userUid:.*}", method = RequestMethod.GET)
	public User getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userUid) {

		logger.trace("[REQUEST] GET /accounts/user/{userUid} : userId = " + userUid);

		User user = accountService.getUserByUid(userUid);

		if (null == user) {
			logger.debug("[RESPONSE] GET /accounts/users/" + userUid + " data = NOT FOUND");
			response.setStatus(HttpStatus.SC_NOT_FOUND);
		} else {
			logger.debug("[RESPONSE] GET /accounts/users/" + userUid + " data = " + user.toString());
		}

		return user;
	}

    @RequestMapping(value = "/accounts/users", method = RequestMethod.POST)
    public User addUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User requestUser) {

        logger.trace("[REQUEST] POST /accounts/users " + requestUser.toString());

        String loginId = requestUser.getLoginId();

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

    @RequestMapping(value = "/accounts/users/{userUid:.*}", method = RequestMethod.PUT)
    public User updateUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userUid, @RequestBody User requestUser) {

        logger.trace("[REQUEST] PUT /accounts/user/" + userUid);

        if (requestUser.getUserUid() == null) {
            requestUser.setUserUid(userUid);
        }

        User updatedUser = null;
        try {
            updatedUser = accountService.modifyUser(requestUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("[RESPONSE] PUT /accounts/user/{userUid} : failed " + userUid + " " + e.getLocalizedMessage());
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }

        if (updatedUser == null) {
            logger.debug("[RESPONSE] PUT /accounts/user/{userUid} : failed to find User [userId=" + userUid + "]" );
            response.setStatus(HttpStatus.SC_NOT_FOUND);
        }

        return updatedUser;
    }

    @RequestMapping(value = "/accounts/users/{userUid}", method = RequestMethod.DELETE)
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userUid) {

        logger.trace("[REQUEST] DELETE /accounts/user/{userUid} : userId = " + userUid);

        response.setStatus(HttpStatus.SC_NO_CONTENT);

        accountService.removeUser(userUid);
    }

    @RequestMapping(value = "/accounts/usergroups", method = RequestMethod.GET)
    public List<UserGroup> getUserGroups(HttpServletRequest request, HttpServletResponse response) {

        logger.trace("[REQUEST] GET /accounts/usergroups");

        return accountService.getUserGroups();
    }

    @RequestMapping(value = "/accounts/usergroups/users", method = RequestMethod.GET)
    public Map<String, Object> getUsersInUserGroups(HttpServletRequest request, HttpServletResponse response) {

        logger.trace("[REQUEST] GET /accounts/usergroups/users");

        return accountService.getUsersByUserGroups();
    }

    @RequestMapping(value = "/accounts/usergroups/{groupUid:.*}/users", method = RequestMethod.GET)
    public List<User> getUsersInUserGroups(HttpServletRequest request, HttpServletResponse response, @PathVariable String groupUid) {

        logger.trace("[REQUEST] GET /accounts/usergroups/"+ groupUid + "/users");

        return accountService.getUserByGroupUid(groupUid);
    }

    @RequestMapping(value = "/accounts/usergroups/{groupUid:.*}", method = RequestMethod.GET)
    public UserGroup getUserGroup(HttpServletRequest request, HttpServletResponse response, @PathVariable String groupUid) {

        logger.trace("[REQUEST] GET /accounts/usergroups/" + groupUid);

        return accountService.getUserGroupByUid(groupUid);
    }

    @RequestMapping(value = "/accounts/usergroups/{groupUid:.*}", method = RequestMethod.POST)
    public UserGroup postUserGroup(HttpServletRequest request, HttpServletResponse response, @PathVariable String groupUid, @RequestBody UserGroup userGroup) {

        logger.trace("[REQUEST] POST /accounts/usergroups/" + groupUid);

        response.setStatus(HttpServletResponse.SC_CREATED);

        return accountService.addUserGroup(userGroup);
    }

    @RequestMapping(value = "/accounts/usergroups/{groupUid:.*}", method = RequestMethod.PUT)
    public UserGroup putUserGroup(HttpServletRequest request, HttpServletResponse response, @PathVariable String groupUid, @RequestBody UserGroup userGroup) {

        logger.trace("[REQUEST] PUT /accounts/usergroups/" + groupUid);

        return accountService.modifyUserGroup(userGroup);
    }

    @RequestMapping(value = "/accounts/usergroups/{groupUid:.*}", method = RequestMethod.DELETE)
    public void deleteUserGroup(HttpServletRequest request, HttpServletResponse response, @PathVariable String groupUid) {

        logger.trace("[REQUEST] DELETE /accounts/usergroups/" + groupUid);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        accountService.removeUserGroup(groupUid);
    }

    @RequestMapping(value = "/accounts/usergroups/{groupUid:.*}/enroll", method = RequestMethod.POST)
    public List<User> postEnrollInUserGroup(HttpServletRequest request, HttpServletResponse response, @PathVariable String groupUid, @RequestBody JSONObject body) {

        logger.trace("[REQUEST] POST /accounts/usergroups/" + groupUid + "/enroll");

        response.setStatus(HttpServletResponse.SC_CREATED);

        if(body.keySet().contains("user")) {
            return accountService.enrollUser(groupUid, body.get("user").toString());
        }
//        else if(body.keySet().contains("usergroup")){
//            return accountService.enrollUserGroup(groupUid, body.get("usergroup").toString());
//        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/accounts/usergroups/{groupUid:.*}/fire", method = RequestMethod.POST)
    public List<User> postFireInUserGroup(HttpServletRequest request, HttpServletResponse response, @PathVariable String groupUid, @RequestBody JSONObject body) {

        logger.trace("[REQUEST] POST /accounts/usergroups/" + groupUid + "/fire");

        // if set NO CONTENT, response has no data on body
        //response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        if(body.keySet().contains("user")) {
            return accountService.unEnrollUser(groupUid, body.get("user").toString());
        }
//        else if(body.keySet().contains("usergroup")){
//            return accountService.enrollUserGroup(groupUid, body.get("usergroup").toString());
//        }
        else {
            return null;
        }
    }
}
