package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import control.CenterControl;
import control.CustomChartControl;
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
    private JMenuItem         mntmCreateCustomChart;
    private JMenuItem         mntmQuickChart;
    private JMenu             mnCustomChart;
    private JMenuItem         mntmConfiguration;
    private JMenu             mnGlobalConfiguration;
    private JMenuItem         mntmSetStartDate;
    private JLabel            lblDateLabel;
    private JMenu             mnNewMenu;
    private JMenuItem         mntmGenerateTestData;
    private JMenuItem         mntmLoadData;
    private JMenuItem         mntmPrintErrorLog;
    private JMenuItem         mntmZoneValues;
    private JMenuItem         mntmStartAutoMode;
    
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
            mnMode = new JMenu("Chart creation");
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
            mnMode.add(getMntmQuickChart());
            mnMode.add(getMnCustomChart());
            mnMode.add(getMntmStartAutoMode());
        }
        return mnMode;
    }
    
    JMenuItem getMntmCreateCustomChart() {
        if (mntmCreateCustomChart == null) {
            mntmCreateCustomChart = new JMenuItem("Create chart");
            mntmCreateCustomChart.setEnabled(false);
            mntmCreateCustomChart.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    CustomChartControl CCC = CustomChartControl.getInstance();
                    try {
                        new HighChartGenerator().generateHtmlChart(CCC.getChartMap(), CCC.getStartDate(), CCC.getEndDate());
                    }
                    catch (Exception e1) {
                        new Notification("Error during HighChart generation\nCreateCustomChart()");
                    }
                }
            });
        }
        return mntmCreateCustomChart;
    }
    
    private JMenuItem getMntmQuickChart() {
        if (mntmQuickChart == null) {
            mntmQuickChart = new JMenuItem("Quick chart");
            mntmQuickChart.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    try {
                        new HighChartGenerator().generateHtmlChart(CustomChartControl.getInstance().getChartMapTrue(), null, null);
                    }
                    catch (Exception e1) {
                        new Notification("Error during HighChart generation\nQuickChart");
                    }
                }
            });
        }
        return mntmQuickChart;
    }
    
    private JMenu getMnCustomChart() {
        if (mnCustomChart == null) {
            mnCustomChart = new JMenu("CustomChart");
            mnCustomChart.add(getMntmCreateCustomChart());
            mnCustomChart.add(getMntmConfiguration());
        }
        return mnCustomChart;
    }
    
    private JMenuItem getMntmConfiguration() {
        if (mntmConfiguration == null) {
            mntmConfiguration = new JMenuItem("Configuration");
            mntmConfiguration.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    mainPage.setEnabled(false);
                    new DateInputFromTo(mainPage);
                }
            });
        }
        return mntmConfiguration;
    }
    
    private JMenu getMnGlobalConfiguration() {
        if (mnGlobalConfiguration == null) {
            mnGlobalConfiguration = new JMenu("Control Center");
            mnGlobalConfiguration.setMnemonic(KeyEvent.VK_C);
            mnGlobalConfiguration.add(getMntmZoneValues());
            mnGlobalConfiguration.add(getMntmSetStartDate());
            mnGlobalConfiguration.add(getMntmLoadData());
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
            lblDateLabel = new JLabel("No start date set - Go to global configuration");
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
    
    private JMenuItem getMntmStartAutoMode() {
        if (mntmStartAutoMode == null) {
            mntmStartAutoMode = new JMenuItem("Start auto mode");
        }
        return mntmStartAutoMode;
    }
}
