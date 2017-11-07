package com.atto.server.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.atto.server.model.account.User;

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

}