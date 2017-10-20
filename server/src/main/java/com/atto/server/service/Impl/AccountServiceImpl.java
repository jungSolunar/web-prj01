package com.atto.server.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atto.server.db.mapper.AccountMapper;
import com.atto.server.manager.CredentialManager;
import com.atto.server.model.User;
import com.atto.server.model.UserGroup;
import com.atto.server.model.UserUserGroup;
import com.atto.server.service.AccountService;
import com.atto.server.util.SecurityContext;
import com.atto.server.util.SecurityUtil;
import com.atto.server.util.TimeUtil;

/**
 * Created by dhjung on 2017. 8. 30..
 */
@Service
public class AccountServiceImpl implements AccountService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private final AccountMapper accountMapper;

	@Autowired
	private final CredentialManager credentialManager;

	public AccountServiceImpl(AccountMapper accountMapper, CredentialManager credentialManager) {
		this.accountMapper = accountMapper;
		this.credentialManager = credentialManager;
	}

	@Override
	public User getUserByUid(String userUid) {
		logger.trace("[Account] getUserById : userUid = " + userUid);
		return accountMapper.selectByUserUid(userUid);
	}

    @Override
    public User getUserByLoginId(String loginId) {
        logger.trace("[Account] getUserByLoginId : loginId = " + loginId);
        return accountMapper.selectByUserLoginId(loginId);
    }

	@Override
	public List<User> getUserByGroupUid(String groupUid) {
		return accountMapper.selectUserByGroupUid(groupUid);
	}

	@Override
	public List<User> getUsers() {
		return accountMapper.selectUsers();
	}

	@Override
	public User addUser(User user) {
	    // TODO add custUid
	    user.setUserUid(SecurityUtil.createNewUserUid());
//	    user.setCreateUid(SecurityContext.get().getUserUid());
//	    user.setCreateDtm(TimeUtil.getCurrentDateString());

	    String encryptedLoginPw = credentialManager.encryptPassword(user.getLoginPw());
	    user.setLoginPw(encryptedLoginPw);

	    logger.debug("[AccountServer] add user : " + user.toString());

	    accountMapper.insertUser(user);

	    return accountMapper.selectByUserUid(user.getUserUid());
	}

	@Override
	public User modifyUser(User user) {
//        user.setUpdateUid(SecurityContext.get().getUserUid());
//        user.setUpdateDtm(TimeUtil.getCurrentDateString());
	    accountMapper.updateUser(user);

        return accountMapper.selectByUserUid(user.getUserUid());
	}

	@Override
	public void removeUser(String userUid) {
	    User storedUser = accountMapper.selectByUserUid(userUid);
//	    storedUser.setActiveYn(false);
//	    storedUser.setDiscardUid(SecurityContext.get().getUserUid());
//	    storedUser.setDiscardDtm(TimeUtil.getCurrentDateString());

	    /* Mark Inactive. Do not delete that user data */
		accountMapper.updateUser(storedUser);
	}

	@Override
	public List<UserGroup> getUserGroups() {
		return accountMapper.selectUserGroups();
	}

	@Override
	public Map<String, Object> getUsersByUserGroups() {
		Map<String, Object> resultBody = new HashMap<>();
		List<UserGroup> storedUserGroup = getUserGroups();
		for(UserGroup userGroup: storedUserGroup){
			resultBody.put(userGroup.getGroupUid(), getUserByGroupUid(userGroup.getGroupUid()));
		}
		return resultBody;
	}

	@Override
    public UserGroup getUserGroupByUid(String userGroupUid) {
		return accountMapper.selectUserGroupByGroupUid(userGroupUid);
    }

	@Override
	public String getUserGroupUidByUserUid(String userUid) {
		return accountMapper.selectUserGroupUidByUserUid(userUid);
	}

    @Override
    public UserGroup addUserGroup(UserGroup userGroup) {
//		userGroup.setCreateUid(SecurityContext.get().getUserUid());
//		userGroup.setCreateDtm(TimeUtil.getCurrentDateString());
		accountMapper.insertUserGroup(userGroup);
		return accountMapper.selectUserGroupByGroupUid(userGroup.getGroupUid());
    }

    @Override
    public UserGroup modifyUserGroup(UserGroup userGroup) {
        // TODO DB SQL null handle
		UserGroup savedUserGroup = accountMapper.selectUserGroupByGroupUid(userGroup.getGroupUid());
//		userGroup.setCreateUid(savedUserGroup.getCreateUid());
//		userGroup.setCreateDtm(savedUserGroup.getCreateDtm());
//
//		userGroup.setUpdateUid(SecurityContext.get().getUserUid());
//		userGroup.setUpdateDtm(TimeUtil.getCurrentDateString());

		accountMapper.updateUserGroup(userGroup);

		return accountMapper.selectUserGroupByGroupUid(userGroup.getGroupUid());
    }

	@Override
	public void removeUserGroup(String userGroupUid) {
		accountMapper.deleteUserGroup(userGroupUid);
	}

	@Override
	public List<User> enrollUser(String groupUid, String userUid) {
		UserUserGroup userUserGroup = new UserUserGroup(userUid, groupUid);
//		userUserGroup.setSaveUid(SecurityContext.get().getUserUid());

		logger.debug("[Account] enrollUser = " + userUserGroup.toString());
		accountMapper.insertUserUserGroup(userUserGroup);

		return getUserByGroupUid(groupUid);
	}

	@Override
	public List<User> unEnrollUser(String groupUid, String userUid) {
        UserUserGroup userUserGroup = new UserUserGroup(userUid, groupUid);

        logger.debug("[Account] unEnrollUser = " + userUserGroup.toString());
	    accountMapper.deleteUserUserGroup(userUserGroup);

	    return getUserByGroupUid(groupUid);
	}

	@Override
	public List<Object> enrollUserGroup(String targetGroupUid, String groupUid) {
        /* TODO implements if it needs */
		return null;
	}

	@Override
	public List<Object> unEnrollUserGroup(String targetGroupUid, String groupUid) {
	    /* TODO implements if it needs */
		return null;
	}
}
