package com.atto.server.service;

import java.util.List;
import java.util.Map;

/**
 * Created by dhjung on 2017. 9. 18..
 */
public interface PermissionService {
    List<String> getBasicPermissions();
    Map<String, List<String>> getPermissions();
    List<String> getPermissionsByGroupUid(String groupUid);
    List<String> addPermission(String groupUid, String permStr);
    List<String> addPermissions(String groupUid, List<String> permissionStrings);
    void removePermissions(String groupUid, List<String> permissionStrings);
}
