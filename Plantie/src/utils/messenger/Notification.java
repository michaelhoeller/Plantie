package utils.messenger;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import control.CenterControl;
import utils.Constants;
import utils.DateConverter;

public class Notification {
    
    public Notification(String msg) {
        if (!CenterControl.getInstance().isAutoMode()) {
            ImageIcon imgIco = createImageIcon(Constants.ICONPATH);
            Image converted = getScaledImage(imgIco.getImage(), 40, 40);
            ImageIcon test = new ImageIcon(converted);
            JOptionPane.showMessageDialog(null, msg, "Plantie - Information", JOptionPane.INFORMATION_MESSAGE, test);
        }
        CenterControl.getInstance().addErrorString("Notification at: " + DateConverter.conv(new Date()).toString() + " " + msg);
    }
    
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        
        return resizedImg;
    }
    
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
