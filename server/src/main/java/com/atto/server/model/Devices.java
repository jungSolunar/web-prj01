package com.atto.server.model;

import java.io.Serializable;
import java.util.List;

public class Devices implements Serializable {

    private static final long serialVersionUID = -5286818901252954275L;

    private Integer total;
    private List<Device> devices;

    public Devices() {
    }

    public Devices(Integer total, List<Device> devices) {
        this.total = total;
        this.devices = devices;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        StringBuffer strbuf = new StringBuffer("{");
        strbuf.append("total: " + String.valueOf(total) + ", ");
        strbuf.append("devices: [");
        strbuf.append("] }");

        int count = 0;
        for (Device aDevice : devices) {
            strbuf.append(aDevice.toString());
            count++;
            if (count < devices.size()) {
                strbuf.append(", ");
            }
        }
        return strbuf.toString();
    }
}
