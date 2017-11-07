package com.atto.server.model.security;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dhjung on 2017. 9. 5..
 */
public class Permission {
    String id;
    String domain;
    String action;

    public Permission(String permissionForm){
        this.domain = permissionForm.split(":")[0];
        this.action = permissionForm.split(":")[1];
    }

    public Permission(String domain, String action){
        this.domain = domain;
        this.action = action;
    }

    public Permission(String id, String domain, String action){
        this.id = id;
        this.domain = domain;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, String> toMap(){
        Map<String, String> body = new HashMap<>();
        body.put("userUid", getId());
        body.put("domain", getDomain());
        body.put("action", getAction());
        return body;
    }

    @Override
    public String toString() {
        return toMap().toString();
    }
}
