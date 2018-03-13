package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

import utils.Constant;

public class Tester {
    
    public static void generateData(Integer amount) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(Constant.FILENAME, "UTF-8");
        Integer minutes = 0;
        Integer hours = 0;
        Integer days = 0;
        
        for (int i = 0; i < amount; i++) {
            writer.println(days + ";" + hours + ";" + minutes + ";" + 0 + "|" + r(10, 60) + ";" + r(10, 60) + ";" + r(100, 600) + ";" + r(100, 600) + "?");
            
            minutes += 10;
            if (minutes >= 60) {
                minutes -= 60;
                hours++;
            }
            if (hours >= 24) {
                hours -= 24;
                days++;
            }
        }
        writer.close();
    }
    
    private static int r(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
}
