package com.atto.server.model;

import com.atto.server.util.TimeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhjung on 2017. 9. 7..
 */
public class UserUserGroup {
    String userUid;
    String groupUid;

    public UserUserGroup(String userUid, String groupUid){
        this.userUid = userUid;
        this.groupUid = groupUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getGroupUid() {
        return groupUid;
    }

    public void setGroupUid(String groupUid) {
        this.groupUid = groupUid;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("userUid", getUserUid());
        map.put("groupUid", getGroupUid());
        return map;
    }

    @Override
    public String toString() {
        return toMap().toString();
    }
}
