package utils.messenger;

import java.util.Date;

import control.CenterControl;
import utils.DateConverter;

public class LogError {
    
    public LogError(String msg) {
        CenterControl.getInstance().addErrorString("Error at: " + DateConverter.conv(new Date()).toString() + " " + msg);
    }
}
