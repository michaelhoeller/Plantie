package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import control.DateControl;

public class DateCreator {
    
    private Integer days;
    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    
    private Date    startDate;
    
    public DateCreator(String days, String hours, String minutes, String seconds) {
        setDays(Integer.parseInt(days));
        setHours(Integer.parseInt(hours));
        setMinutes(Integer.parseInt(minutes));
        setSeconds(Integer.parseInt(seconds));
        if (DateControl.getInstance().isSet()) {
            setStartDate(DateControl.getInstance().getStartDate());
        }
        else {
            System.out.println("Date not set");
        }
    }
    
    private Date addTime(Date date, Integer days, Integer hours, Integer minutes, Integer seconds) {
        if (days == null) {
            days = 0;
        }
        if (hours == null) {
            hours = 0;
        }
        if (minutes == null) {
            minutes = 0;
        }
        if (seconds == null) {
            seconds = 0;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        cal.add(Calendar.MINUTE, minutes);
        cal.add(Calendar.SECOND, seconds);
        
        return cal.getTime();
    }
    
    public Date getMesDate() {
        return addTime(startDate, getDays(), getHours(), getMinutes(), getSeconds());
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Integer getDays() {
        return days;
    }
    
    public void setDays(Integer days) {
        this.days = days;
    }
    
    public Integer getHours() {
        return hours;
    }
    
    public void setHours(Integer hours) {
        this.hours = hours;
    }
    
    public Integer getMinutes() {
        return minutes;
    }
    
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
    
    public Integer getSeconds() {
        return seconds;
    }
    
    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
}
