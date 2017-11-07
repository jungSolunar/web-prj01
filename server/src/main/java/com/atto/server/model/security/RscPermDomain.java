package com.atto.server.model.security;

/**
 * Created by dhjung on 2017. 9. 19..
 */
public class RscPermDomain {
    String rscUid;
    String permDomainUid;
    String saveUid;
    String saveDtm;

    public String getRscUid() {
        return rscUid;
    }

    public void setRscUid(String rscUid) {
        this.rscUid = rscUid;
    }

    public String getPermDomainUid() {
        return permDomainUid;
    }

    public void setPermDomainUid(String permDomainUid) {
        this.permDomainUid = permDomainUid;
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
