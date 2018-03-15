package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import controll.DateControll;

public class DateCreator {
    
    private Integer days;
    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    private Date    mesDate;
    private Date    startDate;
    
    public DateCreator(Integer days, Integer hours, Integer minutes, Integer seconds) {
        setDays(days);
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
        if (DateControll.getInstance().isSet()) {
            setStartDate(DateControll.getInstance().getStartDate());
        }
        else {
            System.out.println("Date not set");
        }
    }
    
    private Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        
        return cal.getTime();
    }
    
    public Date getMesDate() {
        return mesDate;
    }
    
    public void setMesDate(Date mesDate) {
        this.mesDate = mesDate;
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
