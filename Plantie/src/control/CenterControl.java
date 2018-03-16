package control;

public class CenterControl {
    
    private static CenterControl INSTANCE;
    private boolean              autoMode    = false;
    private boolean              zoneControl = false;
    private String               errorString = "";
    
    private CenterControl() {
    }
    
    public static CenterControl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CenterControl();
        }
        return INSTANCE;
    }
    
    public boolean isAutoMode() {
        return autoMode;
    }
    
    public void setAutoMode(boolean autoMode) {
        this.autoMode = autoMode;
    }
    
    public boolean isZoneControl() {
        return zoneControl;
    }
    
    public void setZoneControl(boolean zoneControl) {
        this.zoneControl = zoneControl;
    }
    
    public String getErrorString() {
        return errorString;
    }
    
    public void addErrorString(String errorString) {
        this.errorString += errorString + "\n";
    }
}
