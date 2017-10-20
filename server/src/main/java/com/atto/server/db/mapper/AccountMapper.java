package com.atto.server.db.mapper;

import com.atto.server.model.UserGroup;
import com.atto.server.model.UserUserGroup;
import org.apache.ibatis.annotations.Mapper;

import com.atto.server.model.User;

import java.util.List;

@Mapper
public interface AccountMapper {

    /* User Table */
    List<User> selectUsers();
	List<User> selectUserByGroupUid(String groupUid);
	User selectByUserUid(String userUid);
	User selectByUserLoginId(String loginId);
	String selectPasswordByUserLoginId(String loginId);
	void insertUser(User user);
	void updateUser(User user);
	void deleteUser(String userUid);

	List<UserGroup> selectUserGroups();
	UserGroup selectUserGroupByGroupUid(String groupUid);
	String selectUserGroupUidByUserUid(String userUid);
	void insertUserGroup(UserGroup userGroup);
	void updateUserGroup(UserGroup userGroup);
	void deleteUserGroup(String groupUid);

	void insertUserUserGroup(UserUserGroup userUserGroup);
	void deleteUserUserGroup(UserUserGroup userUserGroup);
}