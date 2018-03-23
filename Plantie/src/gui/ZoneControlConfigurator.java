package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import control.CenterControl;
import control.ZoneControl;
import utils.gui.EnableHelper;
import utils.gui.LayoutHelper;
import utils.messenger.Notification;

public class ZoneControlConfigurator {

	private MainPage mP;

	private JFrame frame;
	private JPanel panel;
	private JCheckBox chckbxZoneControlActive;
	private JButton btnSave;
	private JButton btnBack;
	private JLabel lblTemperature1;
	private JLabel lblTemperature2;
	private JTextField textTempBad;
	private JTextField textTempGood;
	private JSeparator separator;
	private JLabel lblHumidity;
	private JLabel lblHumidity_1;
	private JSeparator separator_1;
	private JLabel lblTemperature;
	private JLabel lblTemperature_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JTextField textHumBad;
	private JTextField textHumGood;
	private JTextField textWaterBad;
	private JTextField textWaterGood;
	private JLabel lblIsRed;
	private JLabel lblIsBlue;
	private JLabel lblIsBlue_1;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel lblNewLabel;

	public ZoneControlConfigurator(MainPage mainPage) {
		mP = mainPage;
		getInputs();
	}

	private void getInputs() {
		frame = new JFrame();
		new LayoutHelper(frame, "Configure zone control values", null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getChckbxZoneControlActive());
		frame.getContentPane().add(getPanel());
		frame.getContentPane().add(getBtnSave());
		frame.getContentPane().add(getBtnBack());
		frame.getContentPane().add(getLblNewLabel());
		frame.setVisible(true);
	}

