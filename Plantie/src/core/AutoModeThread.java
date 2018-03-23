package core;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import control.CenterControl;
import control.CustomChartControl;
import control.FTPControl;
import gui.MainPage;
import gui.generation.HighChartGenerator;
import utils.StringSplitter;
import utils.messenger.Notification;

public class AutoModeThread implements Runnable {
    
    private Thread              worker;
    private int                 interval;
    private final AtomicBoolean running  = new AtomicBoolean(false);
    private final AtomicBoolean finished = new AtomicBoolean(false);
    
    MainPage                    mp;
    
    public AutoModeThread(Integer secondsToSleepBetween) {
        if (secondsToSleepBetween == null) {
            secondsToSleepBetween = CenterControl.getInstance().getAutoModeInterval();
        }
        start(secondsToSleepBetween);
    }
    
    private void start(Integer secondsToSleepBetween) {
        ControlSubThread(secondsToSleepBetween * 1000);
        worker = new Thread(this);
        running.set(true);
        finished.set(false);
        worker.start();
    }
    
    private void ControlSubThread(int sleepInterval) {
        interval = sleepInterval;
    }
    
    public void stop() {
        running.set(false);
    }
    
    public void run() {
        CenterControl CC = CenterControl.getInstance();
        while (running.get()) {
            // Thred code here:
            /**
             * Data loading from txt file
             */
            try {
                StringSplitter SS = new StringSplitter(new FileStringReader().getDataString());
                SS.measurementGeneration();
                new Notification("Data successfully loaded from file >data.txt<");
            }
            catch (IOException e) {
                new Notification("Something went wrong during auto mode data loading!");
            }
            /**
             * HighChart generation
             */
            try {
                new HighChartGenerator().generateHtmlChart(CustomChartControl.getInstance().getChartMapTrue(), null, null);
                new Notification("HighChart generated successfully");
            }
            catch (IOException e) {
                new Notification("Error while HighChartGeneration");
            }
            /**
             * FTP - Upload
             */
            try {
                FTPControl.getInstance().uploadChart(CC.getCurrentChartString(), mp);
                new Notification("Upload successfull");
            }
            catch (IOException e) {
                new Notification("Error while uploading");
            }
            // End of thread code
            try {
                // Code to slow down the Automode
                Thread.sleep(interval);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                new Notification("Thread was interrupted, Failed to complete operation");
            }
        }
        finished.set(true);
    }
    
    public AtomicBoolean getFinished() {
        return finished;
    }
    
}
