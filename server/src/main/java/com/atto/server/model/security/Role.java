package com.atto.server.model.security;

/**
 * Created by dhjung on 2017. 10. 30..
 */
public class Role {
    private String roleSid;
    private String roleName;
    private String note;

    public String getRoleSid() {
        return roleSid;
    }

    public void setRoleSid(String roleSid) {
        this.roleSid = roleSid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
