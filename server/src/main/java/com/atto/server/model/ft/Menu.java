package com.atto.server.model.ft;

/**
 * Created by dhjung on 2017. 8. 28..
 */
public class Menu {
    String menuSid;
    String menuName;
    String storeSid;
    String menuPrice;
    String menuImgSid;
    String menuPriority;
    String menuState;
    Boolean visible;
    String note;

    public Menu(){

    }

    public Menu(String storeSid, String menuSid){
        this.storeSid = storeSid;
        this.menuSid = menuSid;
    }

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

    public String getStoreSid() {
        return storeSid;
    }

    public void setStoreSid(String storeSid) {
        this.storeSid = storeSid;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuImgSid() {
        return menuImgSid;
    }

    public void setMenuImgSid(String menuImgSid) {
        this.menuImgSid = menuImgSid;
    }

    public String getMenuPriority() {
        return menuPriority;
    }

    public void setMenuPriority(String menuPriority) {
        this.menuPriority = menuPriority;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
