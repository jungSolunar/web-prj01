package com.atto.server.model.account;

/**
 * Created by dhjung on 2017. 11. 7..
 */
public class UserRole {
    private String userSid;
    private String roleSid;

    public UserRole(){

    }

    public UserRole(String userSid, String roleSid){
        this.userSid = userSid;
        this.roleSid = roleSid;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public String getRoleSid() {
        return roleSid;
    }

    public void setRoleSid(String roleSid) {
        this.roleSid = roleSid;
    }
}
