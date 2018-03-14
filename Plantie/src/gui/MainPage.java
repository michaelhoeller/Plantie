package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.helper.LayoutHelper;

public class MainPage extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JMenuBar          mBar;
    private JMenu             mnMode;
    private JMenuItem         mntmStart;
    private JMenuItem         mntmMan;
    private JMenu             mnAuto;
    private JMenuItem         mntmConfiguration;
    
    public MainPage() {
        init();
        new LayoutHelper(this, "Plantie");
        this.setVisible(true);
    }
    
    private void init() {
        setJMenuBar(getMBar());
    }
    
    private JMenuBar getMBar() {
        if (mBar == null) {
            mBar = new JMenuBar();
            mBar.add(getMnMode());
        }
        return mBar;
    }
    
    private JMenu getMnMode() {
        if (mnMode == null) {
            mnMode = new JMenu("Mode");
            mnMode.add(getMnAuto());
            mnMode.add(getMntmMan());
        }
        return mnMode;
    }
    
    private JMenuItem getMntmStart() {
        if (mntmStart == null) {
            mntmStart = new JMenuItem("Start");
            mntmStart.setEnabled(false);
        }
        return mntmStart;
    }
    
    private JMenuItem getMntmMan() {
        if (mntmMan == null) {
            mntmMan = new JMenuItem("Manuell");
        }
        return mntmMan;
    }
    
    private JMenu getMnAuto() {
        if (mnAuto == null) {
            mnAuto = new JMenu("Atutomatic");
            mnAuto.add(getMntmStart());
            mnAuto.add(getMntmConfiguration());
        }
        return mnAuto;
    }
    
    private JMenuItem getMntmConfiguration() {
        if (mntmConfiguration == null) {
            mntmConfiguration = new JMenuItem("Configuration");
        }
        return mntmConfiguration;
    }
}
