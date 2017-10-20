package com.atto.server.service;

import java.util.List;

import com.atto.server.model.Device;
import com.atto.server.model.PagingParam;

public interface DeviceService {

    Device getDevice(String deviceId);
    Device addDevice(Device device);
    Device modifyDevice(Device device);
    void removeDevice(String deviceId);
    int getDeviceCount();

    List<Device> getDeivces();
    List<Device> getDeivcesWithPaging(PagingParam pagingParam);

    byte[] getQrCode(String deviceId);
}
