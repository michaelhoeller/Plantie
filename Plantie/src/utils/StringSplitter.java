package utils;

import javax.swing.JOptionPane;

import core.Measurement;
import core.MeasurementHolder;

public class StringSplitter {
    
    private String            completeDataStream;
    private MeasurementHolder MH;
    private Integer           counter = 0;
    
    public StringSplitter(String dataStream, MeasurementHolder MH) {
        this.MH = MH;
        this.completeDataStream = dataStream;
        measurementGeneration();
    }
    
    private void measurementGeneration() {
        String[] lines = completeDataStream.split("\\?");
        
        for (String lineString : lines) {
            String[] splitString = lineString.split("\\|");
            
            String[] timeString = splitString[0].split(";");
            String[] valueString = splitString[1].split(";");
            
            Measurement tempMeas = new Measurement();
            tempMeas.setTemperature(Integer.parseInt(valueString[0]));
            tempMeas.setHumidity(Integer.parseInt(valueString[1]));
            tempMeas.setMoistureLeft(Integer.parseInt(valueString[2]));
            tempMeas.setMoistureRight(Integer.parseInt(valueString[3]));
            
            MH.getHoldingList().add(tempMeas);
            counter++;
        }
        try {
            new TrayNotification("Number of measurements: " + counter);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not read measurements from txt-file", "Critical Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}