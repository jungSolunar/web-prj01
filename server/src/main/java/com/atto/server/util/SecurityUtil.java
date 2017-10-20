package com.atto.server.util;

import static com.atto.server.constant.SecurityConstant.DEVICE_UID_PREFIX;
import static com.atto.server.constant.SecurityConstant.DEVICE_URL_PREFIX;
import static com.atto.server.constant.SecurityConstant.UID_DATETIME_FORMAT;
import static com.atto.server.constant.SecurityConstant.USER_UID_PREFIX;

import java.net.InetAddress;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.joda.time.DateTime;


public class SecurityUtil {

    private SecurityUtil () { /* static class */ }

    public static String createNewUserUid() {
        // USR + yyyyMMddHHmmssSSS + random 10 digit
        return USER_UID_PREFIX + getCurrentDateTimeToString() + getRandomTenNumbersToString();
    }

    public static String createNewDeviceUid() {
        // DEV + yyyyMMddHHmmssSSS + random 10 digit
        return DEVICE_UID_PREFIX + getCurrentDateTimeToString() + getRandomTenNumbersToString();
    }

    public static String getCurrentDateTimeToString() {
        DateTime dateTime = DateTime.now();
        return dateTime.toString(UID_DATETIME_FORMAT);
    }

    public static String getRandomTenNumbersToString() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return String.valueOf(random.nextLong(1000000000L, 10000000000L));
    }

    public static String getDeviceUrl(String deviceId) {
        String url = DEVICE_URL_PREFIX;
//        try {
//            Configuration config = new PropertiesConfiguration("application.properties");
//
//            String ip = config.getString("server.ip");
//            String port = config.getString("server.port");
//            url = url.replace("{ip}", ip);
//            url = url.replace("{port}", port);
//            System.out.println(url);
//        }catch (Exception e){
//            System.out.println("pass");
//        }
        return new String(DEVICE_URL_PREFIX + deviceId);
    }
}
