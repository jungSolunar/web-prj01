package com.atto.server.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.atto.server.model.Device;
import com.atto.server.model.PagingParam;
import com.atto.server.model.QrCode;

@Mapper
public interface DeviceMapper {

    /* DEVICE_INFO table */
    Device selectDevice(String deviceId);
    QrCode selectDeviceQrCode(String deviceId);
    void insertDevice(Device device);
    void updateDevice(Device device);
    void deleteDevice(String deivceId);

    List<Device> selectDevices();
    List<Device> selectDevicesWithPaging(PagingParam pagingParam);
    int selectDeviceCount();

    List<String> selectTableColumnNames(String tableName);
}
