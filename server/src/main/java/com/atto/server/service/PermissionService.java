package com.atto.server.service;

import java.util.List;
import java.util.Map;

/**
 * Created by dhjung on 2017. 9. 18..
 */
public interface PermissionService {
    Map<String, Object> getAccessPermissions(String userSid);
}
