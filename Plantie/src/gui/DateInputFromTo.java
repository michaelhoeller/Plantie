package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import control.CustomChartControl;
import utils.gui.LayoutHelper;
import utils.messenger.Notification;

public class DateInputFromTo {
    
    private JCheckBox chckbxHumidity;
    private JCheckBox chckbxAllDates;
    private JCheckBox chckbxMoisture;
    private JCheckBox chckbxTemperature;
    
    public DateInputFromTo(MainPage mP) {
        getTime(mP);
    }
    
    public void getTime(MainPage mP) {
        JFrame frame = new JFrame();
        
        DateTimePicker dateTimePickerFrom;
        DateTimePicker dateTimePickerTo;
        DatePickerSettings dateSettingsFrom = new DatePickerSettings();
        TimePickerSettings timeSettingsFrom = new TimePickerSettings();
        DatePickerSettings dateSettingsTo = new DatePickerSettings();
        TimePickerSettings timeSettingsTo = new TimePickerSettings();
        
        dateSettingsFrom.setAllowEmptyDates(false);
        dateSettingsFrom.setAllowKeyboardEditing(false);
        
        dateSettingsTo.setAllowEmptyDates(false);
        dateSettingsTo.setAllowKeyboardEditing(false);
        
        timeSettingsFrom.setAllowEmptyTimes(false);
        timeSettingsFrom.use24HourClockFormat();
        timeSettingsFrom.initialTime = LocalTime.NOON;
        timeSettingsFrom.generatePotentialMenuTimes(TimeIncrement.OneHour, null, null);
        timeSettingsFrom.setAllowKeyboardEditing(false);
        
        timeSettingsTo.setAllowEmptyTimes(false);
        timeSettingsTo.use24HourClockFormat();
        timeSettingsTo.initialTime = LocalTime.NOON;
        timeSettingsTo.generatePotentialMenuTimes(TimeIncrement.OneHour, null, null);
        timeSettingsTo.setAllowKeyboardEditing(false);
        
        dateTimePickerFrom = new DateTimePicker(dateSettingsFrom, timeSettingsFrom);
        dateTimePickerFrom.setBounds(10, 36, 514, 25);
        
        dateTimePickerTo = new DateTimePicker(dateSettingsTo, timeSettingsTo);
        dateTimePickerTo.setBounds(10, 97, 514, 25);
        
        JButton btnSave = new JButton("save");
        btnSave.setBounds(403, 225, 121, 25);
        btnSave.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                LocalDateTime ldtFrom = dateTimePickerFrom.getDateTimeStrict();
                LocalDateTime ldtTo = dateTimePickerTo.getDateTimeStrict();
                
                Date dateFrom = Date.from(ldtFrom.atZone(ZoneId.systemDefault()).toInstant());
                Date dateTo = Date.from(ldtTo.atZone(ZoneId.systemDefault()).toInstant());
                
                CustomChartControl CCC = CustomChartControl.getInstance();
                CCC.setStartDate(dateFrom);
                CCC.setEndDate(dateTo);
                CCC.setAllDates(chckbxAllDates.isSelected());
                CCC.setTempChart(chckbxTemperature.isSelected());
                CCC.setHumChart(chckbxHumidity.isSelected());
                CCC.setWaterChart(chckbxMoisture.isSelected());
                CCC.setIsConfigured(true);
                new Notification("Custom chart data was registered successfull");
                mP.getMntmCreateCustomChart().setEnabled(true);
                mP.setEnabled(true);
                frame.dispose();
                
            }
        });
        
        new LayoutHelper(frame, "Configure your custom chart", btnSave);
        frame.getContentPane().setLayout(null);
        frame.setSize(550, 300);
        frame.getContentPane().add(dateTimePickerFrom);
        frame.getContentPane().add(dateTimePickerTo);
        frame.getContentPane().add(btnSave);
        
        JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                mP.setEnabled(true);
                frame.dispose();
            }
        });
        btnBack.setBounds(272, 225, 121, 25);
        frame.getContentPane().add(btnBack);
        
        JLabel lblSelectADate = new JLabel("Select a date from:");
        lblSelectADate.setBounds(10, 11, 180, 14);
        frame.getContentPane().add(lblSelectADate);
        
        JLabel lblTo = new JLabel("To:");
        lblTo.setBounds(10, 72, 180, 14);
        frame.getContentPane().add(lblTo);
        
        JLabel lblOr = new JLabel("Or:");
        lblOr.setBounds(272, 133, 25, 14);
        frame.getContentPane().add(lblOr);
        
        chckbxMoisture = new JCheckBox("Moisture");
        chckbxMoisture.setSelected(CustomChartControl.getInstance().getWaterChart());
        chckbxMoisture.setBounds(10, 226, 180, 23);
        frame.getContentPane().add(chckbxMoisture);
        
        chckbxAllDates = new JCheckBox("Select all dates");
        chckbxAllDates.setBounds(303, 129, 221, 23);
        frame.getContentPane().add(chckbxAllDates);
        
        chckbxHumidity = new JCheckBox("Humidity");
        chckbxHumidity.setSelected(CustomChartControl.getInstance().getHumChart());
        chckbxHumidity.setBounds(10, 200, 180, 23);
        frame.getContentPane().add(chckbxHumidity);
        
        chckbxTemperature = new JCheckBox("Temperature");
        chckbxTemperature.setSelected(CustomChartControl.getInstance().getTempChart());
        chckbxTemperature.setBounds(10, 174, 180, 23);
        frame.getContentPane().add(chckbxTemperature);
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
