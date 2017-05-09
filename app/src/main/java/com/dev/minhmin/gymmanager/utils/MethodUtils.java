package com.dev.minhmin.gymmanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Minh min on 4/19/2017.
 */

public class MethodUtils {
    public Date stringTodate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String dateTostring(Date time) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return mySimpleDateFormat.format(time);
    }

    public String getTimeNow() {
        Date date = new Date();

        String strDateFormat = "dd-MM-yyyy";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        return simpleDateFormat.format(date);
    }

    public String UpDownDay(String time, int day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c1 = Calendar.getInstance();
        Date date = stringTodate(time);
        c1.setTime(date);

        c1.add(Calendar.DATE, day);

        return dateFormat.format(c1.getTime());
    }

}
