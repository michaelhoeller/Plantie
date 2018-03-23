package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.CenterControl;
import utils.gui.LayoutHelper;
import utils.messenger.SuperNotification;

public class AutoModeConfigurator {
    
    private MainPage   mP;
    
    private JFrame     frame;
    private JButton    btnSave;
    private JButton    btnBack;
    private JLabel     lblNothingToConfigure;
    private JTextField textSleepInterval;
    
    public AutoModeConfigurator(MainPage mainPage) {
        mP = mainPage;
        getInputs();
    }
    
    private void getInputs() {
        frame = new JFrame();
        new LayoutHelper(frame, "Configure zone control values", null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(getBtnSave());
        frame.getContentPane().add(getBtnBack());
        frame.getContentPane().add(getLblNothingToConfigure());
        frame.getContentPane().add(getTextSleepInterval());
        frame.setVisible(true);
    }
    
    private JButton getBtnSave() {
        if (btnSave == null) {
            btnSave = new JButton("save");
            btnSave.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    CenterControl CC = CenterControl.getInstance();
                    try {
                        CC.setAutoModeInterval(Integer.parseInt(textSleepInterval.getText()));
                        mP.setEnabled(true);
                        frame.dispose();
                    }
                    catch (Exception e1) {
                        new SuperNotification("Only whole Numbers you dumb FUCK!");
                    }
                }
            });
            btnSave.setBounds(335, 231, 89, 23);
        }
        return btnSave;
    }
    
    private JButton getBtnBack() {
        if (btnBack == null) {
            btnBack = new JButton("back");
            btnBack.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    mP.setEnabled(true);
                    frame.dispose();
                }
            });
            btnBack.setBounds(236, 231, 89, 23);
        }
        return btnBack;
    }
    
    private JLabel getLblNothingToConfigure() {
        if (lblNothingToConfigure == null) {
            lblNothingToConfigure = new JLabel("Thread sleep interval (s)");
            lblNothingToConfigure.setBounds(10, 11, 130, 23);
        }
        return lblNothingToConfigure;
    }
    
    private JTextField getTextSleepInterval() {
        if (textSleepInterval == null) {
            textSleepInterval = new JTextField();
            textSleepInterval.setBounds(150, 12, 89, 20);
            textSleepInterval.setColumns(10);
        }
        textSleepInterval.setText(CenterControl.getInstance().getAutoModeInterval().toString());
        return textSleepInterval;
    }
}
