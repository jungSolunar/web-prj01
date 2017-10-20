package com.atto.server.model;

/**
 * Created by dhjung on 2017. 8. 28..
 */
public class UserGroup {
    String groupUid;
    String name;
    String note;

    public String getGroupUid() {
        return groupUid;
    }

    public void setGroupUid(String groupUid) {
        this.groupUid = groupUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
