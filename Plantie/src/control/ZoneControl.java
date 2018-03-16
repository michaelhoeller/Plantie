package control;

import utils.messenger.Notification;

public class ZoneControl {
    
    private static ZoneControl INSTANCE;
    private String             DefaultTempZone  = "], zones : [ {value : 17, color : '#1ba59e'}, {value : 30, color : '#2ca51c'}, {color : '#e51010'}";
    private String             DefaultHumZone   = "], zones : [ {value : 30, color : '#1ba59e'}, {value : 60, color : '#2ca51c'}, {color : '#e51010'}";
    private String             DefaultWaterZone = "], zones : [ {value : 250, color : '#1ba59e'}, {value : 350, color : '#2ca51c'}, {color : '#e51010'}";
    
    private Integer            tempBad          = 17;
    private Integer            tempGood         = 30;
    private Integer            humBad           = 30;
    private Integer            humGood          = 60;
    private Integer            waterBad         = 300;
    private Integer            waterGood        = 450;
    
    private Boolean            isCostumized     = false;
    
    private Boolean            isNotified       = false;
    
    private ZoneControl() {
    }
    
    public static ZoneControl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZoneControl();
        }
        return INSTANCE;
    }
    
    public void setCustomValues(Integer tempGood, Integer tempBad, Integer humGood, Integer humBad, Integer waterGood, Integer waterBad) {
        this.tempGood = tempGood;
        this.tempBad = tempBad;
        this.humGood = humGood;
        this.humBad = humBad;
        this.waterGood = waterGood;
        this.waterBad = waterBad;
        this.isCostumized = true;
        
    }
    
    public String getDefaultTempZone() {
        return DefaultTempZone;
    }
    
    public String getCustomTempZone() {
        if (!isCostumized && !isNotified) {
            new Notification("No custom value areas specified!\nUsing default values instead.");
            isNotified = true;
        }
        return "], zones : [ {value : " + getTempBad() + ", color : '#1ba59e'}, {value : " + getTempGood() + ", color : '#2ca51c'}, {color : '#e51010'}";
    }
    
    public void setDefaultTempZone(String defaultTempZone) {
        DefaultTempZone = defaultTempZone;
    }
    
    public String getDefaultHumZone() {
        return DefaultHumZone;
    }
    
    public String getCustomHumZone() {
        if (!isCostumized && !isNotified) {
            new Notification("No custom value areas specified!\nUsing default values instead.");
            isNotified = true;
        }
        return "], zones : [ {value : " + getHumBad() + ", color : '#1ba59e'}, {value : " + getHumGood() + ", color : '#2ca51c'}, {color : '#e51010'}";
    }
    
    public void setDefaultHumZone(String defaultHumZone) {
        DefaultHumZone = defaultHumZone;
    }
    
    public String getDefaultWaterZone() {
        return DefaultWaterZone;
    }
    
    public String getCustomWaterZone() {
        if (!isCostumized && !isNotified) {
            new Notification("No custom value areas specified!\nUsing default values instead.");
            isNotified = true;
        }
        return "], zones : [ {value : " + getWaterBad() + ", color : '#1ba59e'}, {value : " + getWaterGood() + ", color : '#2ca51c'}, {color : '#e51010'}";
    }
    
    public void setDefaultWaterZone(String defaultWaterZone) {
        DefaultWaterZone = defaultWaterZone;
    }
    
    public void setIsNotified(Boolean isNotified) {
        this.isNotified = isNotified;
    }
    
    public Boolean getIsCostumized() {
        return isCostumized;
    }
    
    public void setIsCostumized(Boolean isCostumized) {
        this.isCostumized = isCostumized;
    }
    
    public Integer getTempGood() {
        return tempGood;
    }
    
    public void setTempGood(Integer tempGood) {
        this.tempGood = tempGood;
    }
    
    public Integer getTempBad() {
        return tempBad;
    }
    
    public void setTempBad(Integer tempBad) {
        this.tempBad = tempBad;
    }
    
    public Integer getHumGood() {
        return humGood;
    }
    
    public void setHumGood(Integer humGood) {
        this.humGood = humGood;
    }
    
    public Integer getHumBad() {
        return humBad;
    }
    
    public void setHumBad(Integer humBad) {
        this.humBad = humBad;
    }
    
    public Integer getWaterGood() {
        return waterGood;
    }
    
    public void setWaterGood(Integer waterGood) {
        this.waterGood = waterGood;
    }
    
    public Integer getWaterBad() {
        return waterBad;
    }
    
    public void setWaterBad(Integer waterBad) {
        this.waterBad = waterBad;
    }
    
}
