package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import com.toedter.calendar.JDateChooser;

import gui.helper.LayoutHelper;
import utils.Notification;

public class DateInput {

	@SuppressWarnings("deprecation")
	public Date getDateFromInput() {

		JDateChooser jd = new JDateChooser();
		String message = "Choose start date:\n";
		Object[] params = { message, jd };
		JOptionPane.showConfirmDialog(null, params, "Start date", JOptionPane.PLAIN_MESSAGE);
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		s = sdf.format(((JDateChooser) params[1]).getDate());

		return new Date(s);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void getTime() {
		JFrame frame = new JFrame();

		DateTimePicker dateTimePicker;
		DatePickerSettings dateSettings = new DatePickerSettings();
		TimePickerSettings timeSettings = new TimePickerSettings();

		dateSettings.setAllowEmptyDates(false);
		dateSettings.setAllowKeyboardEditing(false);

		timeSettings.setAllowEmptyTimes(false);
		timeSettings.use24HourClockFormat();
		timeSettings.initialTime = LocalTime.of(15, 30);
		timeSettings.generatePotentialMenuTimes(TimeIncrement.OneHour, null, null);
		timeSettings.setAllowKeyboardEditing(false);

		dateTimePicker = new DateTimePicker(dateSettings, timeSettings);
		dateTimePicker.setBounds(10, 10, 514, 25);

		JButton btn = new JButton("test");
		btn.setBounds(10, 45, 514, 25);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(confirmDate(dateTimePicker));
				new Notification("Time was registered successfull");
			}
		});

		new LayoutHelper(frame, "Choose date and time of first measurement");
		frame.getContentPane().setLayout(null);
		frame.setSize(550, 110);
		frame.getContentPane().add(dateTimePicker);
		frame.getContentPane().add(btn);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private String confirmDate(DateTimePicker dateTimePicker) {
		return dateTimePicker.toString();
	}

}
