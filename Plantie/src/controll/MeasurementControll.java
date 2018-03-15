package controll;

import java.util.ArrayList;
import java.util.List;

import core.Measurement;

public class MeasurementControll {
    
    private static MeasurementControll INSTANCE    = null;
    private List<Measurement>          holdingList = new ArrayList<Measurement>();
    private boolean                    isSet       = false;
    
    public static MeasurementControll getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeasurementControll();
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
