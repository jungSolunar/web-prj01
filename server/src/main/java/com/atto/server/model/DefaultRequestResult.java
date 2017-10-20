package com.atto.server.model;

import java.io.Serializable;

import org.json.simple.JSONObject;

public class DefaultRequestResult implements Serializable {

    private static final long serialVersionUID = 1L;

    int status = 200;
    Object body;

    public DefaultRequestResult() {
        this.body = new JSONObject();
    }

    @SuppressWarnings("unchecked")
    public DefaultRequestResult (String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", message);
        this.body = jsonObject;
    }

    public DefaultRequestResult (Object body){
        this.body = body;
    }

    public DefaultRequestResult (int status, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", message);
        this.body = jsonObject;
        this.status = status;
    }

    public DefaultRequestResult (int status, Object body){
        this.body = body;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
