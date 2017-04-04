package com.haitran.handbook.util;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Hai Tran on 1/10/2017.
 */

public class HelpUtil {

    public static int getImageId(String nameImage, Context context) {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(nameImage, "drawable", pkgName);
        return resID;
    }

    public static String tinhTuoiThai(String lastDate) {
        Calendar c = Calendar.getInstance();
        Calendar due = getOneDate(lastDate, 0);
        long different = c.getTimeInMillis() - due.getTimeInMillis();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long day = different / daysInMilli;
        long a = day % 7;
        long week = day / 7;
        if (a == 0) {
            return week + " tuần";
        } else if (day < 7) {
            return day + " ngày";
        }
        return week + " tuần " + a + " ngày";
    }

    public static int tuanThuMay(String lastDate) {
        Calendar c = Calendar.getInstance();
        Calendar due = getOneDate(lastDate, 0);
        long different = c.getTimeInMillis() - due.getTimeInMillis();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long day = different / daysInMilli;
        long a = day % 7;
        long week = day / 7;
        if (a != 0) {
            return (int) (week + 1);
        } else {
            return (int) week;
        }
    }

    public static int tinhPhanTram(String lastDate) {
        Calendar c = Calendar.getInstance();
        Calendar due = getOneDate(lastDate, 0);
        long different = c.getTimeInMillis() - due.getTimeInMillis();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long day = different / daysInMilli;
        int phanTram = (int) (((float) day / (float) 280) * 100);
        return phanTram;
    }
    

    public static String tinhNgayConLai(String dueDate) {
        Calendar c = Calendar.getInstance();
        Calendar due = getOneDate(dueDate, 0);
        long different = due.getTimeInMillis() - c.getTimeInMillis();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long day = different / daysInMilli;
        return day + " ngày";
    }

    public static Calendar getOneDate(String date, int a) {
        SimpleDateFormat
                sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_YEAR, a);
        return c;
    }
}
