package com.atto.server.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dhjung on 2017. 9. 5..
 */
public class Permission {
    String userUid;
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

    public Permission(String domain, List<String> actions){
        this.domain = domain;
        this.action = convertAction(actions);
    }

    public Permission(String userUid, String domain, String action){
        this.userUid = userUid;
        this.domain = domain;
        this.action = action;
    }

    public Permission(String userUid, String domain, List<String> actions){
        this.userUid = userUid;
        this.domain = domain;
        this.action = convertAction(actions);
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getActionString() {
        return action;
    }

    public List<String> getActionList() {
        return Arrays.asList(this.action.split(","));
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActinoList(List<String> actionList) {
        this.action = convertAction(actionList);
    }

    public void addAction(String action){
        if(this.action == null){
            setAction(action);
        }else {
            List<String> actions = new ArrayList<>();
            for (String s : this.action.split(",")) {
                actions.add(s);
            }
            if (!actions.contains(actions))
                actions.add(action);

            this.action = convertAction(actions);
        }
    }

    private String convertAction(List<String> actions) {
//        if(actions.contains("get") && actions.contains("post") && actions.contains("put") && actions.contains("delete")){
//            this.action = "*";
//        } else {
        return actions.stream().collect(Collectors.joining(","));
//        }
    }

    public Map<String, String> toMap(){
        Map<String, String> body = new HashMap<>();
        body.put("userUid", getUserUid());
        body.put("domain", getDomain());
        body.put("action", getActionString());
        return body;
    }

    public String toPermissionStr(){
        return domain + ":" + action;
    }

    @Override
    public String toString() {
        return toMap().toString();
    }
}
