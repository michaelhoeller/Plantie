package control;

import java.util.Date;

public final class DateControl {
    
    private static DateControl INSTANCE = null;
    private Date             startDate;
    private boolean          isSet    = false;
    
    private DateControl() {
    }
    
    public static DateControl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DateControl();
        }
        return INSTANCE;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        if (startDate != null) {
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
