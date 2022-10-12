package com.smartSchool.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class AppUtility {

    public static Timestamp getCurrentTimeStamp() {

        return new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public static String getDeleteStamp() {
        return "_DeleteStamp_" + Calendar.getInstance().getTimeInMillis();
    }


    public static boolean isEmptyOrNull(Object object) {
        if (object == null) {
            return true;
        }
        if (object.equals("undefined")) {
            return true;
        }

        if (object instanceof Optional<?> && !((Optional<?>) object).isPresent()) {
            return true;
        }

        if (object instanceof String) {
            String objString = object.toString();
            if (objString.trim().length() <= 0) {
                return true;
            }
        }
        if (object instanceof StringBuilder) {
            StringBuilder stringBuilder = (StringBuilder) object;
            if (stringBuilder.toString().trim().length() <= 0) {
                return true;
            }
        }
        if (object instanceof ArrayList<?> || object instanceof List<?>) {
            if (((List) object).isEmpty()) {
                return true;
            }
        }
        return false;
    }


}
