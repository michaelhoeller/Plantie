package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    
    public static String conv(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm", Locale.GERMAN);
        return sdf.format(date);
    }
    
    public static String convWithS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.GERMAN);
        return sdf.format(date);
    }
    
    public static String convWithMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy HH:mm:ss.SSSS", Locale.GERMAN);
        return sdf.format(date);
    }
}
