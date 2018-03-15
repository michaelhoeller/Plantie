package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import controll.DateControll;
import gui.helper.LayoutHelper;
import utils.DataGenerator;
import utils.messenger.IntegerInput;
import utils.messenger.Notification;

public class MainPage extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private MainPage          mainPage;
    private JMenuBar          mBar;
    private JMenu             mnMode;
    private JMenuItem         mntmStart;
    private JMenuItem         mntmMan;
    private JMenu             mnAuto;
    private JMenuItem         mntmConfiguration;
    private JMenu             mnGlobalConfiguration;
    private JMenuItem         mntmSetStartDate;
    private JLabel            lblDateLabel;
    private JMenu             mnNewMenu;
    private JMenuItem         mntmGenerateTestData;
    
    public MainPage() {
        init();
        setMainPage(this);
        new LayoutHelper(this, "Plantie", null);
        getContentPane().setLayout(null);
        getContentPane().add(getLblDateLabel());
        this.setVisible(true);
    }
    
    private void init() {
        setJMenuBar(getMBar());
    }
    
    public MainPage getMainPage() {
        return mainPage;
    }
    
    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }
    
    private JMenuBar getMBar() {
        if (mBar == null) {
            mBar = new JMenuBar();
            mBar.add(getMnMode());
            mBar.add(getMnGlobalConfiguration());
            mBar.add(getMnNewMenu());
        }
        return mBar;
    }
    
    JMenu getMnMode() {
        if (mnMode == null) {
            mnMode = new JMenu("Mode");
            mnMode.setMnemonic(KeyEvent.VK_M);
            mnMode.setEnabled(false);
            mnMode.addMouseListener(new MouseAdapter() {
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (DateControll.getInstance().isSet() == false) {
                        new Notification("Please register a start date first");
                    }
                }
            });
            mnMode.add(getMnAuto());
            mnMode.add(getMntmMan());
        }
        return mnMode;
    }
    
    private JMenuItem getMntmStart() {
        if (mntmStart == null) {
            mntmStart = new JMenuItem("Start");
            mntmStart.setEnabled(false);
        }
        return mntmStart;
    }
    
    private JMenuItem getMntmMan() {
        if (mntmMan == null) {
            mntmMan = new JMenuItem("Manuell");
        }
        return mntmMan;
    }
    
    private JMenu getMnAuto() {
        if (mnAuto == null) {
            mnAuto = new JMenu("Atutomatic");
            mnAuto.add(getMntmStart());
            mnAuto.add(getMntmConfiguration());
        }
        return mnAuto;
    }
    
    private JMenuItem getMntmConfiguration() {
        if (mntmConfiguration == null) {
            mntmConfiguration = new JMenuItem("Configuration");
        }
        return mntmConfiguration;
    }
    
    private JMenu getMnGlobalConfiguration() {
        if (mnGlobalConfiguration == null) {
            mnGlobalConfiguration = new JMenu("Global configuration");
            mnGlobalConfiguration.setMnemonic(KeyEvent.VK_C);
            mnGlobalConfiguration.add(getMntmSetStartDate());
        }
        return mnGlobalConfiguration;
    }
    
    private JMenuItem getMntmSetStartDate() {
        if (mntmSetStartDate == null) {
            mntmSetStartDate = new JMenuItem("Set start date");
            mntmSetStartDate.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    mainPage.setEnabled(false);
                    new DateInput(getMainPage());
                }
            });
        }
        return mntmSetStartDate;
    }
    
    JLabel getLblDateLabel() {
        if (lblDateLabel == null) {
            lblDateLabel = new JLabel("No date set - Go to global configuration");
            lblDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            lblDateLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
            lblDateLabel.setBounds(10, 206, 414, 23);
        }
        return lblDateLabel;
    }
    
    private JMenu getMnNewMenu() {
        if (mnNewMenu == null) {
            mnNewMenu = new JMenu("Utilities");
            mnNewMenu.setBorderPainted(true);
            mnNewMenu.setHorizontalTextPosition(SwingConstants.RIGHT);
            mnNewMenu.setHorizontalAlignment(SwingConstants.RIGHT);
            mnNewMenu.setMnemonic(KeyEvent.VK_U);
            mnNewMenu.add(getMntmGenerateTestData());
        }
        return mnNewMenu;
    }
    
    private JMenuItem getMntmGenerateTestData() {
        if (mntmGenerateTestData == null) {
            mntmGenerateTestData = new JMenuItem("Generate test data");
            mntmGenerateTestData.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    try {
                        DataGenerator.generateData(new IntegerInput("Please specify the amount of datasets").getOutcome());
                    }
                    catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return mntmGenerateTestData;
    }
}
