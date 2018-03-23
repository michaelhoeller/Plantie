package utils.messenger;

import java.util.Date;

import control.CenterControl;
import gui.MainPage;
import utils.DateConverter;

public class LogError {

	public LogError(String msg) {
		CenterControl.getInstance()
				.addErrorString("Error at " + DateConverter.convWithS(new Date()).toString() + ":\n" + msg);
		ScrollPane.update(MainPage.getMainPage());
	}
}
