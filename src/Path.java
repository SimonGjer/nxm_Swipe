import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import javax.imageio.ImageIO;


public class Path {

	public static final int nImage = 4;
	public static BufferedImage[] imgs = new BufferedImage[nImage];

	;;;public static Random random = new Random();

	public static int[] path = new int[0]; // {1,0, 1,1, 2,2, 2,3, 3,3, 4,3, 5,3, 4,2, 5,2, 6,2, 7,2, 7,3}; //Format x1,y1, x2,y2, ...
	public static int[][] paths = new int[0][0]; // {{1,0, 1,1}, {3,3, 4,4}, {5,2, 6,2}, {6,4, 5,4},  {9,9, 8,8, 7,7}}; //Format {swipe, swipe, swipe, etc.}

	public static void resetPath() {
		paths = new int[0][0];
	} 

	public static void setPath(int[] s) {
		paths = new int[1][0];
		paths[0] = s;
	}
	public static void setPath(Integer[] s) {
		int[] sInt = new int[s.length];	for(int i=0; i<s.length; i++) sInt[i] = s[i]; //??LookAt
		paths = new int[1][0];
		paths[0] = sInt;
	}

	public static void setPath(int[][] s) {
		paths = s;
	}

	public static void drawPath(int[] s) {
		setPath(s);
		drawPath();
	}
	public static void drawPath(Integer[] s) {
		int[] sInt = new int[s.length];	for(int i=0; i<s.length; i++) sInt[i] = s[i]; //??LookAt
		setPath(sInt);
		drawPath();
	}
	public static void drawPath(int[][] s) {
		setPath(s);
		drawPath();
	}
	public static void drawPath2(ArrayList<Integer> s) { //Java can't see the difference between "ArrayList<Integer>" and "ArrayList<Integer[]>" - this is the reason for the "2" in the name "drawSwipe2"
		int[] tmp = new int[s.size()];
		for(int i = 0; i < s.size(); i++)
			tmp[i]=s.get(i);
		drawPath(tmp); 
	}

	public static void drawPath(ArrayList<Integer[]> s) {
		int[][] tmp = new int[s.size()][0];
		for (int i = 0; i < s.size(); i++) {
			int[] tmp2 = new int[s.get(i).length];
			for(int j = 0; j < s.get(i).length; j++) {
				tmp2[j] = s.get(i)[j];
			}
			tmp[i] = tmp2;
		}
		drawPath(tmp);
	}

	public static void drawPath() {
		System.out.println("drawSwipe()"); //**

		Graphics2D g2d = ShowBoard.getGraphic2D();
		if (g2d == null) return; //??

		double pScale = ShowBoard.pScale;
		int dI = ShowBoard.dI;
		int a; //angle of swipe

		for(int iPaths = 0; iPaths < paths.length; iPaths++) {
			path = paths[iPaths];
			for(int iPath = 0; iPath < path.length - 2; iPath += 2) {

				int x1 = path[iPath],     y1 = path[iPath + 1];
				int x2 = path[iPath + 2], y2 = path[iPath + 3];
				int xPos = (x1 + x2) * dI / 2, yPos = (y1 + y2) * dI / 2;

				int dx = x2 - x1, dy = y2 - y1;

				if (dx == 0) a = 2;
				else if (dy == 0) a = 0;
				else if (dx + dy == 0) a = 1;
				else a = 3;

				Image img = getImage(a);

				int wImg = img.getWidth(null), hImg = img.getHeight(null);
				int wImgScale = (int) (wImg * pScale), hImgcaleS = (int) (hImg * pScale);

				xPos += (dx == 0) ? -wImgScale / 2 + dI / 2 : dI / 2 - wImgScale / 2;
				yPos += (dy == 0) ? -hImgcaleS / 2 + dI / 2 : dI / 2 - hImgcaleS / 2;

				g2d.drawImage(img, xPos, yPos, xPos + wImgScale, yPos + hImgcaleS, 0, 0, wImg, hImg, null);
			}
		}
	}

	public static final double SQRT2 = Math.sqrt(2);


	public static void drawPath3d(ArrayList<Integer> s, Group grPath) { //Java can't see the difference between "ArrayList<Integer>" and "ArrayList<Integer[]>" - this is the reason for the "2" in the name "drawSwipe2"
		int[] tmp = new int[s.size()];
		for(int i = 0; i < s.size(); i++)
			tmp[i]=s.get(i);
		drawPath3d(tmp, grPath); 
	}
	public static void drawPath3d(int[] s, Group grPath) {
		setPath(s);
		drawPath3d(paths, grPath);
	}

	public static void drawPath3d(int[][] paths, Group grPath) {
		drawPath3d(paths, grPath, null);
	}

