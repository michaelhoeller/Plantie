package utils;

import javax.swing.JOptionPane;

public class Constants {
    
    public final static String SEPARATOR_VALUES = ";";
    public final static String SEPARATOR_MIDDLE = "|";
    public final static String SEPARATOR_EOL    = "?";
    
    public static final String FILENAME         = "data.txt";
    public static final String HTMLPATH         = "/res/ChartTemplate.html";
    public static final String ICONPATH         = "/res/icon2.png";
    
    public static final String FTP_HOST         = "ftp.strato.de";
    public static final String FTP_USER         = "urlaubsplaner@zahnarzt-hoeller.de";
    public static final String FTP_PASS         = JOptionPane.showInputDialog("Passwort für ftp connection eingeben:");
    
    public final static String REMOTE_ADRESS    = "/Plantie/";
    public static final String CHART_FILENAME   = "currentChart.html";
    public static boolean      UPLOAD_ENABLED   = false;
    
    public static String getFTP_PASSWORD() {
        return FTP_PASS.toString();
    }
    
}
