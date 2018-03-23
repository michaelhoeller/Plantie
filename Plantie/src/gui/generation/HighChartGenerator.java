package gui.generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import control.CenterControl;
import control.MeasurementControl;
import control.ZoneControl;
import core.Measurement;
import utils.DateConverter;
import utils.messenger.Notification;

public class HighChartGenerator {

	private String htmlString;

	public HighChartGenerator() throws IOException {
		// DEVELOPER VERSION
		// URL url = getClass().getResource(Constants.HTMLPATH);
		// File htmlTemplateFile = new File(url.getPath());
		// htmlString = new FileStringReader().getHtmlString(htmlTemplateFile);
		htmlString = HtmlTemplate.getHtmltemplate();
		if (htmlString.equals(null)) {
			new Notification("Something went wrong during HTML-template instantiation");
		}
	}

	public void generateHtmlChart(HashMap<Integer, Boolean> valueSwitch, Date from, Date to)
			throws FileNotFoundException {
		// Variablen
		// Dont Touch me!
		List<Measurement> mesList = new ArrayList<Measurement>(MeasurementControl.getInstance().getHoldingList());
		CenterControl CC = CenterControl.getInstance();
		// Check which values are wanted
		Boolean selectedTemp = valueSwitch.get(1);
		Boolean selectedHum = valueSwitch.get(2);
		Boolean selectedLeft = valueSwitch.get(3);
		Boolean selectedRight = valueSwitch.get(4);
		Boolean zoneControl = CC.isZoneControl();

		Integer dataSets = CC.getDataSets();

		String timeStampReplacement = "";
		String seriesReplacement = "";

		// Delete unwanted measurements
		if (from != null && to != null) {
			List<Measurement> tempMes = new ArrayList<Measurement>(mesList);
			for (Measurement measurement : tempMes) {
				if (measurement.getDate().getTime() > to.getTime()
						|| measurement.getDate().getTime() < from.getTime()) {
					mesList.remove(measurement);
				}
			}
		}

		/*
		 * REPLACE: $timeStamp = dates of the different measurements | Format: 'date1',
		 * 'date2', 'date3',... $series = name of series and datasets | Format: {name:
		 * 'seriesName1', data: [value1,value2,value3,....},{name: 'seriesName2', data:
		 * [value1,value2,value3,....}
		 */
		for (Measurement measurement : mesList) {
			timeStampReplacement += "'" + DateConverter.conv(measurement.getDate()) + "', ";
		}

		if (selectedTemp) {
			seriesReplacement += "{name: 'Temperature', lineWidth: 0.7,\n                     data: [";
			for (Measurement measurement : mesList) {
				seriesReplacement += measurement.getTemperature() + ", ";
			}
			if (zoneControl) {
				seriesReplacement += ZoneControl.getInstance().getCustomTempZone();
			}
			seriesReplacement += "]},\n";
		}
		if (selectedHum)

		{
			seriesReplacement += "{name: 'Humidity', lineWidth: 0.7,\n                     data: [";
			for (Measurement measurement : mesList) {
				seriesReplacement += measurement.getHumidity() + ", ";
			}
			if (zoneControl) {
				seriesReplacement += ZoneControl.getInstance().getCustomHumZone();
			}
			seriesReplacement += "]},\n";
		}
		if (selectedLeft) {
			seriesReplacement += "{name: 'Waterlevel left', lineWidth: 0.7,\n                     data: [";
			for (Measurement measurement : mesList) {
				seriesReplacement += measurement.getMoistureLeft() + ", ";
			}
			if (zoneControl) {
				seriesReplacement += ZoneControl.getInstance().getCustomWaterZone();
			}
			seriesReplacement += "]},\n";
		}
		if (selectedRight) {
			seriesReplacement += "{name: 'Waterlevel right', lineWidth: 0.7,\n                     data: [";
			for (Measurement measurement : mesList) {
				seriesReplacement += measurement.getMoistureRight() + ", ";
			}
			if (zoneControl) {
				seriesReplacement += ZoneControl.getInstance().getCustomWaterZone();
			}
			seriesReplacement += "]},";
		}

		// replace
		htmlString = htmlString.replace("$amount", dataSets.toString());
		htmlString = htmlString.replace("$timeStamp", timeStampReplacement);
		htmlString = htmlString.replace("$series", seriesReplacement);

		// Generate the HTML
		File newHtmlFile = new File("Chart.html");
		PrintWriter out = new PrintWriter(newHtmlFile);
		out.print(htmlString);
		CenterControl.getInstance().setCurrentChartString(htmlString);
		out.close();

		new Notification("Chart successfully generated");
		ZoneControl.getInstance().setIsNotified(false);
	}

}
