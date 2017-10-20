package com.atto.server.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atto.server.model.Device;
import com.atto.server.model.Devices;
import com.atto.server.model.PagingParam;
import com.atto.server.service.DeviceService;

@RestController
public class DeviceController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public Devices getDevicesWithPaging(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "size", defaultValue = "5", required=false) int size,
            @RequestParam(value = "startIdx", defaultValue = "0", required=false) int startIdx,
            @RequestParam(value = "orderCol", defaultValue = "DEVICE_ID", required=false) String orderCol,
            @RequestParam(value = "order", defaultValue = "ASC", required=false) String order) {

        PagingParam pagingParam = new PagingParam(size, startIdx, orderCol, order);

        logger.trace("[REQUEST] GET " + request.getRequestURI() + " Params: " + pagingParam.toString() );

        List<Device> deviceList = deviceService.getDeivcesWithPaging(pagingParam);
        int deviceCount = deviceService.getDeviceCount();

        Devices devices = new Devices(deviceCount, deviceList);

        return devices;
    }

    @RequestMapping(value = "/devices/{deviceId:.*}", method = RequestMethod.GET)
    public Device getDevice(HttpServletRequest request, HttpServletResponse response, @PathVariable String deviceId) {

        logger.trace("[REQUEST] GET /devices/{deviceId} : deviceId = " + deviceId);

        Device device = deviceService.getDevice(deviceId);

        if (device == null) {
            logger.debug("[RESPONSE] GET /devices/" + deviceId + " NOT_FOUND");
            response.setStatus(HttpStatus.SC_NOT_FOUND);
        } else {
            logger.debug("[RESPONSE] GET /devices/" + deviceId + " " + device.toString());

        }

        return device;
    }

    @RequestMapping(value = "/devices", method = RequestMethod.POST)
    public Device addDevice(HttpServletRequest request, HttpServletResponse response, @RequestBody Device requestDevice) {

        logger.trace("[REQUEST] POST /devices " + requestDevice.toString());

        Device newDevice = null;
        try {
            newDevice = deviceService.addDevice(requestDevice);
        } catch (DuplicateKeyException dke) {
            logger.error("Failed to add device : " + dke.getLocalizedMessage());
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[RESPONSE] POST /devices : failed " + requestDevice.toString());
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        if (newDevice == null) {
            logger.debug("[RESPONSE] POST /devices : failed to create new Device " + requestDevice.toString());
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        } else {
            response.setStatus(HttpStatus.SC_CREATED);
        }

        return newDevice;
    }

    @RequestMapping(value = "/devices/{deviceId:.*}", method = RequestMethod.PUT)
    public Device updateDevice(HttpServletRequest request, HttpServletResponse response, @PathVariable String deviceId, @RequestBody Device requestDevice) {

        logger.trace("[REQUEST] PUT /devices/" + deviceId);

        if (requestDevice.getDeviceId() == null) {
            requestDevice.setDeviceId(deviceId);
        }

        Device updateDevice = null;
        try {
            updateDevice = deviceService.modifyDevice(requestDevice);
        } catch (DuplicateKeyException dke) {
            logger.error("Failed to update device : " + dke.getLocalizedMessage());
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[RESPONSE] PUT /devices/{deviceId} : failed " + deviceId + " " + e.getLocalizedMessage());
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }

        if (updateDevice == null) {
            logger.debug("[RESPONSE] PUT /devices/{deviceId} : failed to find Device [deviceId=" + deviceId + "]" );
            response.setStatus(HttpStatus.SC_NOT_FOUND);
        }

        return updateDevice;
    }

    @RequestMapping(value = "/devices/{deviceId}", method = RequestMethod.DELETE)
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String deviceId) {

        logger.trace("[REQUEST] DELETE /devices/{deviceId} : deviceId = " + deviceId);

        response.setStatus(HttpStatus.SC_NO_CONTENT);

        deviceService.removeDevice(deviceId);
    }

    @RequestMapping(value = "/devices/qrcodes/{deviceId:.*}", method = RequestMethod.GET)
    public void getDeviceQrcode(HttpServletRequest request, HttpServletResponse response, @PathVariable String deviceId) throws IOException {

        logger.trace("[REQUEST] GET /devices/qrcodes/{deviceId} : deviceId = " + deviceId);

        byte[] qrCode = deviceService.getQrCode(deviceId);
        byte[] encodedQrCode = null;

        if (qrCode == null) {
            logger.debug("[RESPONSE] GET /devices/qrcodes/" + deviceId + " NOT_FOUND");
            response.setStatus(HttpStatus.SC_NOT_FOUND);
        } else {
            // FIXME using HttpMessageConverter
            logger.debug("[RESPONSE] GET /devices/qrcodes/" + deviceId + " size=" + qrCode.length);
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            encodedQrCode = Base64.getEncoder().encode(qrCode);
            writeImageToResponseBody(response, encodedQrCode);
        }
    }

    private void writeImageToResponseBody(HttpServletResponse response, byte[] encodedQrCode) throws IOException {
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(encodedQrCode);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
