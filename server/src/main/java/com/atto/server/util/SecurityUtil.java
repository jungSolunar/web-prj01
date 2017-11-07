package com.atto.server.util;

import static com.atto.server.constant.SecurityConstant.UID_DATETIME_FORMAT;
import java.util.concurrent.ThreadLocalRandom;

import org.joda.time.DateTime;


public class SecurityUtil {

    private SecurityUtil () { /* static class */ }

    public static String createNewSid(String prefix){
        return prefix + getCurrentDateTimeToString() + getRandomTenNumbersToString();
    }

    public static String getCurrentDateTimeToString() {
        DateTime dateTime = DateTime.now();
        return dateTime.toString(UID_DATETIME_FORMAT);
    }

    public static String getRandomTenNumbersToString() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return String.valueOf(random.nextLong(1000000000L, 10000000000L));
    }

}
