import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Item {

	public static final Color cBROWN = new Color(Color.YELLOW.getRed() / 3, Color.YELLOW.getGreen() / 3, Color.YELLOW.getBlue() / 3);
	public static final Color cVIOLET = new Color((Color.RED.getRed() + Color.BLUE.getRed()) / 2, 0, (Color.RED.getBlue() + Color.BLUE.getRed()) / 2);
//	public static final File[] files = new File("." + "\\imgs").listFiles(); //**

	public static final File file = new File("." + "\\imgs\\Acorn.png");
	
	public static int nImage = 8; 
	public static BufferedImage[] imgs = new BufferedImage[nImage];

	public static Color getColor(char ch) {

		switch ((int) ch % 10) {
		case 0:	return Color.BLACK;
		case 1:	return cBROWN;
		case 2:	return Color.RED;
		case 3:	return Color.ORANGE;
		case 4:	return Color.YELLOW;
		case 5:	return Color.GREEN;
		case 6:	return Color.BLUE;
		case 7:	return cVIOLET;
		case 8:	return Color.GRAY;
		case 9:	return Color.WHITE;
		default:
			return Color.MAGENTA;
		}
	}

	public static Image getImage(char ch) {
		if(imgs[0] == null) readImages();
		return imgs[ch % 8];
	}

	private static void readImages()  {

		char ch = '\\';
		if (!new File("." + ch + "imgs" + ch + "Acorn.png").exists()) {
			ch = '/';
			if (!new File("." + ch + "imgs" + ch + "Acorn.png").exists()) {
				System.out.println("Can't find files!"); return;
			}
		}
		try {
			imgs[0] = ImageIO.read(new File("." + ch + "imgs" + ch + "Acorn.png"));
			imgs[1] = ImageIO.read(new File("." + ch + "imgs" + ch + "Apple.png"));
			imgs[2] = ImageIO.read(new File("." + ch + "imgs" + ch + "BlueBerry.png"));
			imgs[3] = ImageIO.read(new File("." + ch + "imgs" + ch + "Chestnut.png"));
			imgs[4] = ImageIO.read(new File("." + ch + "imgs" + ch + "Dandelion.png"));
			imgs[5] = ImageIO.read(new File("." + ch + "imgs" + ch + "Flower_Blue.png"));
			imgs[6] = ImageIO.read(new File("." + ch + "imgs" + ch + "Strawberry.png"));
			imgs[7] = ImageIO.read(new File("." + ch + "imgs" + ch + "Mushroom.png"));
//			imgs[8] = ImageIO.read(new File("." + ch + "imgs" + ch + "Clover.png"));
//			imgs[9] = ImageIO.read(new File("." + ch + "imgs" + ch + "Clover_Golden.png"));

		}
		catch (IOException e) {
			System.out.println("can't read file");
		}
	}
}
