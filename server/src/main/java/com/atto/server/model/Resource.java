package com.atto.server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.atto.server.util.SecurityContext;
import com.atto.server.util.TimeUtil;

/**
 * Created by dhjung on 2017. 8. 28..
 */
public class Resource {
    private String rscUid;
    private String rscUrl;
    private String rscMethod;
    private String name;
    private String note;

    public Resource(){
        init();
    }

    public Resource(String rscUrl, String rscMethod){
        init();
        this.rscUrl = rscUrl;
        this.rscMethod = rscMethod;
    }

    private void init(){
        rscUid = UUID.randomUUID().toString();
    }

    public String getRscUid() {
        return rscUid;
    }

    public void setRscUid(String rscUid) {
        this.rscUid = rscUid;
    }

    public String getRscUrl() {
        return rscUrl;
    }

    public void setRscUrl(String rscUrl) {
        this.rscUrl = rscUrl;
    }

    public String getRscMethod() {
        return rscMethod;
    }

    public void setRscMethod(String rscMethod) {
        this.rscMethod = rscMethod;
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

    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("rscUid", getRscUid());
        map.put("rscUrl", getRscUrl());
        map.put("rscMethod", getRscMethod());
        map.put("name", getName());
        map.put("note", getNote());
        return map;
    }

    @Override
    public String toString() {
        return toMap().toString();
    }
}
