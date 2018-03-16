package utils;

import java.util.ArrayList;
import java.util.List;

import control.MeasurementControl;
import core.Measurement;
import utils.messenger.Notification;

public class StringSplitter {
    
    private String            completeDataStream;
    private List<Measurement> mesList;
    private Integer           counter = 0;
    
    public StringSplitter(String dataStream) {
        this.completeDataStream = dataStream;
        measurementGeneration();
    }
    
    private void measurementGeneration() {
        mesList = new ArrayList<Measurement>();
        String[] lines = completeDataStream.split("\\?");
        
        for (String lineString : lines) {
            String[] splitString = lineString.split("\\|");
            
            String[] timeString = splitString[0].split(";");
            String[] valueString = splitString[1].split(";");
            
            Measurement tempMes = new Measurement();
            tempMes.setDate(new DateCreator(timeString[0], timeString[1], timeString[2], timeString[3]).getMesDate());
            tempMes.setTemperature(Integer.parseInt(valueString[0]));
            tempMes.setHumidity(Integer.parseInt(valueString[1]));
            tempMes.setMoistureLeft(Integer.parseInt(valueString[2]));
            tempMes.setMoistureRight(Integer.parseInt(valueString[3]));
            
            mesList.add(tempMes);
            counter++;
        }
        
        MeasurementControl.getInstance().setHoldingList(getMesList());
        new Notification(counter + " datasets have been loaded");
    }
    
    public List<Measurement> getMesList() {
        return mesList;
    }
    
    public void setMesList(List<Measurement> mesList) {
        this.mesList = mesList;
    }
    
}
