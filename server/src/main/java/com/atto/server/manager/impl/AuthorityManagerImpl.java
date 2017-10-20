package com.atto.server.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atto.server.db.mapper.PermissionMapper;
import com.atto.server.manager.AuthorityManager;
import com.atto.server.model.Permission;
import com.atto.server.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhjung on 2017. 9. 5..
 */
@Component
public class AuthorityManagerImpl implements AuthorityManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PermissionMapper permissionMapper;

	public AuthorityManagerImpl(PermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}

    @Override
    public Boolean isPermitted(Subject subject, String restUrl, String method) {
	    logger.debug("[AuthorityManager] hasPermission user: " + subject.getLoginId() + " permission: [" + restUrl + ":" +method + "]");

		// FIXME TEST CODE
        //String[] permUrlSplit = restUrl.substring(1, restUrl.length()).split("/");
		String urlFromat = convertGeneralFromatFromRestUrl(restUrl);
	    Permission permission = new Permission(subject.getUserUid(), urlFromat, method);

		return permissionMapper.hasPermissionByUser(permission);
    }

	private static String convertGeneralFromatFromRestUrl(String restUrl){
        /*
            TEST CODE
        convertGeneralFromatFromRestUrl("/a");
        convertGeneralFromatFromRestUrl("/a/b");
        convertGeneralFromatFromRestUrl("/a/ABC201710101234567890");
        convertGeneralFromatFromRestUrl("/a/DEF201710101234567890");
        convertGeneralFromatFromRestUrl("/a/b/GHI20171010123456789");
        convertGeneralFromatFromRestUrl("/a/ABC201710101234567/c/ABC201710101234567890");
        convertGeneralFromatFromRestUrl("/a/ABC201710100000000000/c/d");
        convertGeneralFromatFromRestUrl("/ABC201710101234567890000/b/c/d/ABC20171010123455667asd");
         */

		System.out.println("pre:" + restUrl);
		String[] splitUrl = restUrl.split("/");
		String regex = "^[A-Z]{3}+[0-9]{27}$";
		List<String> changeUrl = new ArrayList<>();
		for(String s: splitUrl){
			if(s.matches(regex)){
				changeUrl.add("{id}");
			}else {
				changeUrl.add(s);
			}
		}
		String re = String.join("/", changeUrl);
		System.out.println("post:" + re);
		return re;
	}
}
