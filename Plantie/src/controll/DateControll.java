package controll;

import java.util.Date;

public final class DateControll {
    
    private static DateControll INSTANCE = null;
    private Date             startDate;
    private boolean          isSet    = false;
    
    private DateControll() {
    }
    
    public static DateControll getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DateControll();
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
