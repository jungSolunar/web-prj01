package com.atto.server.model.account;

import org.json.simple.JSONObject;

/**
 * Communicates with DataSource (MySQL)
 * Refers MySQL Table Schema : TAA_USER
 *
 * Created by dhjung on 2017. 8. 28..
 */
public class User {
    private String userSid;
    private String userName;
    private String loginType;
    private String userLoginId;
    private String userLoginPw;
    private String kaccountEmail;
    private String kaccountSid;

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserLoginPw() {
        return userLoginPw;
    }

    public void setUserLoginPw(String userLoginPw) {
        this.userLoginPw = userLoginPw;
    }

    public String getKaccountEmail() {
        return kaccountEmail;
    }

    public void setKaccountEmail(String kaccountEmail) {
        this.kaccountEmail = kaccountEmail;
    }

    public String getKaccountSid() {
        return kaccountSid;
    }

    public void setKaccountSid(String kaccountSid) {
        this.kaccountSid = kaccountSid;
    }
}
