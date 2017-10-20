package com.atto.server.model;

import org.json.simple.JSONObject;

public class LoginIdPassword {

    private String loginId;
    private String password;

    public LoginIdPassword(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginId", getLoginId());
        jsonObject.put("password", getPassword());
        return jsonObject;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
