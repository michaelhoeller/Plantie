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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import control.CenterControl;
import control.CustomChartControl;
import control.DateControl;
import control.FTPControl;
import core.AutoModeThread;
import core.FileStringReader;
import gui.generation.HighChartGenerator;
import utils.Constants;
import utils.DataGenerator;
import utils.DateConverter;
import utils.StringSplitter;
import utils.gui.EnableHelper;
import utils.gui.LayoutHelper;
import utils.messenger.IntegerInput;
import utils.messenger.LogError;
import utils.messenger.Notification;
import utils.messenger.SuperNotification;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainPage mainPage;
	private JMenuBar mBar;
	private JMenu mnChartCreation;
	private JMenuItem mntmCreateCustomChart;
	private JMenuItem mntmQuickChart;
	private JMenu mnCustomChart;
	private JMenuItem mntmConfiguration;
	private JMenu mnControlCenter;
	private JMenuItem mntmSetStartDate;
	private JLabel lblDateLabel;
	private JMenu mnUtilities;
	private JMenuItem mntmGenerateTestData;
	private JMenuItem mntmLoadData;
	private JMenuItem mntmPrintErrorLog;
	private JMenuItem mntmZoneValues;
	private JMenuItem mntmStartAutoMode;
	private JLabel lblNoValuesLoaded;
	private JLabel lblCurrentStatus;
	private JSeparator separator;
	private JButton btnExit;
	private JLabel lblValueHeading;
	private JLabel lblDateHeading;
	private JPanel panel;
	private JLabel lblModeDisplay;
	private JButton btnStopAuto;
	private boolean menuEnabled = true;
	private JLabel lblNotifications;
	private JPanel panelNotifications;
	private JTextPane textPane;
	private JCheckBox chckbxEnableNotifications;
	private AutoModeThread AMT;
	private JButton btnQuickload;

	public static MainPage getMainPage() {
		return mainPage;
	}

	public MainPage() {
		init();
		setMainPage(this);
		btnQuickload = new JButton("QuickLoad");
		new LayoutHelper(this, "Plantie", btnQuickload);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getLblCurrentStatus());
		getContentPane().add(getSeparator());
		getContentPane().add(getLblModeDisplay());
		getContentPane().add(getBtnStopAuto());
		getContentPane().add(getLblNotifications());
		getContentPane().add(getPanelNotifications());

		chckbxEnableNotifications = new JCheckBox("Popups?");
		chckbxEnableNotifications.setBounds(10, 57, 100, 23);
		getContentPane().add(chckbxEnableNotifications);
		chckbxEnableNotifications.setSelected(CenterControl.getInstance().isNotificationsWanted());
		chckbxEnableNotifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxEnableNotifications.isSelected()) {
					CenterControl.getInstance().setNotificationsWanted(true);
				} else {
					CenterControl.getInstance().setNotificationsWanted(false);
				}
			}
		});
		this.setVisible(true);
	}

	private void init() {
		setJMenuBar(getMBar());
	}

	public void setMainPage(MainPage mainPage) {
		MainPage.mainPage = mainPage;
	}

	private JMenuBar getMBar() {
		if (mBar == null) {
			mBar = new JMenuBar();
			mBar.add(getMnChartCreation());
			mBar.add(getMnControlCenter());
			mBar.add(getMnUtilities());
		}
		return mBar;
	}

	JMenu getMnChartCreation() {
		if (mnChartCreation == null) {
			mnChartCreation = new JMenu("Chart creation");
			mnChartCreation.setMnemonic(KeyEvent.VK_M);
			mnChartCreation.setEnabled(false);
			mnChartCreation.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if (DateControl.getInstance().isSet() == false) {
						new Notification("Please register a start date first\nand load the data.txt");

					}
				}
			});
			mnChartCreation.add(getMntmQuickChart());
			mnChartCreation.add(getMnCustomChart());

			JMenuItem mntmUploadChart = new JMenuItem("Upload chart");
			mntmUploadChart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CenterControl CC = CenterControl.getInstance();
					if (Constants.UPLOAD_ENABLED) {
						if (!(CC.getCurrentChartString() == null)) {
							try {
								FTPControl.getInstance().uploadChart(CC.getCurrentChartString(), mainPage);
							} catch (IOException e1) {
								new Notification("Error during file upload");

							}
						}
					} else {
						new SuperNotification("Your account is not entitled to upload");
					}
				}
			});
			mnChartCreation.add(mntmUploadChart);
			mnChartCreation.add(getMntmStartAutoMode());
		}
		return mnChartCreation;
	}

	JMenuItem getMntmCreateCustomChart() {
		if (mntmCreateCustomChart == null) {
			mntmCreateCustomChart = new JMenuItem("Create chart");
			mntmCreateCustomChart.setEnabled(false);
			mntmCreateCustomChart.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					CustomChartControl CCC = CustomChartControl.getInstance();
					try {
						new HighChartGenerator().generateHtmlChart(CCC.getChartMap(), CCC.getStartDate(),
								CCC.getEndDate());

					} catch (Exception e1) {
						new Notification("Error during HighChart generation\nCreateCustomChart");

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
						new HighChartGenerator().generateHtmlChart(CustomChartControl.getInstance().getChartMapTrue(),
								null, null);

					} catch (Exception e1) {

						new Notification("Error during HighChart generation\nQuickChart");

						// HelpMethode
						File f = new File("StackTrace.txt");
						PrintWriter out = null;
						try {
							out = new PrintWriter(f);
						} catch (FileNotFoundException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						out.print("Printing stack trace:\n------------------------------------\n");
						StackTraceElement[] elements = e1.getStackTrace();
						for (int i = 1; i < elements.length; i++) {
							StackTraceElement s = elements[i];
							out.print("at " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":"
									+ s.getLineNumber() + ")\n");
						}
						out.close();
						// HelpMethode
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

	private JMenu getMnControlCenter() {
		if (mnControlCenter == null) {
			mnControlCenter = new JMenu("Control Center");
			mnControlCenter.setMnemonic(KeyEvent.VK_C);
			mnControlCenter.add(getMntmZoneValues());
			mnControlCenter.add(getMntmSetStartDate());
			mnControlCenter.add(getMntmLoadData());
		}
		return mnControlCenter;
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
			lblDateLabel = new JLabel("Go to global configuration");
			lblDateLabel.setBounds(234, 34, 180, 23);
			lblDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDateLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		return lblDateLabel;
	}

	private JMenu getMnUtilities() {
		if (mnUtilities == null) {
			mnUtilities = new JMenu("Utilities");
			mnUtilities.setBorderPainted(true);
			mnUtilities.setHorizontalTextPosition(SwingConstants.RIGHT);
			mnUtilities.setHorizontalAlignment(SwingConstants.RIGHT);
			mnUtilities.setMnemonic(KeyEvent.VK_U);
			mnUtilities.add(getMntmGenerateTestData());
			mnUtilities.add(getMntmPrintErrorLog());
		}
		return mnUtilities;
	}

	private JMenuItem getMntmGenerateTestData() {
		if (mntmGenerateTestData == null) {
			mntmGenerateTestData = new JMenuItem("Generate test data");
			mntmGenerateTestData.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						DataGenerator
								.generateData(new IntegerInput("Please specify the amount of datasets").getOutcome());

					} catch (Exception e1) {
						new LogError("Cancel button data generation");

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
						StringSplitter SS = new StringSplitter(new FileStringReader().getDataString());
						SS.measurementGeneration();
						lblValueHeading.setText("Amount of loaded values:");
						lblNoValuesLoaded.setText(CenterControl.getInstance().getDataSets().toString());
						getMnChartCreation().setEnabled(true);

					} catch (Exception e2) {
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
					printLog();
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
			mntmStartAutoMode.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					toggleMenu();
					AMT = new AutoModeThread();
					AMT.ControlSubThread(100);
					AMT.start();
				}
			});
		}
		return mntmStartAutoMode;

	}

	private JButton getBtnStopAuto() {
		if (btnStopAuto == null) {
			btnStopAuto = new JButton("Stop auto");
			btnStopAuto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AMT.stop();
					new SuperNotification("Waiting for thread to finish!\nThis can take some time");
					mainPage.setTitle("Plantie - waiting for end of thread");
					while (!AMT.getFinished().get()) {
						mainPage.setEnabled(false);
					}
					mainPage.setEnabled(true);
					mainPage.setTitle("Plantie");
					new Notification("Thread finished");
					toggleMenu();
				}
			});
			btnStopAuto.setEnabled(false);
			btnStopAuto.setBounds(324, 7, 100, 23);
		}
		return btnStopAuto;
	}

	// Disable the menu
	private void toggleMenu() {
		if (menuEnabled) {
			mnChartCreation.setEnabled(false);
			mnControlCenter.setEnabled(false);
			mnUtilities.setEnabled(false);
			EnableHelper.setEnabledAll(panel, false);
			btnStopAuto.setEnabled(true);
			menuEnabled = false;
			chckbxEnableNotifications.setEnabled(false);
			chckbxEnableNotifications.setSelected(false);
			CenterControl.getInstance().setNotificationsWanted(false);
			CenterControl.getInstance().setOperationMode("auto");
			lblModeDisplay.setText(CenterControl.getInstance().getOperationMode());

		} else {
			mnChartCreation.setEnabled(true);
			mnControlCenter.setEnabled(true);
			mnUtilities.setEnabled(true);
			EnableHelper.setEnabledAll(panel, true);
			btnStopAuto.setEnabled(false);
			menuEnabled = true;
			chckbxEnableNotifications.setEnabled(true);
			CenterControl.getInstance().setOperationMode("manual");
			lblModeDisplay.setText(CenterControl.getInstance().getOperationMode());

		}
	}

	private JLabel getLblCurrentStatus() {
		if (lblCurrentStatus == null) {
			lblCurrentStatus = new JLabel("Current status:");
			lblCurrentStatus.setBounds(10, 11, 100, 14);
		}
		return lblCurrentStatus;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(10, 159, 414, 2);
		}
		return separator;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.setBounds(0, 34, 100, 23);
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					printLog();
					System.exit(0);
				}
			});
		}
		return btnExit;
	}

	private JLabel getLblValueHeading() {
		if (lblValueHeading == null) {
			lblValueHeading = new JLabel("No values loaded - ");
			lblValueHeading.setBounds(110, 4, 180, 14);
			lblValueHeading.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblValueHeading;
	}

	JLabel getLblDateHeading() {
		if (lblDateHeading == null) {
			lblDateHeading = new JLabel("No start date set - ");
			lblDateHeading.setBounds(110, 38, 180, 14);
			lblDateHeading.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblDateHeading;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 172, 414, 57);
			panel.setLayout(null);
			panel.add(getLblDateLabel());

			lblNoValuesLoaded = new JLabel("Go to global configuration");
			lblNoValuesLoaded.setBounds(234, 0, 180, 23);
			panel.add(lblNoValuesLoaded);
			lblNoValuesLoaded.setHorizontalTextPosition(SwingConstants.RIGHT);
			lblNoValuesLoaded.setHorizontalAlignment(SwingConstants.RIGHT);

			btnQuickload.setBounds(0, 0, 100, 23);
			panel.add(btnQuickload);
			panel.add(getBtnExit());
			panel.add(getLblValueHeading());
			panel.add(getLblDateHeading());
			btnQuickload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date retDate = new Date();
					if (getLblDateLabel() != null) {
						getLblDateHeading().setText("Date of first measurement:");
						getLblDateLabel().setText(DateConverter.conv(retDate).toString());
					}
					getMntmLoadData().setEnabled(true);
					DateControl.getInstance().setStartDate(retDate);
					try {
						try {
							DataGenerator.generateData(500);
						} catch (Exception e1) {
							new LogError("Cancel button data generation");
						}
						StringSplitter SS = new StringSplitter(new FileStringReader().getDataString());
						SS.measurementGeneration();

						lblValueHeading.setText("Loaded values:");
						lblNoValuesLoaded.setText(CenterControl.getInstance().getDataSets().toString());
						getMnChartCreation().setEnabled(true);

					} catch (Exception e2) {
						new Notification("Something went wrong!\nIs the data.txt next to the JAR?");

					}
				}
			});
		}
		return panel;
	}

	private JLabel getLblModeDisplay() {
		if (lblModeDisplay == null) {
			lblModeDisplay = new JLabel();
			lblModeDisplay.setText(CenterControl.getInstance().getOperationMode());
			lblModeDisplay.setBounds(120, 11, 46, 14);
		}
		return lblModeDisplay;
	}

	private JLabel getLblNotifications() {
		if (lblNotifications == null) {
			lblNotifications = new JLabel("Notifications:");
			lblNotifications.setBounds(10, 36, 100, 14);
		}
		return lblNotifications;
	}

	private JPanel getPanelNotifications() {
		if (panelNotifications == null) {
			panelNotifications = new JPanel();
			panelNotifications.setBounds(120, 36, 304, 112);
			panelNotifications.setLayout(null);

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_1.setBounds(0, 0, 304, 112);
			panelNotifications.add(scrollPane_1);

			textPane = new JTextPane();
			textPane.setEditable(false);
			scrollPane_1.setViewportView(textPane);
		}
		return panelNotifications;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	private void printLog() {
		File errorLog = new File("error.log");
		PrintWriter out = null;
		try {
			out = new PrintWriter(errorLog);

		} catch (FileNotFoundException e1) {
			new Notification("Error during error log creation");

		}
		out.print(CenterControl.getInstance().getErrorString());
		out.close();
	}
}
