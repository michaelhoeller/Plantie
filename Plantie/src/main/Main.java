package main;

import core.FileReader;
import core.MeasurementHolder;
import gui.LineChartCreator;

public class Main {
    
    public static void main(String[] args) throws Exception {
        // Tester.generateData(20);select * from Db_Scripts where dbsc_id > 2000 ORDER BY 1 ASC;
        MeasurementHolder mesHolder = new MeasurementHolder(new FileReader().getFileString());
        final LineChartCreator test = new LineChartCreator("Test", mesHolder.getHoldingList());
        test.pack();
        test.setLocationRelativeTo(null);
        test.setVisible(true);
    }
}
