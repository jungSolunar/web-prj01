package com.atto.server.service;

import java.util.List;
import java.util.Map;

import com.atto.server.model.User;
import com.atto.server.model.UserGroup;

/**
 * User, User Group Management Service
 *
 * Created by dhjung on 2017. 8. 29..
 */
public interface AccountService {

    /* User */
    User getUserByUid(String userUid);
    List<User> getUsers();
    User getUserByLoginId(String userLoginId); /* for login */
    List<User> getUserByGroupUid(String groupUid);
    User addUser(User user);
    User modifyUser(User user);
    void removeUser(String userUid);

    /* UserGroup */
    Map<String, Object> getUsersByUserGroups();
    List<UserGroup> getUserGroups();
    UserGroup getUserGroupByUid(String userGroupUid);
    String getUserGroupUidByUserUid(String userUid);
    UserGroup addUserGroup(UserGroup userGroup);
    UserGroup modifyUserGroup(UserGroup userGroup);
    void removeUserGroup(String userGroupUid);

    /* User - UserGroup Mapping */
    List<User> enrollUser(String groupUid, String userUid);
    List<User> unEnrollUser(String groupUid, String userUid);
    List<Object> enrollUserGroup(String targetGroupUid, String groupUid);
    List<Object> unEnrollUserGroup(String targetGroupUid, String groupUid);

}