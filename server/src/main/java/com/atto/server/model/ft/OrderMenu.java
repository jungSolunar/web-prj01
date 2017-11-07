package com.atto.server.model.ft;

/**
 * Created by dhjung on 2017. 11. 7..
 */
public class OrderMenu {
    private String menuSid;
    private String menuName;
    private String menuCount;

    public String getMenuSid() {
        return menuSid;
    }

    public void setMenuSid(String menuSid) {
        this.menuSid = menuSid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(String menuCount) {
        this.menuCount = menuCount;
    }
}
