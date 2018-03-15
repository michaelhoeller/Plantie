package gui.helper;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRootPane;

public class LayoutHelper {
    
    public LayoutHelper(JFrame jFrame, String title, JButton defaultButton) {
        jFrame.setTitle(title);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/icon2.png")));
        jFrame.setResizable(false);
        jFrame.setSize(450, 300);
        jFrame.setLocationRelativeTo(null);
        if (defaultButton != null) {
            JRootPane rootPane = jFrame.getRootPane();
            rootPane.setDefaultButton(defaultButton);
        }
    }
    
}
