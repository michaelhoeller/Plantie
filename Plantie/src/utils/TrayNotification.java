package utils;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;

public class TrayNotification {
    
    public TrayNotification(String msg) throws MalformedURLException, AWTException {
        displayTray(msg);
    }
    
    private void displayTray(String msg) throws AWTException, MalformedURLException {
        
        if (SystemTray.isSupported()) {
            // Obtain only one instance of the SystemTray object
            SystemTray tray = SystemTray.getSystemTray();
            
            final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("res/icon2.png"));
            
            // Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            // Set tooltip text for the tray icon
            trayIcon.setToolTip("Plantie");
            tray.add(trayIcon);
            
            trayIcon.displayMessage(msg, "Information was provided by Plantie", MessageType.NONE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Could not access system tray\n\nusing JOptionPane instead", "WARNING", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
