package com.atto.server.model;

public class QrCode {

    private byte[] data;

    public QrCode() {
    }

    public QrCode(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
