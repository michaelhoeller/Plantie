package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import control.CenterControl;
import control.DateControl;
import core.FileStringReader;
import gui.generation.HighChartGenerator;
import utils.DataGenerator;
import utils.StringSplitter;
import utils.gui.LayoutHelper;
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
    private JMenuItem         mntmLoadData;
    private JMenuItem         mntmPrintErrorLog;
    private JMenuItem         mntmZoneValues;
    
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
                    if (DateControl.getInstance().isSet() == false) {
                        new Notification("Please register a start date first\nand load the data.txt");
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
            mntmStart.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    HashMap<Integer, Boolean> test = new HashMap<>();
                    test.put(1, true);
                    test.put(2, true);
                    test.put(3, true);
                    test.put(4, true);
                    try {
                        new HighChartGenerator().generateHtmlChart(test, null, null);
                    }
                    catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
        }
        return mntmStart;
    }
    
    private JMenuItem getMntmMan() {
        if (mntmMan == null) {
            mntmMan = new JMenuItem("Manuell");
            mntmMan.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    HashMap<Integer, Boolean> test = new HashMap<>();
                    test.put(1, true);
                    test.put(2, true);
                    test.put(3, true);
                    test.put(4, true);
                    try {
                        new HighChartGenerator().generateHtmlChart(test, null, null);
                    }
                    catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
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
            mnGlobalConfiguration = new JMenu("Control Center");
            mnGlobalConfiguration.setMnemonic(KeyEvent.VK_C);
            mnGlobalConfiguration.add(getMntmSetStartDate());
            mnGlobalConfiguration.add(getMntmLoadData());
            mnGlobalConfiguration.add(getMntmZoneValues());
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
            mnNewMenu.add(getMntmPrintErrorLog());
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
    
    JMenuItem getMntmLoadData() {
        if (mntmLoadData == null) {
            mntmLoadData = new JMenuItem("Load data");
            mntmLoadData.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    try {
                        new StringSplitter(new FileStringReader().getDataString());
                        getMnMode().setEnabled(true);
                    }
                    catch (Exception e2) {
                        new Notification("Something went wrong!\nIs the data.txt next to the JAR?");
                    }
                }
            });
            mntmLoadData.addMouseListener(new MouseAdapter() {
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (DateControl.getInstance().isSet() == false) {
                        new Notification("Please register a start date first");
                    }
                }
            });
            mntmLoadData.setEnabled(false);
        }
        return mntmLoadData;
    }
    
    private JMenuItem getMntmPrintErrorLog() {
        if (mntmPrintErrorLog == null) {
            mntmPrintErrorLog = new JMenuItem("Print error log");
            mntmPrintErrorLog.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    File errorLog = new File("error.log");
                    PrintWriter out = null;
                    try {
                        out = new PrintWriter(errorLog);
                    }
                    catch (FileNotFoundException e1) {
                        new Notification("Error during error log creation");
                    }
                    out.print(CenterControl.getInstance().getErrorString());
                    out.close();
                }
            });
        }
        return mntmPrintErrorLog;
    }
    
    private JMenuItem getMntmZoneValues() {
        if (mntmZoneValues == null) {
            mntmZoneValues = new JMenuItem("Zone values");
            mntmZoneValues.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    mainPage.setEnabled(false);
                    new ZoneControlConfigurator(mainPage);
                }
            });
        }
        return mntmZoneValues;
    }
}