	private JCheckBox getChckbxZoneControlActive() {
		if (chckbxZoneControlActive == null) {
			chckbxZoneControlActive = new JCheckBox("Zone control active");
			chckbxZoneControlActive.setSelected(CenterControl.getInstance().isZoneControl());
			chckbxZoneControlActive.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					pnlSetState();
					if (chckbxZoneControlActive.isSelected()) {
						CenterControl.getInstance().setZoneControl(true);
					} else {
						CenterControl.getInstance().setZoneControl(false);
					}
				}
			});
			chckbxZoneControlActive.setBounds(6, 231, 224, 23);
		}
		return chckbxZoneControlActive;
	}

	private void pnlSetState() {
		if (chckbxZoneControlActive.isSelected()) {
			EnableHelper.setEnabledAll(panel, true);
		} else {
			EnableHelper.setEnabledAll(panel, false);
		}
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 11, 414, 188);
			panel.setLayout(null);
			panel.add(getLblTemperature1());
			panel.add(getLblTemperature2());
			panel.add(getTextTempBad());
			panel.add(getTextTempGood());
			panel.add(getSeparator());
			panel.add(getLblHumidity());
			panel.add(getLblHumidity_1());
			panel.add(getSeparator_1());
			panel.add(getLblTemperature());
			panel.add(getLblTemperature_1());
			panel.add(getLabel());
			panel.add(getLabel_1());
			panel.add(getLabel_4());
			panel.add(getLabel_5());
			panel.add(getLabel_6());
			panel.add(getLabel_7());
			panel.add(getTextHumBad());
			panel.add(getTextHumGood());
			panel.add(getTextWaterBad());
			panel.add(getTextWaterGood());
			panel.add(getLblIsRed());
			panel.add(getLblIsBlue());
			panel.add(getLblIsBlue_1());
			panel.add(getLabel_8());
			panel.add(getLabel_9());
			panel.add(getLabel_10());
			pnlSetState();
		}
		return panel;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("save");
			btnSave.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					ZoneControl zC = ZoneControl.getInstance();

					try {
						zC.setTempBad(Integer.parseInt(textTempBad.getText()));
						zC.setTempGood(Integer.parseInt(textTempGood.getText()));
						zC.setHumBad(Integer.parseInt(textHumBad.getText()));
						zC.setHumGood(Integer.parseInt(textHumGood.getText()));
						zC.setWaterBad(Integer.parseInt(textWaterBad.getText()));
						zC.setWaterGood(Integer.parseInt(textWaterGood.getText()));
						zC.setIsCostumized(true);
						new Notification("Zone control values saved");
						mP.setEnabled(true);
						frame.dispose();
					} catch (Exception e2) {
						new Notification("Please insert whole numbers only!");
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

	private JLabel getLblTemperature1() {
		if (lblTemperature1 == null) {
			lblTemperature1 = new JLabel("Temperature");
			lblTemperature1.setBounds(10, 11, 100, 14);
		}
		return lblTemperature1;
	}

	private JLabel getLblTemperature2() {
		if (lblTemperature2 == null) {
			lblTemperature2 = new JLabel("Temperature");
			lblTemperature2.setBounds(10, 36, 100, 14);
		}
		return lblTemperature2;
	}

	private JTextField getTextTempBad() {
		if (textTempBad == null) {
			textTempBad = new JTextField();
			textTempBad.setBounds(144, 8, 86, 20);
			textTempBad.setColumns(10);
			textTempBad.setText(ZoneControl.getInstance().getTempBad().toString());
		}
		return textTempBad;
	}

	private JTextField getTextTempGood() {
		if (textTempGood == null) {
			textTempGood = new JTextField();
			textTempGood.setBounds(144, 33, 86, 20);
			textTempGood.setColumns(10);
			textTempGood.setText(ZoneControl.getInstance().getTempGood().toString());
		}
		return textTempGood;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(10, 61, 394, 2);
		}
		return separator;
	}

	private JLabel getLblHumidity() {
		if (lblHumidity == null) {
			lblHumidity = new JLabel("Humidity");
			lblHumidity.setBounds(10, 74, 100, 14);
		}
		return lblHumidity;
	}

	private JLabel getLblHumidity_1() {
		if (lblHumidity_1 == null) {
			lblHumidity_1 = new JLabel("Humidity");
			lblHumidity_1.setBounds(10, 99, 100, 14);
		}
		return lblHumidity_1;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBounds(10, 124, 394, 2);
		}
		return separator_1;
	}

	private JLabel getLblTemperature() {
		if (lblTemperature == null) {
			lblTemperature = new JLabel("Temperature");
			lblTemperature.setBounds(10, 137, 100, 14);
		}
		return lblTemperature;
	}

	private JLabel getLblTemperature_1() {
		if (lblTemperature_1 == null) {
			lblTemperature_1 = new JLabel("Temperature");
			lblTemperature_1.setBounds(10, 162, 100, 14);
		}
		return lblTemperature_1;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("<");
			label.setBounds(120, 11, 14, 14);
		}
		return label;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("<");
			label_1.setBounds(120, 74, 14, 14);
		}
		return label_1;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("<");
			label_4.setBounds(120, 137, 14, 14);
		}
		return label_4;
	}

	private JLabel getLabel_5() {
		if (label_5 == null) {
			label_5 = new JLabel(">");
			label_5.setBounds(120, 36, 14, 14);
		}
		return label_5;
	}

	private JLabel getLabel_6() {
		if (label_6 == null) {
			label_6 = new JLabel(">");
			label_6.setBounds(120, 99, 14, 14);
		}
		return label_6;
	}

	private JLabel getLabel_7() {
		if (label_7 == null) {
			label_7 = new JLabel(">");
			label_7.setBounds(120, 162, 14, 14);
		}
		return label_7;
	}

	private JTextField getTextHumBad() {
		if (textHumBad == null) {
			textHumBad = new JTextField();
			textHumBad.setBounds(144, 71, 86, 20);
			textHumBad.setColumns(10);
			textHumBad.setText(ZoneControl.getInstance().getHumBad().toString());
		}
		return textHumBad;
	}

	private JTextField getTextHumGood() {
		if (textHumGood == null) {
			textHumGood = new JTextField();
			textHumGood.setBounds(144, 96, 86, 20);
			textHumGood.setColumns(10);
			textHumGood.setText(ZoneControl.getInstance().getHumGood().toString());
		}
		return textHumGood;
	}

	private JTextField getTextWaterBad() {
		if (textWaterBad == null) {
			textWaterBad = new JTextField();
			textWaterBad.setBounds(144, 134, 86, 20);
			textWaterBad.setColumns(10);
			textWaterBad.setText(ZoneControl.getInstance().getWaterBad().toString());
		}
		return textWaterBad;
	}

	private JTextField getTextWaterGood() {
		if (textWaterGood == null) {
			textWaterGood = new JTextField();
			textWaterGood.setBounds(144, 159, 86, 20);
			textWaterGood.setColumns(10);
			textWaterGood.setText(ZoneControl.getInstance().getWaterGood().toString());
		}
		return textWaterGood;
	}

	private JLabel getLblIsRed() {
		if (lblIsRed == null) {
			lblIsRed = new JLabel("is blue");
			lblIsRed.setBounds(240, 11, 46, 14);
		}
		return lblIsRed;
	}

	private JLabel getLblIsBlue() {
		if (lblIsBlue == null) {
			lblIsBlue = new JLabel("is blue");
			lblIsBlue.setBounds(240, 74, 46, 14);
		}
		return lblIsBlue;
	}

	private JLabel getLblIsBlue_1() {
		if (lblIsBlue_1 == null) {
			lblIsBlue_1 = new JLabel("is blue");
			lblIsBlue_1.setBounds(240, 137, 46, 14);
		}
		return lblIsBlue_1;
	}

	private JLabel getLabel_8() {
		if (label_8 == null) {
			label_8 = new JLabel("is red");
			label_8.setBounds(240, 36, 46, 14);
		}
		return label_8;
	}

	private JLabel getLabel_9() {
		if (label_9 == null) {
			label_9 = new JLabel("is red");
			label_9.setBounds(240, 99, 46, 14);
		}
		return label_9;
	}

	private JLabel getLabel_10() {
		if (label_10 == null) {
			label_10 = new JLabel("is red");
			label_10.setBounds(240, 162, 46, 14);
		}
		return label_10;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Values inbetween are green");
			lblNewLabel.setBounds(10, 211, 414, 14);
		}
		return lblNewLabel;
	}
}
