package gui;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainPage extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JButton           btnSelectDocument;
    private JLabel            lblDocumentPath;
    
    public MainPage() {
        setTitle("Plantie");
        this.setDefaultCloseOperation(3);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/icon2.png")));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(getBtnSelectDocument());
        getContentPane().add(getLblDocumentPath());
        this.setVisible(true);
    }
    
    private JButton getBtnSelectDocument() {
        if (btnSelectDocument == null) {
            btnSelectDocument = new JButton("Choose Document");
            btnSelectDocument.setBounds(291, 239, 143, 23);
        }
        return btnSelectDocument;
    }
    
    private JLabel getLblDocumentPath() {
        if (lblDocumentPath == null) {
            lblDocumentPath = new JLabel("New label");
            lblDocumentPath.setBounds(10, 243, 180, 14);
        }
        return lblDocumentPath;
    }
}
