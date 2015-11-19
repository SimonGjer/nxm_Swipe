import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;


public class Swipe {

	public static final int nImage = 4;
	public static BufferedImage[] imgs = new BufferedImage[nImage];

	;;;public static Random random = new Random();

	public static int[] swipe = new int[0]; // {1,0, 1,1, 2,2, 2,3, 3,3, 4,3, 5,3, 4,2, 5,2, 6,2, 7,2, 7,3}; //Format x1,y1, x2,y2, ...
	public static int[][] swipes = new int[0][0]; // {{1,0, 1,1}, {3,3, 4,4}, {5,2, 6,2}, {6,4, 5,4},  {9,9, 8,8, 7,7}}; //Format {swipe, swipe, swipe, etc.}

	public static void resetSwipe() {
		swipes = new int[0][0];
	} 

	public static void setSwipe(int[] s) { 
		swipes = new int[1][0];
		swipes[0] = s;
	}
	public static void setSwipes(int[][] s) {
		swipes = s;
	}

	public static void drawSwipe(int[] s) {
		setSwipe(s);
		drawSwipe();
	}
	public static void drawSwipe(int[][] s) {
		setSwipes(s);
		drawSwipe();
	}	

	public static void drawSwipe() {
		System.out.println("drawSwipe()"); //**

		Graphics2D g2d = ShowBoard.getG2D();
		if (g2d == null) return; //??

		double pScale = ShowBoard.pScale;
		int dI = ShowBoard.dI;
		int a; //angle of swipe

		for(int iSwipes = 0; iSwipes < swipes.length; iSwipes++) {
			swipe = swipes[iSwipes];
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
				int wImgScale = (int) (wImg * pScale), hImgcaleS = (int) (hImg * pScale);

				//			;;;System.out.println("xPos: " + xPos + "   yPos: " + yPos);
				//			;;;System.out.println("dI = " + dI);
				//			;;;System.out.println("pScale = " + pScale);
				//			;;;System.out.println("wImg: " + wImg + "   hImg: " + hImg);
				//			;;;System.out.println("wImgScale: " + wImgScale + "   hImgcaleS: " + hImgcaleS);

				xPos += (dx == 0) ? -wImgScale / 2 + dI / 2 : dI / 2 - wImgScale / 2;
				yPos += (dy == 0) ? -hImgcaleS / 2 + dI / 2 : dI / 2 - hImgcaleS / 2;

				g2d.drawImage(img, xPos, yPos, xPos + wImgScale, yPos + hImgcaleS, 0, 0, wImg, hImg, null);
			}
		}
	}





	//Only for test
	public static void rndSwipe() {
		swipe = new int[10 + random.nextInt(5) * 2];
		swipe[0] = random.nextInt(8); swipe[1] = random.nextInt(8);

		for (int i = 2; i < swipe.length; i +=2) {
			int a = random.nextInt(8);
			int dx = 0, dy = 0;

			switch (a) {
			case 0:	dx = 1;	break;
			case 1:	dx = 1;	dy = -1; break;
			case 2:	dy = -1; break;
			case 3:	dx = -1; dy = -1; break;
			case 4:	dx = -1; break;
			case 5:	dx = -1; dy = 1; break;
			case 6:	dx = 0;	dy = 1; break;
			case 7:	dx = 1;	dy = 1; break;
			}

			swipe[i] = swipe[i-2] + dx; swipe[i+1] = swipe[i-1] + dy;
		}
		swipes = new int[1][0];
		swipes[0] = swipe;
		//		ShowBoard.rePaint();
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
			imgs[0] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_0_80.png"));
			imgs[1] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_45_80.png"));
			imgs[2] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_90_80.png"));
			imgs[3] = ImageIO.read(new File("." + ch + "imgs" + ch + "Swipe_135_80.png"));
		}
		catch (IOException e) {
			System.out.println("can't read file");
		}

	}

	public static int[][] transformComp(boolean[][][] components) {
		ArrayList<int[]> cSwipes = new ArrayList<int[]>();

		for (int i=0; i < components.length; i++) {
			boolean[][] comp = components[i];
			int nCol = comp.length, nRow = comp[0].length;
			for (int iRow = 0; iRow < nRow; iRow++)
				for (int iCol = 0; iCol < nCol; iCol++) {
					if (comp[iCol][iRow]) {
						if (iCol + 1 < nCol && comp[iCol + 1][iRow]) cSwipes.add(new int[]{iCol, iRow, iCol + 1, iRow});
						if (iRow + 1 < nRow) {
							if (iCol - 1 >= 0 && comp[iCol - 1][iRow + 1]) cSwipes.add(new int[]{iCol, iRow, iCol - 1, iRow + 1});
							if (comp[iCol][iRow + 1]) cSwipes.add(new int[]{iCol, iRow, iCol, iRow + 1});
							if (iCol + 1 < nCol && comp[iCol + 1][iRow + 1]) cSwipes.add(new int[]{iCol, iRow, iCol + 1, iRow + 1});
						}
					}
				}
		}
		int s = cSwipes.size();
		int[][] compSwipes = new int[s][0];
		for(int i=0; i < s; i++)
			compSwipes[i] = cSwipes.get(i);

		return compSwipes;
	}


	private static Image getImage(int i) {
		if(imgs[0] == null) readImages();
		return imgs[i % nImage];
	}

}
