package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import control.DateControl;
import utils.DateConverter;
import utils.gui.LayoutHelper;
import utils.messenger.Notification;

public class DateInput {
    
    public DateInput(MainPage mP) {
        getTime(mP);
    }
    
    public void getTime(MainPage mP) {
        JFrame frame = new JFrame();
        
        DateTimePicker dateTimePicker;
        DatePickerSettings dateSettings = new DatePickerSettings();
        TimePickerSettings timeSettings = new TimePickerSettings();
        
        dateSettings.setAllowEmptyDates(false);
        dateSettings.setAllowKeyboardEditing(false);
        
        timeSettings.setAllowEmptyTimes(false);
        timeSettings.use24HourClockFormat();
        timeSettings.initialTime = LocalTime.NOON;
        timeSettings.generatePotentialMenuTimes(TimeIncrement.OneHour, null, null);
        timeSettings.setAllowKeyboardEditing(false);
        
        dateTimePicker = new DateTimePicker(dateSettings, timeSettings);
        dateTimePicker.setBounds(10, 10, 514, 25);
        
        JButton btnSave = new JButton("Confirm");
        btnSave.setBounds(403, 46, 121, 25);
        btnSave.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                LocalDateTime ldt = dateTimePicker.getDateTimeStrict();
                Date retDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                if (mP.getLblDateLabel() != null) {
                    mP.getLblDateLabel().setText("Date of first measurement: " + DateConverter.conv(retDate).toString());
                }
                mP.getMntmLoadData().setEnabled(true);
                DateControl.getInstance().setStartDate(retDate);
                new Notification("Time was registered successfull");
                mP.setEnabled(true);
                frame.dispose();
                
            }
        });
        
        new LayoutHelper(frame, "Choose date and time of first measurement", btnSave);
        frame.getContentPane().setLayout(null);
        frame.setSize(550, 121);
        frame.getContentPane().add(dateTimePicker);
        frame.getContentPane().add(btnSave);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
