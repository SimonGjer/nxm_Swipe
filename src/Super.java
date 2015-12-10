//import java.awt.Color;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;


public class Super {

	static final int UP = 0, RIGTHUP = 1, RIGTH = 2, RIGTHDOWN = 3, DOWN =4, DOWNLEFT = 5, LEFT = 6, LEFTUP = 7;
	static final double PI_180 = Math.PI / 180.0;

	public static VertexSuper[][] getGraphWithSuperNodesTmp(char[][] board) {
		counter = 0;
		int nCol = board.length, nRow = board[0].length;

		VertexSuper[][] vs = buildInitialSuperGraph(board);

		boolean fAllDone;
		do {
			fAllDone = true;
			for(int iRow = 0; iRow < nRow; iRow++) {
				for(int iCol = 0; iCol < nCol; iCol++) {
					VertexSuper v = vs[iCol][iRow];
					while(v.vSuper != null) v = v.vSuper;
					//					if (v.getSuperNode() == null) {
					if (v.edgeTo.size() <= 2 && v.edgeTo.size() >= 1) {
						for (int i = 0; i < v.edgeTo.size(); i++) {
							VertexSuper vAdd = v.edgeTo.get(i);
							if (vAdd.edgeTo.size() <= 2 && vAdd.edgeTo.size() >= 1) {
								createSuperNode(v, vAdd);
								fAllDone = false;
								break;
							}
						}
					}
				}
			}
		} while (!fAllDone);
		System.out.println("SuperNode Done!");
		return vs;
	}

