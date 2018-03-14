package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenHelper {
    
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    /**
     * @param divisor
     *            See @Constants
     * @return
     */
    public static int getWidth(Integer divisor) {
        return (int) (screenSize.getWidth() / divisor);
    }
    
    /**
     * @param divisor
     *            See {@Constants}
     * @return
     */
    public static int getHeight(Integer divisor) {
        return (int) (screenSize.getHeight() / divisor);
    }
}
