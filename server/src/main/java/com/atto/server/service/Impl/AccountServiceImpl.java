package com.atto.server.service.Impl;

import java.util.List;

import com.atto.server.model.account.UserRole;
import com.atto.server.model.security.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atto.server.db.mapper.AccountMapper;
import com.atto.server.manager.CredentialManager;
import com.atto.server.model.account.User;
import com.atto.server.service.AccountService;
import com.atto.server.util.SecurityUtil;

import static com.atto.server.constant.SecurityConstant.*;


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
	public List<User> getUsers() {
		return accountMapper.selectUsers();
	}

	@Override
	public User addUser(User user) {
	    // TODO add custUid
	    user.setUserSid(SecurityUtil.createNewSid(USER_UID_PREFIX));

	    String encryptedLoginPw = credentialManager.encryptPassword(user.getUserLoginId());
	    user.setUserLoginPw(encryptedLoginPw);

	    logger.debug("[AccountServer] add user : " + user.toString());

	    accountMapper.insertUser(user);

	    return accountMapper.selectByUserUid(user.getUserSid());
	}

	@Override
	public User modifyUser(User user) {
	    accountMapper.updateUser(user);

        return accountMapper.selectByUserUid(user.getUserSid());
	}

	@Override
	public void removeUser(String userUid) {
	    User storedUser = accountMapper.selectByUserUid(userUid);

	    /* Mark Inactive. Do not delete that user data */
		accountMapper.updateUser(storedUser);
	}

	@Override
	public Role assignRole(UserRole userRole) {
		return null;
	}
}
