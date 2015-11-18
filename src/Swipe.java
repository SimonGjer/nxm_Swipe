import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Swipe {

	public static final int nImage = 4;
	public static BufferedImage[] imgs = new BufferedImage[nImage];


	public static int[] swipe = new int[] {0,0, 1,1, 2,2, 2,3, 3,3, 4,3, 5,3, 4,2, 5,2, 6,2, 7,2, 7,3};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public static void drawSwipe() {
		System.out.println("drawSwipe()"); //**

		Graphics2D g2d = ShowBoard.getG2D();
		if (g2d == null) return;

//		g2d.setColor(Color.CYAN);
//		g2d.fillRect(20, 20, 140, 140);

		int dI = ShowBoard.dI;
		int a; //angle of swipe
		for(int iSwipe = 0; iSwipe < swipe.length - 2; iSwipe += 2) {
			 
			int x1 = swipe[iSwipe],     y1 = swipe[iSwipe + 1];
			int x2 = swipe[iSwipe + 2], y2 = swipe[iSwipe + 3];
			int xPos = (x1 + x2) * dI / 2, yPos = (y1 + y2) * dI / 2;
			
			int dx = x2 - x1, dy = y2 - y1;
			
			if (dx == 0) a = 2;
			else if (dy == 0) a = 0;
			else if (dx + dy == 0) a = 1;
			else a = 3;
	
			Image img = getImage(a);
			
			int wImg = img.getWidth(null), hImg = img.getHeight(null);

			xPos += (dx == 0) ? -wImg / 2 + dI / 2 : dI / 2 - wImg / 2;
			yPos += (dy == 0) ? -hImg / 2 + dI / 2 : dI / 2 - hImg / 2;
			
			g2d.drawImage(img, xPos, yPos, xPos + wImg, yPos + hImg, 0, 0, wImg, hImg, null);
		}	



	}



	public static void readImages() {

		char ch = '\\';
		if (!new File("." + ch + "imgs" + ch + "Acorn.png").exists()) {
			ch = '/';
			if (!new File("." + ch + "imgs" + ch + "Acorn.png").exists()) {
				System.out.println("Can't find files!"); return;
			}
		}
		try {
			imgs[0] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_0.png"));
			imgs[1] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_45.png"));
			imgs[2] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_90.png"));
			imgs[3] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_135.png"));
		}
		catch (IOException e) {
			System.out.println("can't read file");
		}

	}

	public static Image getImage(int i) {
		if(imgs[0] == null) readImages();
		return imgs[i % nImage];
	}

}
