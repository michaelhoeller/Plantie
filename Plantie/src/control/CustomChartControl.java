package control;

import java.util.Date;
import java.util.HashMap;

public class CustomChartControl {

	private static CustomChartControl INSTANCE = null;
	private Date startDate;
	private Date endDate;
	private Boolean isConfigured = false;

	private Boolean tempChart = true;
	private Boolean humChart = true;
	private Boolean waterChart = true;

	private Boolean allDates = false;

	private HashMap<Integer, Boolean> chartMap = new HashMap<>();
	private HashMap<Integer, Boolean> chartMapTrue = new HashMap<>();

	public HashMap<Integer, Boolean> getChartMapTrue() {
		chartMapTrue.put(1, true);
		chartMapTrue.put(2, true);
		chartMapTrue.put(3, true);
		chartMapTrue.put(4, true);
		return chartMapTrue;
	}

	public HashMap<Integer, Boolean> getChartMap() {
		chartMap.put(1, getTempChart());
		chartMap.put(2, getHumChart());
		chartMap.put(3, getWaterChart());
		chartMap.put(4, getWaterChart());
		return chartMap;
	}

	private CustomChartControl() {
	}

	public static CustomChartControl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CustomChartControl();
		}
		return INSTANCE;
	}

	public Date getStartDate() {
		if (getAllDates()) {
			return null;
		}
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		if (getAllDates()) {
			return null;
		}
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsConfigured() {
		return isConfigured;
	}

	public void setIsConfigured(Boolean isConfigured) {
		this.isConfigured = isConfigured;
	}

	public Boolean getAllDates() {
		return allDates;
	}

	public void setAllDates(Boolean allDates) {
		this.allDates = allDates;
	}

	public Boolean getTempChart() {
		return tempChart;
	}

	public void setTempChart(Boolean tempChart) {
		this.tempChart = tempChart;
	}

	public Boolean getHumChart() {
		return humChart;
	}

	public void setHumChart(Boolean humChart) {
		this.humChart = humChart;
	}

	public Boolean getWaterChart() {
		return waterChart;
	}

	public void setWaterChart(Boolean waterChart) {
		this.waterChart = waterChart;
	}
}