	public static VertexSuper[][] buildInitialSuperGraph(char[][] board) {
		int nCol = board.length, nRow = board[0].length;

		VertexSuper[][] vs = new VertexSuper[nCol][nRow];

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				vs[iCol][iRow] = new VertexSuper(iCol, iRow);
				vs[iCol][iRow].item = board[iCol][iRow];
			}
		}

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				if(iCol + 1 < nCol)
					if (board[iCol][iRow] == board[iCol + 1][iRow]) {
						vs[iCol][iRow].edgeToSimple[RIGTH] = vs[iCol + 1][iRow]; vs[iCol + 1][iRow].edgeToSimple[LEFT] = vs[iCol][iRow];
					}
				if(iCol > 0 && iRow + 1 < nRow)
					if (board[iCol][iRow] == board[iCol - 1][iRow + 1]) {
						vs[iCol][iRow].edgeToSimple[DOWNLEFT] = vs[iCol - 1][iRow + 1];	vs[iCol - 1][iRow + 1].edgeToSimple[RIGTHUP] = vs[iCol][iRow];
					}
				if(iRow + 1 < nRow)
					if (board[iCol][iRow] == board[iCol][iRow + 1]) {
						vs[iCol][iRow].edgeToSimple[DOWN] = vs[iCol][iRow + 1];	vs[iCol][iRow + 1].edgeToSimple[UP] = vs[iCol][iRow];
					}
				if(iCol + 1 < nCol && iRow + 1 < nRow) 
					if (board[iCol][iRow] == board[iCol + 1][iRow + 1]) {
						vs[iCol][iRow].edgeToSimple[RIGTHDOWN] = vs[iCol + 1][iRow + 1]; vs[iCol + 1][iRow + 1].edgeToSimple[LEFTUP] = vs[iCol][iRow];
					}
			}
		}
		for(int iRow = 0; iRow < nRow; iRow++) 
			for(int iCol = 0; iCol < nCol; iCol++)
				vs[iCol][iRow].updateSuperEdge();

		return vs;
	}

	private static int counter;

	private static void createSuperNode(VertexSuper v, VertexSuper vAdd) {
		System.out.println("Counter: " + (counter++));
		int valueTotalSuper = v.value + vAdd.value;
		double xSuper = (v.xPos * v.value + vAdd.xPos * vAdd.value) / valueTotalSuper;
		double ySuper = (v.yPos * v.value + vAdd.yPos * vAdd.value) / valueTotalSuper;
		double zSuper = Math.max(v.zPos, vAdd.zPos) + 1.0;
		VertexSuper vSuper = new VertexSuper(xSuper , ySuper);
		vSuper.addAbsorbedEdge(v); vSuper.addAbsorbedEdge(vAdd);
		vSuper.value = valueTotalSuper;
		vSuper.item = v.item;
		vSuper.zPos = zSuper;
		v.setSuperNode(vSuper); vAdd.setSuperNode(vSuper);

		VertexSuper vCopy = v.getCopyOfVertex();
		for (VertexSuper vOut : vCopy.edgeTo) {
			if (vOut != vAdd) {
				vSuper.addEdge(vOut);
				v.removeEdge(vOut); //v.removeEdge(vOut);
				v.addEdge(vSuper);//v.addEdge(vSuper);

				VertexSuper vOutCopy = vOut.getCopyOfVertex();

				for (int iOutOut = 0; iOutOut < vOutCopy.edgeTo.size(); iOutOut++) {
					//					if (vOut.edgeTo.get(iOutOut) == v || vOut.edgeTo.get(iOutOut) == vAdd) {
					vOut.removeEdge(v); vOut.removeEdge(vAdd); vOut.addEdge(vSuper);
					//						vOut.edgeTo.set(iOutOut, vSuper);
					System.out.println(iOutOut + ": " +xSuper + "," + ySuper);
					//					}
				}
			}
		}
		VertexSuper vAddCopy = vAdd.getCopyOfVertex();


		for (VertexSuper vAddOut : vAddCopy.edgeTo) {
			if (vAddOut != v) {
				vSuper.addEdge(vAddOut);
				v.removeEdge(vAddOut);
				v.addEdge(vSuper);

				VertexSuper vAddOutCopy = vAddOut.getCopyOfVertex();

				for (int iAddOutOut = 0; iAddOutOut < vAddOutCopy.edgeTo.size(); iAddOutOut++) {
					//					if (vAddOut.edgeTo.get(iAddOutOut) == vAdd || vAddOut.edgeTo.get(iAddOutOut) == v) {
					//						vAddOut.edgeTo.set(iAddOutOut, vSuper);
					vAddOut.removeEdge(v); vAddOut.removeEdge(vAdd); vAddOut.addEdge(vSuper);
					//					
					//					}
				}
			}
		}
	}


	public static void draw3d(VertexSuper[][] Graph) {

		VertexSuper[][] G = Graph;

		//		String[] txtImgGray = new String[] {"AppleGrayBox.png", "AppleDarkGrayBox.png", "ChestnutGrayBox.png", "ChestnutDarkGrayBox.png", "BlueBerryGrayBox.png", "BlueBerryDarkGrayBox.png", "AcornGrayBox.png", "AcornDarkGrayBox.png"};
		//		Image[] imgs = new Image[4];
		//		Image[] imgsGray = new Image[txtImgGray.length];
		//			try {
		//			imgs[0] = new Image("images/Apple.png"); imgs[1] = new Image("images/BlueBerry.png"); imgs[2] = new Image("images/Chestnut.png"); imgs[3] = new Image("images/Acorn.png");
		//			for(int i = 0; i < txtImgGray.length; i++) imgsGray[i] = new Image("images/" + txtImgGray[i]);
		//		} catch(Exception e) { System.out.println("Error Exception: " + e); }

		PhongMaterial matApple = new PhongMaterial();
		PhongMaterial matChestnut = new PhongMaterial();
		PhongMaterial matBlueberry = new PhongMaterial();
		PhongMaterial matAcorn = new PhongMaterial();
		PhongMaterial mat = new PhongMaterial();

		Color cApple = Color.RED; //new Color(0xBC, 0x18, 0x15, 0xFF);
		Color cChestnut = Color.GREEN; // new Color(0xB4, 0x61, 0xC5, 0xAF);
		Color cBlueberry = Color.BLUE; // new Color(0x40, 0x61, 0xC5, 0xAF);
		Color cAcorn = Color.BROWN; // new Color(0x7F, 0x53, 0x33, 0xAF);

		//		matApple.setDiffuseMap(imgsGray[0]);
		matApple.setDiffuseColor(cApple);
		matApple.setSpecularColor(cApple.brighter());
		matChestnut.setDiffuseColor(cChestnut);
		matChestnut.setSpecularColor(cChestnut.brighter());
		matBlueberry.setDiffuseColor(cBlueberry);
		matBlueberry.setSpecularColor(cBlueberry.brighter());
		matAcorn.setDiffuseColor(cAcorn);
		matAcorn.setSpecularColor(cAcorn.brighter());

		PhongMaterial[] matCyls = new PhongMaterial[]{matApple, matChestnut, matBlueberry, matAcorn};
		//		Rotate rotX90 = new Rotate(90, 0, 0, 0, Rotate.X_AXIS);


		char[][] board = Board.getBoard();
		int nCol = Board.nCol, nRow = Board.nRow;

		Group grSuper = Main.grSuper;
		grSuper.getChildren().clear();

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSuper v = G[iCol][iRow];
				v.fDrawn = false;
				while(v.vSuper != null) {
					v = v.vSuper;
					v.fDrawn = false;
				}
			}
		}

		double xMid = nCol / 2.0, yMid = nRow / 2.0;
		double xPos1, yPos1, zPos1, xPos2, yPos2, zPos2, xPosMid, yPosMid, zPosMid;
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSuper v = G[iCol][iRow];
				while(v.vSuper != null) {
//					VertexSuper vLast = v;
					v = v.vSuper;
					if (v.fDrawn) continue; //Perhaps => Break;

					v.fDrawn = true;

//					xPos1 = vLast.xPos; yPos1 = vLast.yPos;
					xPos2 = v.xPos; yPos2 = v.yPos; zPos2 = v.zPos;

//					xPosMid = (xPos2 - xPos1) / 2.0; yPosMid = (yPos2 - yPos1) / 2.0;

					Sphere sphere = new Sphere(0.4);
					sphere.setTranslateX(xPos2 - xMid + 0.5);
					sphere.setTranslateZ(yMid - yPos2 - 0.5);
					sphere.setTranslateY(-zPos2); 
					//					sphere.setRotationAxis(Rotate.Z_AXIS);
					//					sphere.setRotate(90);
					mat = matCyls[(board[iCol][iRow] - 'A') % matCyls.length];
					sphere.setMaterial(mat);
					grSuper.getChildren().add(sphere);

					ArrayList<VertexSuper> vCollapsed = v.vCollapsed;
					for(VertexSuper vDown : vCollapsed) {
						xPos1 = vDown.xPos; yPos1 = vDown.yPos; zPos1 = vDown.zPos;
						xPosMid = (xPos2 + xPos1) / 2.0; yPosMid = (yPos2 + yPos1) / 2.0; zPosMid = (zPos2 + zPos1) / 2.0;
						double dx = xPos2 - xPos1, dy = yPos2 - yPos1, dz = zPos2 - zPos1;
						Cylinder cyl = new Cylinder(0.1, Math.sqrt(dx * dx + dy * dy + dz * dz) + .1);
						cyl.setTranslateX(xPosMid - xMid + 0.5); cyl.setTranslateY(-zPosMid); cyl.setTranslateZ(yMid - yPosMid - 0.5);
						
						double yRot = 0;
						if (Math.abs(dx) < 1E-6) {
							if (dy < 0 ) yRot = 180; else yRot = 0;
						} else {
							yRot = Math.atan(dy / dx) / PI_180 - 90; 
							if (dx < 0) yRot += 180; //??
						}
						System.out.println("yRot = " + yRot);
						
						double lBase = Math.sqrt(dx * dx + dy * dy);
						double xRot = (lBase == 0) ? 0 : 90 - Math.atan(dz / lBase) / PI_180;
						
						cyl.getTransforms().addAll(new Rotate(yRot, Rotate.Y_AXIS), new Rotate(xRot, Rotate.X_AXIS));

						cyl.setMaterial(mat);
						grSuper.getChildren().add(cyl);
					}

					

				}
			}
		}
	}
}
