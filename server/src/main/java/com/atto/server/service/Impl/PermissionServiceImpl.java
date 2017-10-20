package com.atto.server.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atto.server.db.mapper.MenuResourceMapper;
import com.atto.server.db.mapper.PermissionMapper;
import com.atto.server.model.Permission;
import com.atto.server.model.Resource;
import com.atto.server.model.UserGroupResource;
import com.atto.server.service.PermissionService;

/**
 * Created by dhjung on 2017. 9. 19..
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuResourceMapper menuResourceMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> getBasicPermissions() {
        return convertResourceToPermissionFormat(menuResourceMapper.selectResources());
    }

    @Override
    public Map<String, List<String>> getPermissions() {
        Map<String, List<String>> permissionPerGroupList = new HashMap<>();
        List<String> groupUidList = permissionMapper.selectGroupUids();
        for(String groupUid: groupUidList){
            permissionPerGroupList.put(groupUid, getPermissionsByGroupUid(groupUid));
        }
        return permissionPerGroupList;
    }

    @Override
    public List<String> getPermissionsByGroupUid(String groupUid) {
        return convertResourceToPermissionFormat(menuResourceMapper.selectResourcesByGroupId(groupUid));
    }

    @Override
    public List<String> addPermission(String groupUid, String permStr) {
        return getPermissionsByGroupUid(groupUid);
    }

    @Override
    public List<String> addPermissions(String groupUid, List<String> permissions) {

        // TODO Improve Database Query for multiple permissions
        List<Permission> permissionList = convertPermissionStrToPermission(permissions);
        for (Permission permission: permissionList) {
            for (String action: permission.getActionList()) {
                Map<String, String> param = new HashMap<>();
                param.put("domain", permission.getDomain());
                param.put("action", convertActionToMethod(action));
                logger.debug("addPermission [Param]: " + param.toString());

                List<String> rscUidList = permissionMapper.selectRscUidByPermission(param);
                logger.debug("addPermission [rscUidList]: " + rscUidList.toString());

                if (rscUidList.size() != 0) {
                    for (String rscUid : rscUidList) {
                        UserGroupResource userGroupResource = new UserGroupResource(groupUid, rscUid);
                        //userGroupResource.setSaveUid(SecurityContext.get().getUserUid());
                        permissionMapper.insertPermission(userGroupResource);
                        // TODO Handle Exception Cases
                    }
                }
            }
        }

        return getPermissionsByGroupUid(groupUid);
    }

    public void removePermission(String groupUid, String permStr) {
        Permission convertPermission = new Permission(permStr);
        for(String action: convertPermission.getActionList()){
            logger.debug("RemovePermission domain: " + convertPermission.getDomain() + ", action: " + action);

            List<Resource> resourceList = permissionMapper.selectRscByPermission((new Permission(convertPermission.getDomain(), convertActionToMethod(action)).toMap()));
            for(Resource rsc : resourceList){
                logger.info("RemovePermission url: " + rsc.getRscUrl() + ", method: " + rsc.getRscMethod());

                permissionMapper.deletePermission(new UserGroupResource(groupUid, rsc.getRscUid()));
            }
        }
    }

    @Override
    public void removePermissions(String groupUid, List<String> permissions) {
        if(permissions.size() == 0){
            permissionMapper.deletePermissionsByGroupUid(groupUid);
        }else{
            for(String permStr : permissions){
                removePermission(groupUid, permStr);
            }
        }
    }

    private List<Permission> convertPermissionStrToPermission(List<String> permissionStrList){
        List<Permission> permissionList = new ArrayList<>();
        for(String permissionStr: permissionStrList){
            permissionList.add(new Permission(permissionStr));
        }
        return permissionList;
    }

    private List<String> getDomainList(List<String> permissionStrList){
        List<String> domainList = new ArrayList<>();
        for(String permissionStr: permissionStrList){
            domainList.add(new Permission(permissionStr).getDomain());
        }
        return domainList;
    }

    private List<String> convertResourceToPermissionFormat(List<Resource> resourceList){
//        System.out.println("======> " + resourceList);
//        Map<String, List<String>> urls = new HashMap<>();
//        for(Resource resource : resourceList){
//            if(!urls.keySet().contains(resource.getRscUrl())) {
//                urls.put(resource.getRscUrl(), new ArrayList<>());
//            }
//            urls.get(resource.getRscUrl()).add(convertMethodToAction(resource.getRscMethod()));
//        }

        // TODO Improve Database Query for multiple resources
        List<String> permissionList = new ArrayList<>();
//        for(String key: urls.keySet()){
//            String domain = permissionMapper.selectPermDomainsByAclId(key);
//            List<String> action = urls.get(key);
//            permissionList.add((new Permission(domain, action)).toPermissionStr());
//        }
//        permissionDuplicateClear(permissionList);

        Map<String, List<String>> permTmp = new HashMap<>();
        for(Resource resource: resourceList){
            String permDomain = permissionMapper.selectPermDomainNameByRscUid(resource.getRscUid());
            if(permTmp.keySet().contains(permDomain)){
                List<String> actionList = permTmp.get(permDomain);
                actionList.add(convertMethodToAction(resource.getRscMethod()));
                permTmp.put(permDomain, actionList);
            } else {
                List<String> actionList = new ArrayList<>();
                actionList.add(convertMethodToAction(resource.getRscMethod()));
                permTmp.put(permDomain, actionList);
            }
        }

        for(String key:permTmp.keySet()){
            permissionList.add(new Permission(key, permTmp.get(key)).toPermissionStr());
        }

        return permissionList;
    }

    private String convertMethodToAction(String method){
        switch (method){
            case "get":
                return "read";
            case "post":
                return "create";
            case "put":
                return "modify";
            case "delete":
                return "remove";
            default:
                return "";
        }
    }

    private String convertActionToMethod(String action){
        switch (action){
            case "read":
                return "get";
            case "create":
                return "post";
            case "modify":
                return "put";
            case "remove":
                return "delete";
            default:
                return "";
        }
    }

    private List<String> mergeActionList(List<String> list1, List<String> list2){
        List<String> resultList = list1;
        for(String s: list2){
            if(!resultList.contains(s))
                resultList.add(s);
        }
        return resultList;
    }

//    private List<String> permissionDuplicateClear(List<String> permissionStrList){
//        int i = 0;
//        int j = i+1;
//        Permission permission1;
//        Permission permission2;
//        while(true){
//            permission1 = new Permission(permissionStrList.get(i));
//            permission2 = new Permission(permissionStrList.get(j));
//
//            if(permission1.getDomain().equals(permission2.getDomain())){
//                permission1.setActinoList(mergeActionList(permission1.getActionList(), permission2.getActionList()));
//                permissionStrList.add(i, permission1.toPermissionStr());
//                permissionStrList.remove(j);
//            }
//            if(j >= permissionStrList.size()-1){
//                i++;
//                j = i + 1;
//            }else{
//                j++;
//            }
//
//            if(i >= permissionStrList.size()-1){
//                break;
//            }
//
//        }
//        return permissionStrList;
//    }
}
