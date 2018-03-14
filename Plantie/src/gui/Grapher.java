package gui;

import java.io.IOException;
import java.util.List;

import core.FileReader;
import core.Measurement;
import core.MeasurementHolder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Grapher extends Application {
    
    private static MeasurementHolder mh;
    private static List<Measurement> mesList;
    private Integer                  counterTemp = 1;
    private Integer                  counterHum  = 1;
    private Integer                  counterL    = 1;
    private Integer                  counterR    = 1;
    private static String            select;
    
    // public Grapher(List<Measurement> mesList) {
    // this.mesList = mesList;
    // launch();
    // }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void start(Stage stage) {
        stage.setTitle("Measurements");
        stage.centerOnScreen();
        // defining the axes
        final NumberAxis xAxisT = new NumberAxis();
        final NumberAxis yAxisT = new NumberAxis();
        final NumberAxis xAxisH = new NumberAxis();
        final NumberAxis yAxisH = new NumberAxis();
        final NumberAxis xAxisL = new NumberAxis();
        final NumberAxis yAxisL = new NumberAxis();
        final NumberAxis xAxisR = new NumberAxis();
        final NumberAxis yAxisR = new NumberAxis();
        xAxisT.setLabel("Time");
        yAxisT.setLabel("Value");
        xAxisH.setLabel("Time");
        yAxisH.setLabel("Value");
        xAxisL.setLabel("Time");
        yAxisL.setLabel("Value");
        xAxisR.setLabel("Time");
        yAxisR.setLabel("Value");
        
        // creating the chart
        final LineChart<Number, Number> lineChartTemperature = new LineChart<Number, Number>(xAxisT, yAxisT);
        
        final LineChart<Number, Number> lineChartHumidity = new LineChart<Number, Number>(xAxisH, yAxisH);
        
        final LineChart<Number, Number> lineChartMoistureLeft = new LineChart<Number, Number>(xAxisL, yAxisL);
        
        final LineChart<Number, Number> lineChartMoistureRight = new LineChart<Number, Number>(xAxisR, yAxisR);
        
        lineChartTemperature.setTitle("Temperature");
        lineChartHumidity.setTitle("Humidity");
        lineChartMoistureLeft.setTitle("Moisture Left");
        lineChartMoistureRight.setTitle("Moisture Right");
        
        // defining the series
        XYChart.Series tempSeries = new XYChart.Series();
        // tempSeries.setName("Temperature");
        
        XYChart.Series humSeries = new XYChart.Series();
        // humSeries.setName("Humidity");
        
        XYChart.Series moistLSeries = new XYChart.Series();
        moistLSeries.setName(" ");
        
        XYChart.Series moistRSeries = new XYChart.Series();
        moistRSeries.setName(" ");
        FlowPane root = new FlowPane();
        
        switch (select) {
            case "temp":
                for (Measurement measurement : mesList) {
                    tempSeries.getData().add(new XYChart.Data(counterTemp, measurement.getTemperature()));
                    counterTemp++;
                }
                root.getChildren().add(lineChartTemperature);
                break;
            
            default:
                break;
        }
        
        // populating the series with data
        // for (Measurement measurement : mesList) {
        // tempSeries.getData().add(new XYChart.Data(counterTemp, measurement.getTemperature()));
        // humSeries.getData().add(new XYChart.Data(counterHum, measurement.getHumidity()));
        // moistLSeries.getData().add(new XYChart.Data(counterL, measurement.getMoistureLeft()));
        // moistRSeries.getData().add(new XYChart.Data(counterR, measurement.getMoistureRight()));
        // counterR++;
        // counterL++;
        // counterHum++;
        // counterTemp++;
        // }
        
        // root.getChildren().addAll(lineChartTemperature, lineChartHumidity, lineChartMoistureLeft, lineChartMoistureRight);
        
        lineChartTemperature.getData().add(tempSeries);
        lineChartHumidity.getData().add(humSeries);
        lineChartMoistureLeft.getData().add(moistLSeries);
        lineChartMoistureRight.getData().add(moistRSeries);
        
        Scene scene = new Scene(root, 1025, 800);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader();
        mh = new MeasurementHolder(fr.getFileString());
        mesList = mh.getHoldingList();
        launch(args);
        System.exit(1);
    }
    
    public static void go(String[] args, String select) throws IOException {
        setSelect(select);
        FileReader fr = new FileReader();
        mh = new MeasurementHolder(fr.getFileString());
        mesList = mh.getHoldingList();
        launch(args);
        System.exit(1);
    }
    
    private static void setSelect(String select2) {
        select = select2;
    }
}
