package com.atto.server.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atto.server.model.security.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atto.server.db.mapper.PermissionMapper;
import com.atto.server.service.PermissionService;

/**
 * Created by dhjung on 2017. 9. 19..
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Map<String, Object> getAccessPermissions(String userUid) {
        return convertPermissionListFormat(permissionMapper.selectPermissionsByUserUid(userUid));
    }

    private Map<String, Object> convertPermissionListFormat(List<Permission> permissionList){
        Map<String, Object> permMap = new HashMap<>();
        for(Permission perm : permissionList){
            String permDomain = perm.getDomain();
            if(!permMap.keySet().contains(perm.getDomain())){
                permMap.put(permDomain, new ArrayList<>());
            }
            List<String> actionList = (List<String>) permMap.get(permDomain);
            actionList.add(perm.getAction());
            permMap.put(permDomain, actionList);

        }
        return permMap;
    }

}
