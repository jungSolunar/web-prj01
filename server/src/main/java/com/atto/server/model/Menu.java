package com.atto.server.model;

/**
 * Created by dhjung on 2017. 8. 28..
 */
public class Menu {
    String menuUid;
    String menuTypeCode;
    Integer displayOrder;
    String rscUid;
    Boolean topYn;
    String parentUid;
    String name;
    String note;
    Boolean activeYn;
    String createUid;
    String createDtm;
    String updateUid;
    String updateDtm;

    public String getMenuUid() {
        return menuUid;
    }

    public void setMenuUid(String menuUid) {
        this.menuUid = menuUid;
    }

    public String getMenuTypeCode() {
        return menuTypeCode;
    }

    public void setMenuTypeCode(String menuTypeCode) {
        this.menuTypeCode = menuTypeCode;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getRscUid() {
        return rscUid;
    }

    public void setRscUid(String rscUid) {
        this.rscUid = rscUid;
    }

    public Boolean getTopYn() {
        return topYn;
    }

    public void setTopYn(Boolean topYn) {
        this.topYn = topYn;
    }

    public String getParentUid() {
        return parentUid;
    }

    public void setParentUid(String parentUid) {
        this.parentUid = parentUid;
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

    public Boolean getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid;
    }

    public String getCreateDtm() {
        return createDtm;
    }

    public void setCreateDtm(String createDtm) {
        this.createDtm = createDtm;
    }

    public String getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid;
    }

    public String getUpdateDtm() {
        return updateDtm;
    }

    public void setUpdateDtm(String updateDtm) {
        this.updateDtm = updateDtm;
    }
}
