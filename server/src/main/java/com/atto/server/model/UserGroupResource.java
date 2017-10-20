package com.atto.server.model;

import com.atto.server.util.TimeUtil;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhjung on 2017. 9. 7..
 */
public class UserGroupResource {
    String groupUid;
    String rscUid;

    public UserGroupResource(String groupUid, String apiUid){
        this.groupUid = groupUid;
        this.rscUid = apiUid;
    }

    public String getGroupUid() {
        return groupUid;
    }

    public void setGroupUid(String groupUid) {
        this.groupUid = groupUid;
    }

    public String getRscUid() {
        return rscUid;
    }

    public void setRscUid(String rscUid) {
        this.rscUid = rscUid;
    }

    public Map<String, String> toMap(){
        Map<String, String> body = new HashMap<>();
        body.put("groupUid", getGroupUid());
        body.put("rscUid", getRscUid());
        return body;
    }

    @Override
    public String toString() {
        return toMap().toString();
    }
}
