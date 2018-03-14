package core;

import java.util.Date;

public class Measurement {
    
    private Date    date;
    private Integer temperature;
    private Integer humidity;
    private Integer moistureLeft;
    private Integer moistureRight;
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Integer getTemperature() {
        return temperature;
    }
    
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
    
    public Integer getHumidity() {
        return humidity;
    }
    
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
    
    public Integer getMoistureLeft() {
        return moistureLeft;
    }
    
    public void setMoistureLeft(Integer moistureLeft) {
        this.moistureLeft = moistureLeft;
    }
    
    public Integer getMoistureRight() {
        return moistureRight;
    }
    
    public void setMoistureRight(Integer moistureRight) {
        this.moistureRight = moistureRight;
    }
    
}