	public static void drawPath3d(int[][] paths, Group grPath, Color cColor) {
		System.out.println("drawSwipe3d()"); //**

		Group board3d = Main.board3d;
		//		;;;System.out.println("board3d.getChildren().size(): " + board3d.getChildren().size());
		int rot; //angle of swipe
		double lCyl; //Lenght of Cyl
		PhongMaterial matCyl = new PhongMaterial();
		PhongMaterial matApple = new PhongMaterial();
		PhongMaterial matChestnut = new PhongMaterial();
		PhongMaterial matBlueberry = new PhongMaterial();
		PhongMaterial matAcorn = new PhongMaterial();
		PhongMaterial mat = new PhongMaterial();

		Color cApple = Color.RED; //new Color(0xBC, 0x18, 0x15, 0xFF);
		Color cChestnut = Color.GREEN; // new Color(0xB4, 0x61, 0xC5, 0xAF);
		Color cBlueberry = Color.BLUE; // new Color(0x40, 0x61, 0xC5, 0xAF);
		Color cAcorn = Color.BROWN; // new Color(0x7F, 0x53, 0x33, 0xAF);

		if (cColor != null) {
			matCyl.setDiffuseColor(cColor);
			matCyl.setSpecularColor(cColor.brighter());
		}

		matApple.setDiffuseColor(cApple);
		matApple.setSpecularColor(cApple.brighter());
		matChestnut.setDiffuseColor(cChestnut);
		matChestnut.setSpecularColor(cChestnut.brighter());
		matBlueberry.setDiffuseColor(cBlueberry);
		matBlueberry.setSpecularColor(cBlueberry.brighter());
		matAcorn.setDiffuseColor(cAcorn);
		matAcorn.setSpecularColor(cAcorn.brighter());

		PhongMaterial[] matCyls = new PhongMaterial[]{matApple, matChestnut, matBlueberry, matAcorn};
		Rotate rotX90 = new Rotate(90, 0, 0, 0, Rotate.X_AXIS);

		int nCol = Board.nCol, nRow = Board.nRow;
		double xMid = nCol / 2.0, yMid = nRow / 2.0;

		char[][] board = Board.getBoard();

//		Group grPath = Main.grPath;

		grPath.getChildren().clear();

		int x1, y1, x2 = 0, y2 = 0, dx, dy;
		double xPos, yPos;

		for(int iPaths = 0; iPaths < paths.length; iPaths++) {
			path = paths[iPaths];
			for(int iPath = 0; iPath < path.length - 2; iPath += 2) {

				x1 = path[iPath];     y1 = path[iPath + 1];
				x2 = path[iPath + 2]; y2 = path[iPath + 3];
				dx = x2 - x1; dy = y2 - y1;
				xPos = (x1 + x2) / 2.0; yPos = (y1 + y2) / 2.0;

				if (dx == 0) rot = 0; else if (dy == 0) rot = 90; else if (dx + dy == 0) rot = 135;	else rot = 45;
				if (rot == 0 || rot == 90) lCyl = 1; else lCyl = SQRT2;


				if (cColor == null) { mat = matCyls[(board[x1][y1] - 'A') % matCyls.length];
				} else { mat = matCyl; }

				Sphere sphere = new Sphere(0.1);
				Cylinder cyl = new Cylinder(0.1, lCyl);

				sphere.setMaterial(mat);
				sphere.setTranslateX(x1 - xMid + 0.5);
				sphere.setTranslateZ(yMid - y1 - 0.5);

				cyl.setMaterial(mat);
				cyl.setTranslateX(xPos - xMid + 0.5);
				cyl.setTranslateZ(yMid - yPos - 0.5);
				//				cyl.setId("Path " + iPath);
				Rotate rotZ = new Rotate(rot, 0, 0, 0, Rotate.Z_AXIS);
				cyl.getTransforms().addAll(rotX90, rotZ);

				grPath.getChildren().addAll(sphere, cyl);
			}
			Sphere sphere = new Sphere(0.1);
			sphere.setMaterial(mat);
			sphere.setTranslateX(x2 - xMid + 0.5);
			sphere.setTranslateZ(yMid - y2 - 0.5);
			grPath.getChildren().add(sphere);
		}
	}
	
	public static void resetPath3d() {
		paths = new int[0][0];
		Main.grPathComp.getChildren().clear();
				Main.grPathBigComp.getChildren().clear();
		Main.grPathBruteForce.getChildren().clear();
		Main.grPathRnd.getChildren().clear();
		
//		drawPath3d(paths);
	} 
	
	public static Group deleteNodes(Group gr, String txt) {
		Group newGr = new Group();

		for(Node node : gr.getChildren()) {
			String id = node.getId();
			//			if (id == null || !id.contains(txt)) newGr.getChildren().add(node);
		}
		return newGr;
	}


	//Only for test
	;;;public static void rndPath() {
		path = new int[10 + random.nextInt(5) * 2];
		path[0] = random.nextInt(8); path[1] = random.nextInt(8);

		for (int i = 2; i < path.length; i += 2) {
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
			path[i] = path[i-2] + dx; path[i+1] = path[i-1] + dy;
		}
		paths = new int[1][0];
		paths[0] = path;
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
		catch (IOException e) {	System.out.println("can't read file"); }
	}

	public static int[][] compToPath(boolean[][][] components) {
		ArrayList<int[]> cPath = new ArrayList<int[]>();

		for (int i = 0; i < components.length; i++) {
			boolean[][] comp = components[i];
			int nCol = comp.length, nRow = comp[0].length;
			for (int iRow = 0; iRow < nRow; iRow++) {
				for (int iCol = 0; iCol < nCol; iCol++) {
					if (comp[iCol][iRow]) {
						if (iCol + 1 < nCol && comp[iCol + 1][iRow]) cPath.add(new int[]{iCol, iRow, iCol + 1, iRow});
						if (iRow + 1 < nRow) {
							if (iCol - 1 >= 0 && comp[iCol - 1][iRow + 1]) cPath.add(new int[]{iCol, iRow, iCol - 1, iRow + 1});
							if (comp[iCol][iRow + 1]) cPath.add(new int[]{iCol, iRow, iCol, iRow + 1});
							if (iCol + 1 < nCol && comp[iCol + 1][iRow + 1]) cPath.add(new int[]{iCol, iRow, iCol + 1, iRow + 1});
						}
					}
				}
			}
		}
		int s = cPath.size();
		int[][] compPaths = new int[s][0];
		for(int i=0; i < s; i++) compPaths[i] = cPath.get(i);
		return compPaths;
	}

	private static Image getImage(int i) {
		if(imgs[0] == null) readImages();
		return imgs[i % nImage];
	}
}
