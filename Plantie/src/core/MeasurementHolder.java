package core;

import java.util.ArrayList;
import java.util.List;

import utils.StringSplitter;

public class MeasurementHolder {
    
    private List<Measurement> holdingList = new ArrayList<Measurement>();
    
    public MeasurementHolder(String dataStream) {
        new StringSplitter(dataStream, this);
    }
    
    public List<Measurement> getHoldingList() {
        return holdingList;
    }
}
