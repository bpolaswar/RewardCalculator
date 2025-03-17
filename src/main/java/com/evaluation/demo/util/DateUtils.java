package com.evaluation.demo.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class to put date related utility functions
 */
public class DateUtils {
    public static Date getStartDateOfLastThreeMonths() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -2);
        return calendar.getTime();
    }
}
