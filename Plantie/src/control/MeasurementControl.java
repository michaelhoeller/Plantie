package control;

import java.util.ArrayList;
import java.util.List;

import core.Measurement;

public final class MeasurementControl {
    
    private static MeasurementControl INSTANCE    = null;
    private List<Measurement>         holdingList = new ArrayList<Measurement>();
    private boolean                   isSet       = false;
    
    private MeasurementControl() {
    }
    
    public static MeasurementControl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeasurementControl();
        }
        return INSTANCE;
    }
    
    public List<Measurement> getHoldingList() {
        return holdingList;
    }
    
    public void setHoldingList(List<Measurement> holdingList) {
        this.holdingList = holdingList;
        if (!holdingList.isEmpty()) {
            setSet(true);
        }
    }
    
    public boolean isSet() {
        return isSet;
    }
    
    public void setSet(boolean isSet) {
        this.isSet = isSet;
    }
    
}
