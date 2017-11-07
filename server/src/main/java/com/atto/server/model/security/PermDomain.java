package com.atto.server.model.security;

/**
 * Created by dhjung on 2017. 9. 1..
 */
public class PermDomain {
    String permDomainUid;
    String permDomainName;
    String saveUid;
    String saveDtm;

    public String getPermDomainUid() {
        return permDomainUid;
    }

    public void setPermDomainUid(String permDomainUid) {
        this.permDomainUid = permDomainUid;
    }

    public String getPermDomainName() {
        return permDomainName;
    }

    public void setPermDomainName(String permDomainName) {
        this.permDomainName = permDomainName;
    }

    public String getSaveUid() {
        return saveUid;
    }

    public void setSaveUid(String saveUid) {
        this.saveUid = saveUid;
    }

    public String getSaveDtm() {
        return saveDtm;
    }

    public void setSaveDtm(String saveDtm) {
        this.saveDtm = saveDtm;
    }
}
