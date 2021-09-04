/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class DateCommon {
    
    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date convertStringToDateByPattern(String strTime, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(strTime);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static java.sql.Date convertDateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }
    
    public static Timestamp convertDateToTimestamp(Date date) {
        return new java.sql.Timestamp(date.getTime());
    }
    
    public static Date convertTimestampToDate(Timestamp time) {
        return new Date(time.getTime());
    }
    
}
