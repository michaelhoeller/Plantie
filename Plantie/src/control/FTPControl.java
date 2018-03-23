package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.ftp.FTPClient;

import gui.MainPage;
import utils.Constants;
import utils.messenger.Notification;

public class FTPControl {

	private String ftpHost;
	private String ftpUser;
	private String ftpPassword;
	private FTPClient ftpClient;

	private static FTPControl INSTANCE;

	public static FTPControl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FTPControl();
		}
		return INSTANCE;
	}

	private FTPControl() {
		ftpClient = new FTPClient();

		ftpHost = Constants.FTP_HOST;
		ftpUser = Constants.FTP_USER;
		ftpPassword = Constants.getFTP_PASSWORD();
	}

	private void connect(MainPage mp) {
		try {
			FTPClient connClient = getFtpClient();
			connClient.connect(getFTPHost());

			if (connClient.login(getFTPUser(), getFTPPassword())) {
				new Notification("connection established");
			} else {
				new Notification("connection failed");
			}
		} catch (Exception e) {
			new Notification("Connection could not be established");

		}
	}

	public void uploadChart(String chartString, MainPage mp) throws IOException {
		connect(mp);
		File file = new File(System.getProperty("java.io.tmpdir"), "temp");

		PrintWriter writer = new PrintWriter(file);
		writer.write(chartString);
		writer.close();

		InputStream is = new FileInputStream(file);
		ftpClient.storeFile(Constants.REMOTE_ADRESS + Constants.CHART_FILENAME, is);
		is.close();
		file.delete();
		new Notification("File uploaded successfully!");

	}

	private String getFTPHost() {
		return ftpHost;
	}

	private String getFTPUser() {
		return ftpUser;
	}

	private String getFTPPassword() {
		return ftpPassword;
	}

	private FTPClient getFtpClient() {
		return ftpClient;
	}

}
