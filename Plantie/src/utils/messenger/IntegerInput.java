package utils.messenger;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import utils.Constants;

public class IntegerInput {

	private Integer outcome;

	public IntegerInput(String msg) {
		ImageIcon imgIco = createImageIcon(Constants.ICONPATH);
		Image converted = getScaledImage(imgIco.getImage(), 40, 40);
		ImageIcon test = new ImageIcon(converted);
		try {
			setOutcome(Integer.parseInt((String) JOptionPane.showInputDialog(null, msg, "Plantie - Question",
					JOptionPane.INFORMATION_MESSAGE, test, null, 100)));
		} catch (NumberFormatException e) {
			new Notification("Please only numbers you dumb fuck!");
		}
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			new LogError("Couldn't find file: " + path);
			return null;
		}
	}

	public Integer getOutcome() {
		return outcome;
	}

	public void setOutcome(Integer outcome) {
		this.outcome = outcome;
	}
}
