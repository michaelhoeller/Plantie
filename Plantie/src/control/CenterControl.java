package control;

public class CenterControl {

	private static CenterControl INSTANCE;
	private boolean zoneControl = false;
	private String errorString = "";
	private String currentChartString = null;

	private String operationMode = "manual";

	private boolean notificationsWanted = false;

	private Integer dataSets;

	private CenterControl() {
	}

	public static CenterControl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CenterControl();
		}
		return INSTANCE;
	}

	public boolean isAutoMode() {
		if (operationMode.equals("manual")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isZoneControl() {
		return zoneControl;
	}

	public void setZoneControl(boolean zoneControl) {
		this.zoneControl = zoneControl;
	}

	public String getErrorString() {
		return errorString;
	}

	public void addErrorString(String errorString) {
		this.errorString += errorString + "\n----------------------------------------------------------------------\n";
	}

	public String getCurrentChartString() {
		return currentChartString;
	}

	public void setCurrentChartString(String currentChartString) {
		this.currentChartString = currentChartString;
	}

	public Integer getDataSets() {
		return dataSets;
	}

	public void setDataSets(Integer dataSets) {
		this.dataSets = dataSets;
	}

	public String getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}

	public boolean isNotificationsWanted() {
		return notificationsWanted;
	}

	public void setNotificationsWanted(boolean notificationsDisabled) {
		this.notificationsWanted = notificationsDisabled;
	}
}
