package com.steven.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Steven on 2016/11/8.
 */
public class DateApp {
    private static Date date = null;
    private static SimpleDateFormat simpleDateFormat = null;
    private static Calendar calendar = Calendar.getInstance();

    public DateApp() {
        date = new Date();
    }

    public static void run() {
        DateApp dateApp = new DateApp();
        dateApp.datePrint();
        dateApp.calendarPrint();
    }

    public void datePrint() {
        System.out.println("CST时间 : " + date); //Tue Nov 08 10:21:16 CST 2016
        System.out.println("GMT时间 : " + date.toGMTString()); //8 Nov 2016 02:22:17 GMT
        System.out.println("Local表示 : " + date.toLocaleString()); //2016-11-8 10:25:03
        System.out.println("Year: " + (date.getYear() + 1900) + " Month: " + (date.getMonth() + 1) + " Day: " + date.getDate() + " Time: " + date.getHours() + ":" + date.getMinutes());
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));
        try {
            System.out.println(simpleDateFormat.parse("2016-11-08 14:18:22"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void calendarPrint() {
        //Month in Calendar minus 1
        //Sunday is the first in a week
        calendar.set(2016, 10, 8, 14, 36, 20);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        System.out.println(year + " " + month + " " + date + " " + day);
    }


}
