package utils.gui;

import java.awt.Component;
import java.awt.Container;

public class EnableHelper {
	public static void setEnabledAll(Object object, boolean state) {
		if (object instanceof Container) {
			Container c = (Container) object;
			Component[] components = c.getComponents();
			for (Component component : components) {
				setEnabledAll(component, state);
				component.setEnabled(state);
			}
		} else {
			if (object instanceof Component) {
				Component component = (Component) object;
				component.setEnabled(state);
			}
		}
	}
}
