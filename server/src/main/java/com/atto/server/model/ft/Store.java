package com.atto.server.model.ft;

/**
 * Created by dhjung on 2017. 10. 30..
 */
public class Store {
    String storeSid;
    String storeName;
    String userSid;
    String storeLocation;

    public String getStoreSid() {
        return storeSid;
    }

    public void setStoreSid(String storeSid) {
        this.storeSid = storeSid;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }
}
