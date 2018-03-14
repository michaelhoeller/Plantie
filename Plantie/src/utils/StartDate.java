package utils;

import java.util.Date;

import gui.DateInput;

public final class StartDate {
    
    private static final StartDate INSTANCE = new StartDate(new DateInput());
    
    private Date                   startDate;
    
    private StartDate(DateInput dateInput) {
        setStartDate(dateInput.getDate());
    }
    
    public static StartDate getInstance() {
        return INSTANCE;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
