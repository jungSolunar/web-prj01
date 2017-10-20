package com.atto.server.model;

import org.json.simple.JSONObject;

/**
 * Communicates with DataSource (MySQL)
 * Refers MySQL Table Schema : TAA_USER
 *
 * Created by dhjung on 2017. 8. 28..
 */
public class User {
    private String userUid;
    private String loginId;
    private String loginPw;
    private String email;
    private String phone;
    private String name;
    private String note;


    public User() {
    }

    public User(JSONObject info) {
        if (info == null) {
            new User();
        } else {
            setUserUid((String) info.get("userUid"));
            setLoginId((String) info.get("loginId"));
            setLoginPw((String) info.get("loginPw"));
            setEmail((String) info.get("email"));
            setPhone((String) info.get("phone"));
            setName((String) info.get("name"));
            setNote((String) info.get("note"));
        }
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

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @SuppressWarnings("unchecked")
    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userUid", getUserUid());
        jsonObject.put("loginId", getLoginId());
        jsonObject.put("loginPw", getLoginPw());
        jsonObject.put("email", getEmail());
        jsonObject.put("phone", getPhone());
        jsonObject.put("name", getName());
        jsonObject.put("note", getNote());
        return jsonObject;
    }

    @Override
    public String toString(){
        return toJson().toString();
    }
}
