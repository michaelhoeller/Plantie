package gui.helper;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class LayoutHelper {
    
    public LayoutHelper(JFrame jFrame, String title) {
        jFrame.setTitle(title);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/icon2.png")));
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setSize(450, 300);
    }
    
}
