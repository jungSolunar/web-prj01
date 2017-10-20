package com.atto.server.model;

import java.io.Serializable;

public class Device implements Serializable {

    private static final long serialVersionUID = -7317502390971387767L;

    private String deviceId;
    private String deviceName;
    private String modelName;
    private String certifiedNumber;
    private String serialNumber;
    private String macAddress;
    private String orderingOrganization;
    private String orderingOrganizationContact;
    private String deliveryDate;
    private String deliverer;
    private String note;
    private String updateUid;
    private String updateDtm;
    private String deviceUrl;
    private byte [] qrCode;

    public Device() {

    }

    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getCertifiedNumber() {
        return certifiedNumber;
    }
    public void setCertifiedNumber(String certifiedNumber) {
        this.certifiedNumber = certifiedNumber;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    public String getOrderingOrganization() {
        return orderingOrganization;
    }
    public void setOrderingOrganization(String orderingOrganization) {
        this.orderingOrganization = orderingOrganization;
    }
    public String getOrderingOrganizationContact() {
        return orderingOrganizationContact;
    }
    public void setOrderingOrganizationContact(String orderingOrganizationContact) {
        this.orderingOrganizationContact = orderingOrganizationContact;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getDeliverer() {
        return deliverer;
    }
    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
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

    public String getDeviceUrl() {
        return deviceUrl;
    }

    public void setDeviceUrl(String deviceUrl) {
        this.deviceUrl = deviceUrl;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        StringBuffer strbuf = new StringBuffer("{");
        strbuf.append("deviceId: " + deviceId + ", ");
        strbuf.append("deviceName: " + deviceName + ", ");
        strbuf.append("modelName: " + modelName + ", " );
        strbuf.append("certifiedNumber: " + certifiedNumber + ", ");
        strbuf.append("serialNumber: " + serialNumber + ", ");
        strbuf.append("macAddress: " + macAddress + ", ");
        strbuf.append("orderingOrganization: " + orderingOrganization + ", ");
        strbuf.append("orderingOrganizationContact: " + orderingOrganizationContact + ", ");
        strbuf.append("deliveryData: " + deliveryDate + ", ");
        strbuf.append("deliverer: " + deliverer + ", ");
        strbuf.append("note: " + note + ", ");
        strbuf.append("deviceUrl: " + deviceUrl + ", ");
        strbuf.append("updateUid: " + updateUid + ", ");
        strbuf.append("updateDtm: " + updateDtm);
        strbuf.append("}");
        return strbuf.toString();
    }

}
