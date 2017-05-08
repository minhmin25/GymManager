package com.dev.minhmin.gymmanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
