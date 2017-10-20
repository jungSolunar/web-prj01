package com.atto.server.model;

import org.json.simple.JSONObject;

/**
 * Created by dhjung on 2017. 8. 28..
 */
public class AccessLog {
    String log_uid;
    String remote_host;
    String remote_ip;
    String login_id;
    String user_uid;
    String user_name;
    String rsc_acl_id;
    String func_acl_id;
    JSONObject parameter;
    String request_dtm;
    String result_code;
    String result_message;
    String debug_mesasge;
    String response_dtm;
    Integer interval_ms;
    String create_dtm;

    public String getLog_uid() {
        return log_uid;
    }

    public void setLog_uid(String log_uid) {
        this.log_uid = log_uid;
    }

    public String getRemote_host() {
        return remote_host;
    }

    public void setRemote_host(String remote_host) {
        this.remote_host = remote_host;
    }

    public String getRemote_ip() {
        return remote_ip;
    }

    public void setRemote_ip(String remote_ip) {
        this.remote_ip = remote_ip;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRsc_acl_id() {
        return rsc_acl_id;
    }

    public void setRsc_acl_id(String rsc_acl_id) {
        this.rsc_acl_id = rsc_acl_id;
    }

    public String getFunc_acl_id() {
        return func_acl_id;
    }

    public void setFunc_acl_id(String func_acl_id) {
        this.func_acl_id = func_acl_id;
    }

    public JSONObject getParameter() {
        return parameter;
    }

    public void setParameter(JSONObject parameter) {
        this.parameter = parameter;
    }

    public String getRequest_dtm() {
        return request_dtm;
    }

    public void setRequest_dtm(String request_dtm) {
        this.request_dtm = request_dtm;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public String getDebug_mesasge() {
        return debug_mesasge;
    }

    public void setDebug_mesasge(String debug_mesasge) {
        this.debug_mesasge = debug_mesasge;
    }

    public String getResponse_dtm() {
        return response_dtm;
    }

    public void setResponse_dtm(String response_dtm) {
        this.response_dtm = response_dtm;
    }

    public Integer getInterval_ms() {
        return interval_ms;
    }

    public void setInterval_ms(Integer interval_ms) {
        this.interval_ms = interval_ms;
    }

    public void setInterval_ms(int interval_ms) {
        this.interval_ms = interval_ms;
    }

    public String getCreate_dtm() {
        return create_dtm;
    }

    public void setCreate_dtm(String create_dtm) {
        this.create_dtm = create_dtm;
    }
}
