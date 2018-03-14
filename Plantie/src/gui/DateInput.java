package gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

public class DateInput {
    
    @SuppressWarnings("deprecation")
    public Date getDate() {
        
        JDateChooser jd = new JDateChooser();
        String message = "Choose start date:\n";
        Object[] params = { message, jd };
        JOptionPane.showConfirmDialog(null, params, "Start date", JOptionPane.PLAIN_MESSAGE);
        String s = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        s = sdf.format(((JDateChooser) params[1]).getDate());
        
        return new Date(s);
    }
    
}
