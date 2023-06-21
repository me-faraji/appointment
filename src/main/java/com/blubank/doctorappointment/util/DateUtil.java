package com.blubank.doctorappointment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static Date toDate(String strDate) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(strDate);
    }
    public static long diffInTime(String fromDate, String toDate) throws ParseException {
        Date d1 = SIMPLE_DATE_FORMAT.parse(fromDate);
        Date d2 = SIMPLE_DATE_FORMAT.parse(toDate);
        return d2.getTime() - d1.getTime();
    }
    public static long diffInDays(long time) {
        return TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);
    }
    public static long diffInMinutes(long time) {
        return TimeUnit.MINUTES.convert(time, TimeUnit.MILLISECONDS);
    }
    public static Date plusMinute(Date date, int val) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, val);
        System.out.println(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
        return c.getTime();
    }
}
