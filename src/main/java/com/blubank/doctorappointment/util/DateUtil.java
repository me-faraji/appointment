package com.blubank.doctorappointment.util;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.ULocale;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
//    public static Date parse(String strDate, String pattern) throws ParseException {
//        return SIMPLE_DATE_FORMAT.parse(strDate);
//    }
    public static Date parse(String strDate, EPattern pattern) throws ParseException {
        ULocale locale = new ULocale("en_US@calendar=persian");
        DateFormat df = new com.ibm.icu.text.SimpleDateFormat(pattern.format, locale);
        return df.parse(strDate);
    }
    public static long diffInTime(String fromDate, String toDate, EPattern pattern) throws ParseException {
        ULocale locale = new ULocale("en_US@calendar=persian");
        DateFormat df = new com.ibm.icu.text.SimpleDateFormat(pattern.format, locale);
        Date d1 = df.parse(fromDate);
        Date d2 = df.parse(toDate);
        return d2.getTime() - d1.getTime();
    }
    public static long diffInDays(long time) {
        return TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);
    }
    public static long diffInMinutes(long time) {
        return TimeUnit.MINUTES.convert(time, TimeUnit.MILLISECONDS);
    }
    public static Date plusMinute(Date date, int val, EPattern pattern) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, val);
        return c.getTime();
    }
    public static String parse(Date date, EPattern pattern) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (pattern == EPattern.HH_mm) return c.get(Calendar.HOUR_OF_DAY) + "" + (c.get(Calendar.MINUTE) == 0 ? "" : ":" + c.get(Calendar.MINUTE));
        return null;
    }
    public enum EPattern {
        DD_MM_YYYY_HH_mm_SS("dd-MM-yyyy HH:mm:ss"),
        HH_mm("HH:mm"),
        DD_MM_YYYY("dd-MM-yyyy");

        private final String format;
        EPattern(String format) { this.format = format; }
        public String format() {return this.format; }
    }
}
