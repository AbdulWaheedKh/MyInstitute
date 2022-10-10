package com.myinstitute.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class AppUtility {

    public static Timestamp getCurrentTimeStamp() {

        return new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public static String getDeleteStamp() {
        return "_DeleteStamp_" + Calendar.getInstance().getTimeInMillis();
    }

}
