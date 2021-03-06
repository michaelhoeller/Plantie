package utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

import utils.messenger.Notification;

public class DataGenerator {

	public static void generateData(Integer amount) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(Constants.FILENAME, "UTF-8");
		Integer minutes = 0;
		Integer hours = 0;
		Integer days = 0;

		Integer tempTemp = 23;
		Integer tempHum = 50;
		Integer tempL = 330;
		Integer tempR = 330;

		for (int i = 0; i < amount; i++) {
			writer.println(days + ";" + hours + ";" + minutes + ";" + 0 + "|" + tempTemp + ";" + tempHum + ";" + tempL
					+ ";" + tempR + "?");

			tempTemp = nextVal(tempTemp);
			tempHum = nextVal(tempHum);
			tempL = nextVal(tempL);
			tempR = nextVal(tempR);

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
		new Notification(amount + " values generated");
	}

	private static Integer nextVal(Integer currentVal) {
		return r(currentVal - 3, currentVal + 3);
	}

	private static int r(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
