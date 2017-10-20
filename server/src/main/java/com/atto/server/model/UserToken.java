package com.atto.server.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Token information wrapper
 * @see Subject This class refers Subject which will have more information for business logic
 *
 * Created by dhjung on 2017. 8. 28..
 */
public class UserToken {
    private String userUid;
    private String loginId;
    private String token;
    private long expirationDtm;
    private long saveDtm;


    public UserToken(){
        userUid = "";
        loginId = "";
        token = "";
        expirationDtm = 0L;
        saveDtm = 0L;
    }

    public UserToken(Map<String, Object> map){
        setUserUid(map.get("userUid").toString());
        setLoginId(map.get("loginId").toString());
        setToken(map.get("token").toString());
        setExpirationDtm(Long.valueOf(map.get("expirationDtm").toString()));
        setSaveDtm(Long.valueOf(map.get("saveDtm").toString()));
    }

    public UserToken(Subject subject) {
        setUserUid(subject.getUserUid());
        setLoginId(subject.getLoginId());
        setToken(subject.getToken());
        setExpirationDtm(subject.getExpirationDtm());
        setSaveDtm(subject.getSaveDtm());
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpirationDtm() {
        return expirationDtm;
    }

    public void setExpirationDtm(long expirationDtm) {
        this.expirationDtm = expirationDtm;
    }

    public long getSaveDtm() {
        return saveDtm;
    }

    public void setSaveDtm(long saveDtm) {
        this.saveDtm = saveDtm;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("userUid", getUserUid());
        map.put("loginId", getLoginId());
        map.put("token", getToken());
        map.put("expirationDtm", getExpirationDtm());
        map.put("saveDtm", getSaveDtm());
        return map;
    }

    @Override
    public String toString() {
        return toMap().toString();
    }
}
