package com.atto.server.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by dhjung on 2017. 8. 30..
 */
public class TimeUtil {
    private static String dbTimeFormat = "yyyy-MM-dd hh:mm:ss";

    public static long getCurrentTimeMillis() {
        return DateTime.now().getMillis();
    }

    public static DateTime getCurrentDate() {
        return new DateTime(getCurrentTimeMillis());
    }

    public static long getExpirationMillis(long expireInterval) {
        return getCurrentTimeMillis() + expireInterval;
    }

    public static String getCurrentDateString() {
        return getCurrentDate().toString(dbTimeFormat);
    }

    public static DateTime getExpirationDate(DateTime currentTime, long expireInterval) {
        return new DateTime(currentTime.getMillis() + expireInterval);
    }

    public static long convertStringTimeToLongMillis(String time){
        DateTimeFormatter fmt = DateTimeFormat.forPattern(dbTimeFormat);
        DateTime dt = DateTime.parse(time, fmt);
        return dt.getMillis();
    }
}