package main;

import core.FileReader;
import core.MeasurementHolder;
import gui.LineChartCreator;

public class Main {
    
    public static void main(String[] args) throws Exception {
        // Tester.generateData(50);
        new LineChartCreator("Test", new MeasurementHolder(new FileReader().getFileString()).getHoldingList());
    }
}
