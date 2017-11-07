package com.atto.server.service;

import java.util.List;

import com.atto.server.model.account.User;
import com.atto.server.model.account.UserRole;
import com.atto.server.model.security.Role;

/**
 * User, User Group Management Service
 *
 * Created by dhjung on 2017. 8. 29..
 */
public interface AccountService {

    User getUserByLoginId(String userLoginId); /* for login */

    /* User */
    User getUserByUid(String userUid);
    List<User> getUsers();
    User addUser(User user);
    User modifyUser(User user);
    void removeUser(String userUid);

    Role assignRole(UserRole userRole);

}