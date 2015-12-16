import javafx.scene.paint.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item {

//	public static final Color cBROWN = new Color(Color.YELLOW.getRed() / 3, Color.YELLOW.getGreen() / 3, Color.YELLOW.getBlue() / 3);
//	public static final Color cVIOLET = new Color((Color.RED.getRed() + Color.BLUE.getRed()) / 2, 0, (Color.RED.getBlue() + Color.BLUE.getRed()) / 2);
	public static boolean fSelcApple = true, fSelcBlueberry = true, fSelcChestnut = true, fSelcAcorn = true;
	
	public static final File file = new File("." + "\\imgs\\Acorn.png");
	
	public static int nImage = 8; 
	public static BufferedImage[] imgs = new BufferedImage[nImage];

	public static Color cApple = Color.rgb(0xBC, 0x18, 0x15);
	public static Color cChestnut = Color.rgb(0x76, 0x9A, 0x0A);
	public static Color cBlueberry = Color.rgb(0x40, 0x61, 0xE5);
	public static Color cAcorn = Color.rgb(0x7F, 0x53, 0x33);
	
	public static javafx.scene.image.Image iconApple = new javafx.scene.image.Image("images/Apple_24px.png");
	public static javafx.scene.image.Image iconBlueberry = new javafx.scene.image.Image("images/BlueBerry_24px.png");
	public static javafx.scene.image.Image iconChestnut = new javafx.scene.image.Image("images/Chestnut_24px.png");
	public static javafx.scene.image.Image iconAcorn = new javafx.scene.image.Image("images/Acorn_24px.png");
	
	
//	Color cApple = Color.RED; //new Color(0xBC, 0x18, 0x15, 0xFF);
//	Color cChestnut = Color.GREEN; // new Color(0xB4, 0x61, 0xC5, 0xAF);
//	Color cBlueberry = Color.BLUE; // new Color(0x40, 0x61, 0xC5, 0xAF);
//	Color cAcorn = Color.BROWN; // new Color(0x7F, 0x53, 0x33, 0xAF);
//	public static Color getColor(char ch) {
//
//		switch ((int) ch % 8) {
//		case 0:	return Color.BLACK;
//		case 1:	return cBROWN;
//		case 2:	return Color.RED;
//		case 3:	return Color.ORANGE;
//		case 4:	return Color.YELLOW;
//		case 5:	return Color.GREEN;
//		case 6:	return Color.BLUE;
//		case 7:	return cVIOLET;
//		case 8:	return Color.GRAY;
//		case 9:	return Color.WHITE;
//		default:
//			return Color.MAGENTA;
//		}
//	}

	public static Image getImage(char ch) {
		if(imgs[0] == null) readImages();
		return imgs[ch % 8];
	}

	private static void readImages()  {

		char ch = '\\';
		if (!new File("." + ch + "imgs" + ch + "Acorn.png").exists()) {
			ch = '/';
			if (!new File("." + ch + "imgs" + ch + "Acorn.png").exists()) {	System.err.println("Can't find files!"); return; }
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
		}
		catch (IOException e) {
			System.out.println("can't read file");
		}
	}
	
	public static Image getRingImage() {
		try {
			return ImageIO.read(new File("./imgs/White Ring_90px.png"));
					}
		catch (IOException e) {
			System.out.println("can't read file");
		}
		return null;
	}
	
	public static boolean visible(char item) {
		if (item == 'A') return fSelcApple;
		if (item == 'B') return fSelcChestnut;
		if (item == 'C') return fSelcBlueberry;
		if (item == 'D') return fSelcAcorn;
		return true;
	}
}
