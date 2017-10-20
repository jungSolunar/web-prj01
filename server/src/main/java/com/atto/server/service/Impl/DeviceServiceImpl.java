package com.atto.server.service.Impl;

import static com.atto.server.constant.SecurityConstant.DEVICE_TABLE_NAME;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atto.server.db.mapper.DeviceMapper;
import com.atto.server.model.Device;
import com.atto.server.model.PagingParam;
import com.atto.server.model.QrCode;
import com.atto.server.service.DeviceService;
import com.atto.server.util.QrUtil;
import com.atto.server.util.SecurityContext;
import com.atto.server.util.SecurityUtil;
import com.atto.server.util.TimeUtil;

@Service
public class DeviceServiceImpl implements DeviceService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final List<String> tableColumnNames;

    @Autowired
    private final DeviceMapper deviceMapper;

    public DeviceServiceImpl(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
        tableColumnNames = getColumnNamesFromDatabase(DEVICE_TABLE_NAME);
        testDevice();
    }

    private void testDevice(){
        List<Device> deviceList = deviceMapper.selectDevices();
        for(Device device:deviceList){
            String deviceUrl = SecurityUtil.getDeviceUrl(device.getDeviceId());
            byte[] qrCode = QrUtil.createQrCodeForUrl(deviceUrl);

            device.setDeviceUrl(deviceUrl);
            device.setQrCode(qrCode);

            deviceMapper.deleteDevice(device.getDeviceId());
            deviceMapper.insertDevice(device);
        }
    }

    @Override
    public Device getDevice(String deviceId) {
        logger.debug("getDevice deivceId=" + deviceId);
        return deviceMapper.selectDevice(deviceId);
    }

    @Override
    public Device addDevice(Device device) {
        String deviceId = SecurityUtil.createNewDeviceUid();
        device.setDeviceId(deviceId);

        String deviceUrl = SecurityUtil.getDeviceUrl(deviceId);
        byte[] qrCode = QrUtil.createQrCodeForUrl(deviceUrl);

        device.setDeviceUrl(deviceUrl);
        device.setQrCode(qrCode);

        logger.debug("addDevice new device=" + device.toString());
        logger.debug("addDevice qrCode size = " + qrCode.length);

        deviceMapper.insertDevice(device);
        return deviceMapper.selectDevice(device.getDeviceId());
    }

    @Override
    public Device modifyDevice(Device device) {
        device.setUpdateUid(SecurityContext.get().getUserUid());
        device.setUpdateDtm(TimeUtil.getCurrentDateString());
        logger.debug("modifyDevice updated device=" + device.toString());

        deviceMapper.updateDevice(device);
        return deviceMapper.selectDevice(device.getDeviceId());
    }

    @Override
    public void removeDevice(String deviceId) {
        logger.debug("deleteDevice deviceId=" + deviceId);
        deviceMapper.deleteDevice(deviceId);
    }

    @Override
    public List<Device> getDeivces() {
        logger.debug("getDevices");
        return deviceMapper.selectDevices();
    }

    @Override
    public List<Device> getDeivcesWithPaging(PagingParam pagingParam) {
        logger.debug("getDeivcesWithPaging " + pagingParam.toString());
        pagingParam.validateOrderColumn(tableColumnNames);
        return deviceMapper.selectDevicesWithPaging(pagingParam);
    }

    @Override
    public byte[] getQrCode(String deviceId) {
        logger.debug("getQrCode deviceId=" + deviceId);

        QrCode qrCode = deviceMapper.selectDeviceQrCode(deviceId);
        if (qrCode == null) {
            return null;
        } else {
            writeQrCode(deviceId, qrCode); /* for test */
            return qrCode.getData();
        }
    }

    private void writeQrCode(String deviceId, QrCode qrCode) {
        if (qrCode == null) return;

        try {
            OutputStream out = new FileOutputStream("/tmp/qrcode-" + deviceId);
            out.write(qrCode.getData());
            out.flush();
            out.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getDeviceCount() {
        logger.debug("getDeviceCount");
        return deviceMapper.selectDeviceCount();
    }

    private List<String> getColumnNamesFromDatabase(String tableName) {
        logger.debug("getColumnNamesFromDatabase");
        List<String> columnNames = deviceMapper.selectTableColumnNames(tableName);
        if (columnNames == null) {
            logger.debug("getColumnNamesFromDatabase columnNames is null");
        } else {
            logger.debug("getColumnNamesFromDatabase columnNames.size=" + columnNames.size());
        }
        return columnNames;
    }
}
