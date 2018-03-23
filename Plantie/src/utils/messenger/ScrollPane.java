package utils.messenger;

import control.CenterControl;
import gui.MainPage;

public class ScrollPane {

	public static void update(MainPage mp) {
		mp.getTextPane().setText(CenterControl.getInstance().getErrorString());
	}

	public static void updateTest(MainPage mp, String toWrite) {
		mp.getTextPane().setText(toWrite);
	}
}
