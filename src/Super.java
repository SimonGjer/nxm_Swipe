//import java.awt.Color;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;


public class Super {

	static final int UP = 0, RIGTHUP = 1, RIGTH = 2, RIGTHDOWN = 3, DOWN =4, DOWNLEFT = 5, LEFT = 6, LEFTUP = 7;
	static final double PI_180 = Math.PI / 180.0;

	public static VertexSuper[][] sg; // sg == superGraph
	private static int nCol, nRow;
	private static double zMax;

	public static VertexSuper[][] getGraphWithSuperNodesTmp(char[][] board) {
		nCol = board.length; nRow = board[0].length;
		sg = buildInitialSuperGraph(board);
		;;;counter = 0;

		boolean fAllDone;
		do {
			fAllDone = true;
			if(rule1()) fAllDone = false;
			if(rule2()) fAllDone = false;
			if(rule3()) fAllDone = false;
		} while (!fAllDone);
		;;;System.out.println("SuperNode Done!");
		return sg;
	}


	public static boolean rule1() {
		int rule = 1;
		;System.out.println("Rule " + rule + " called");
		boolean fDone, fChange = false;
		do {
			fDone = true;
			for(int iRow = 0; iRow < nRow; iRow++) {
				for(int iCol = 0; iCol < nCol; iCol++) {
					VertexSuper v = sg[iCol][iRow];
					while(v.vSuper != null) v = v.vSuper;
					if (v.edgeTo.size() <= 2 && v.edgeTo.size() >= 1) {
						for (int i = 0; i < v.edgeTo.size(); i++) {
							VertexSuper vAdd = v.edgeTo.get(i);
							if (vAdd.edgeTo.size() <= 2 && vAdd.edgeTo.size() >= 1) {
								createSuperNode(v, vAdd, rule);
								fDone = false; fChange = true;
								break;
							}
						}
					}
				}
			}
		} while (!fDone);
		return fChange;
	}

	public static boolean rule2() {
		int rule = 2;
		;System.out.println("Rule " + rule + " called");
		boolean fDone, fChange = false;
		do {
			fDone = true;
			for(int iRow = 0; iRow < nRow; iRow++) {
				for(int iCol = 0; iCol < nCol; iCol++) {
					VertexSuper v = sg[iCol][iRow];
					while(v.vSuper != null) v = v.vSuper;

					ArrayList<VertexSuper[]> ts = getTriangles(v);

					for(VertexSuper[] t : ts) {
						if (t[0].edgeTo.size() <= 3 && t[1].edgeTo.size() <= 3 && t[2].edgeTo.size() <= 3) {
							createSuperNode(t, rule); break;
						}
					}
				}
			}
		} while (!fDone);
		return fChange;
	}

	public static boolean rule3() {
		int rule = 3;
		;System.out.println("Rule " + rule + " called");
		boolean fDone, fChange = false;
		do {
			fDone = true;
			for(int iRow = 0; iRow < nRow; iRow++) {
				for(int iCol = 0; iCol < nCol; iCol++) {
					VertexSuper v = sg[iCol][iRow];
					while(v.vSuper != null) v = v.vSuper; // go to surface

					System.out.println();
					ArrayList<VertexSuper[]> k4s = getK4s(v);
					System.out.println("k4s.size(): " + k4s.size());
					//Find K4's with most 1 lonely-vertex for each corner in K4. A lonely-vertex is a vertex that can't be reached from another edge in K4.  
					ArrayList<VertexSuper[]> k4sOK = new ArrayList<>();
					for(VertexSuper[] k4 : k4s) {
						boolean fMax1LonelyEdge = true;
						for(VertexSuper vCorner : k4) {
							int nLonely = 0;
							for(VertexSuper vOut : vCorner.edgeTo) {

								boolean fTriangle = false;
								for(VertexSuper vOutOut : vOut.edgeTo) {
									if (vOutOut == vCorner) continue;
									for (VertexSuper vCornerCheck : k4) {
										if (vOutOut == vCornerCheck) {
											fTriangle = true; break;
										}
									}
								}
								if (!fTriangle) nLonely++;
							}
							if (nLonely > 1) { fMax1LonelyEdge = false; break; }
						}
						if (fMax1LonelyEdge) k4sOK.add(k4);
					}
					
					if (k4sOK.size() >= 1) {
						VertexSuper[] k4OK = k4sOK.get(0);
						createSuperNode(k4OK, rule);
						fChange = true;
					}
					
				}
			}
		} while (!fDone);
		return fChange;
	}


	public static ArrayList<VertexSuper[]> getTriangles(VertexSuper v) {
		ArrayList<VertexSuper[]> triangles = new ArrayList<>();
		for (VertexSuper v2 : v.edgeTo) {
			for (VertexSuper v3 : v2.edgeTo) {
				if (v3 != v) {
					for (VertexSuper v4 : v3.edgeTo) {
						if (v4 == v) {
							VertexSuper[] triangel = new VertexSuper[3];
							boolean fDup = false;
							//							for (VertexSuper[] t : triangles) if(t[1] == v2) { fDup = true; break; }

							for (VertexSuper[] t : triangles) {
								if ((t[1] == v2 && t[2] == v3) || (t[1] == v3 && t[2] == v2)) { fDup = true; break; }
							}

							if (!fDup) {
								triangel[0] = v; triangel[1] = v2; triangel[2] = v3;
								triangles.add(triangel);
							}
						}
					}
				}
			}
		}
		return triangles;
	}

	public static ArrayList<VertexSuper[]> getK4s(VertexSuper v) {
		ArrayList<VertexSuper[]> k4s = new ArrayList<>(); 
		ArrayList<VertexSuper[]> ts = getTriangles(v);
		VertexSuper tmp;
		for (VertexSuper[] t : ts) {
			ArrayList<VertexSuper> vOuts = new ArrayList<>();
			for (VertexSuper vIn : t) {
				for (VertexSuper vOut : vIn.edgeTo) {
					boolean fOut = true;
					for (VertexSuper vInCheck : t) {
						if (vInCheck == vOut) { fOut = false; break; }
					}
					if (fOut) {
						boolean fExist = false;
						for (VertexSuper vOutCheck : vOuts) if (vOutCheck == vOut) { fExist = true; break; }
						if (!fExist) vOuts.add(vOut);
					}
				}
			}

			for(VertexSuper vOut : vOuts) {
				boolean fAllTriToOut = true;
				for(VertexSuper vIn : t) {
					boolean fTriToOut = false;
					for(VertexSuper vCheck : vOut.edgeTo) {
						if (vCheck == vIn) { fTriToOut = true; break; }
					}
					if (!fTriToOut) fAllTriToOut = false;
				}
				if (fAllTriToOut) {
					VertexSuper[] k4 = new VertexSuper[4];
					k4[0] = t[0]; k4[1] = t[1]; k4[2] = t[2]; k4[3] = vOut;

					for(int i = 0; i < k4.length - 1; i++) { //Sort vertices in K4 (makes it easier to exclude duplicates)
						for(int j = i + 1; j < k4.length; j++) {
							if (k4[i].id >  k4[j].id) {
								tmp = k4[i]; k4[i] = k4[j]; k4[j] = tmp;
							}
						}
					}
					boolean fDup = false;
					for (VertexSuper[] k4si : k4s) {
						boolean fEqual = true;
						for(int i = 0; i < k4.length; i++) {
							if (k4[i] != k4si[i]) { fEqual = false; break; }
						}
						if (fEqual) { fDup = true; break; }
					}
					if (!fDup) k4s.add(k4);
				}
			}
		}
		return k4s;
	}


	public static VertexSuper[][] buildInitialSuperGraph(char[][] board) {
		nCol = board.length; nRow = board[0].length;
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

	private static void createSuperNode(VertexSuper v, VertexSuper vAdd, int rule) {
		int valueSuper = v.value + vAdd.value;
		double xSuper = (v.xPos * v.value + vAdd.xPos * vAdd.value) / valueSuper;
		double ySuper = (v.yPos * v.value + vAdd.yPos * vAdd.value) / valueSuper;
		double zSuper = Math.max(v.zPos, vAdd.zPos) + 1.0;
		VertexSuper vSuper = new VertexSuper(xSuper , ySuper);
		vSuper.addAbsorbedEdge(v); vSuper.addAbsorbedEdge(vAdd);
		vSuper.value = valueSuper;
		vSuper.item = v.item;
		vSuper.zPos = zSuper;
		vSuper.sRuleUsed = rule;
		v.setSuperNode(vSuper); vAdd.setSuperNode(vSuper);

		VertexSuper vCopy = v.getCopyOfVertex();
		for (VertexSuper vOut : vCopy.edgeTo) {
			if (vOut != vAdd) {
				vSuper.addEdge(vOut);
				v.removeEdge(vOut);
				v.addEdge(vSuper);
				vOut.removeEdge(v); vOut.removeEdge(vAdd); vOut.addEdge(vSuper);
			}
		}
		VertexSuper vAddCopy = vAdd.getCopyOfVertex();

		for (VertexSuper vAddOut : vAddCopy.edgeTo) {
			if (vAddOut != v) {
				vSuper.addEdge(vAddOut);
				v.removeEdge(vAddOut);
				v.addEdge(vSuper);
				vAddOut.removeEdge(v); vAddOut.removeEdge(vAdd); vAddOut.addEdge(vSuper);
			}
		}
	}

	private static int counter = 0;

	private static void createSuperNode(VertexSuper[] vs, int rule) {
		System.out.println("vs-SuperNode created: " + ++counter);

		int valueSuper = 0;
		double xSuper = 0, ySuper = 0, zSuper = 0;
		for (VertexSuper v : vs) {
			valueSuper += v.value;
			xSuper += v.xPos * v.value;	ySuper += v.yPos * v.value;
			if (zSuper < v.zPos) zSuper = v.zPos;
		}
		xSuper /= valueSuper; ySuper /= valueSuper;
		zSuper += 1.0;

		VertexSuper vSuper = new VertexSuper(xSuper , ySuper);
		for (VertexSuper v : vs)
		{ vSuper.addAbsorbedEdge(v); v.setSuperNode(vSuper); }
		vSuper.value = valueSuper;
		vSuper.item = vs[0].item;
		vSuper.zPos = zSuper;
		vSuper.sRuleUsed = rule;

		for (VertexSuper v : vs) {
			VertexSuper vCopy = v.getCopyOfVertex();
			for (VertexSuper vOut : vCopy.edgeTo) {
				v.addEdge(vSuper);
				boolean fEdgeIntern = false;
				for (VertexSuper vCk : vs) if (vCk == vOut) fEdgeIntern = true;
				if (!fEdgeIntern) {
					vSuper.addEdge(vOut);
					v.removeEdge(vOut);
					for (VertexSuper vRm : vs) { vOut.removeEdge(vRm); }
					vOut.addEdge(vSuper);
				}
			}
		}
	}

	private static ArrayList<VertexSuper> vSurfaces;
	private static HashMap<VertexSuper, Object> hmVisit;

	public static ArrayList<VertexSuper> getSurface(VertexSuper[][] Graph) {
		VertexSuper[][] G = Graph;
		vSurfaces = new ArrayList<VertexSuper>();
		hmVisit = new HashMap<VertexSuper, Object>();

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSuper v = G[iCol][iRow];
				while(v.vSuper != null) v = v.vSuper; //Go to surface
				if (!hmVisit.containsKey(v)) buildSurface(v);
			}
		}

		return vSurfaces;
	}
	public static ArrayList<VertexSuper> buildSurface(VertexSuper v) {
		vSurfaces.add(v);
		hmVisit.put(v, null);
		for(VertexSuper vv : v.edgeTo)
			if (!hmVisit.containsKey(vv)) buildSurface(vv);

		return vSurfaces;
	}

	public static void swimCoincidingVS(VertexSuper[][] Graph) {
		resetUsedVS(Graph);
		ArrayList<VertexSuper> lGraph = new ArrayList<VertexSuper>();
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSuper v = Graph[iCol][iRow];
				while(v.vSuper != null) {
					v = v.vSuper;
					if(!v.fUsed) {
						lGraph.add(v); v.fUsed = true;
					}
				}
			}
		}
		double dx, dy, dz;
		boolean fDone = true;
		while (fDone) {
			fDone = false;
			for(int i = 0; i < lGraph.size(); i++) {
				VertexSuper v1 = lGraph.get(i);
				if (zMax < v1.zPos) zMax = v1.zPos;
				for(int j = i + 1; j < lGraph.size(); j++) {
					VertexSuper v2 = lGraph.get(j);
					if(Math.abs(dx = (v1.xPos - v2.xPos)) < 0.5) {
						if(Math.abs(dy = (v1.yPos - v2.yPos)) < 0.5) {
							if(Math.abs(dz = (v1.zPos - v2.zPos)) < 0.5) {
								if (Math.sqrt(dx * dx + dy * dy + dz * dz) < 0.5) { v2.zPos += 1.0; fDone = false; }
							}
						}
					}
				}
			}
		}
	}

	public static void resetUsedVS(VertexSuper[][] Graph) {
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSuper v = Graph[iCol][iRow];
				v.fUsed = false;
				while(v.vSuper != null) { v = v.vSuper; v.fUsed = false; }
			}
		}
	}


	public static void draw3d(VertexSuper[][] Graph) {

		VertexSuper[][] G = Graph;
		zMax = 0;


		swimCoincidingVS(Graph);




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

		char[][] board = Board.getBoard();
		int nCol = Board.nCol, nRow = Board.nRow;

		Group grSuper = Main.grSuper;
		grSuper.getChildren().clear();

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSuper v = G[iCol][iRow];
				v.fUsed = false;
				while(v.vSuper != null) {
					v = v.vSuper;
					v.fUsed = false;
				}
			}
		}

		double xMid = nCol / 2.0, yMid = nRow / 2.0;
		double xPos1, yPos1, zPos1, xPos2, yPos2, zPos2, xPosMid, yPosMid, zPosMid;
		double dx, dy, dz;
		double zMaxSuperPos = 0;
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSuper v = G[iCol][iRow];
				while(v.vSuper != null) {
					v = v.vSuper;
					if (v.fUsed) continue; //Perhaps => Break;

					v.fUsed = true;

					xPos2 = v.xPos; yPos2 = v.yPos; zPos2 = v.zPos;
					if (zPos2 > zMaxSuperPos) zMaxSuperPos = zPos2;

					Shape3D shape3d;
					switch(v.sRuleUsed) {
					case 0: shape3d = new Sphere(0.3); break;
					case 1: shape3d = new Sphere(0.3); break;
					case 2: shape3d = new Cylinder(0.3, 0.6); break;
					case 3: shape3d = new Box(0.6, 0.6, 0.6); break;
					default: shape3d = new Sphere(0.3); break;
					}
					shape3d.setTranslateX(xPos2 - xMid + 0.5);
					shape3d.setTranslateZ(yMid - yPos2 - 0.5);
					shape3d.setTranslateY(-zPos2); 
					mat = matCyls[(board[iCol][iRow] - 'A') % matCyls.length];
					shape3d.setMaterial(mat);
					grSuper.getChildren().add(shape3d);

					ArrayList<VertexSuper> vCollapsed = v.vCollapsed;
					for(VertexSuper vDown : vCollapsed) {
						xPos1 = vDown.xPos; yPos1 = vDown.yPos; zPos1 = vDown.zPos;
						xPosMid = (xPos2 + xPos1) / 2.0; yPosMid = (yPos2 + yPos1) / 2.0; zPosMid = (zPos2 + zPos1) / 2.0;
						dx = xPos2 - xPos1; dy = yPos2 - yPos1; dz = zPos2 - zPos1;
						Cylinder cyl = new Cylinder(0.05, Math.sqrt(dx * dx + dy * dy + dz * dz) + .1);
						cyl.setTranslateX(xPosMid - xMid + 0.5); cyl.setTranslateY(-zPosMid); cyl.setTranslateZ(yMid - yPosMid - 0.5);

						double yRot = 0;
						if (Math.abs(dx) < 1E-6) {
							if (dy < 0 ) yRot = 180; else yRot = 0;
						} else {
							yRot = Math.atan(dy / dx) / PI_180 - 90; 
							if (dx < 0) yRot += 180; //??
						}

						double lBase = Math.sqrt(dx * dx + dy * dy);
						double xRot = (lBase == 0) ? 0 : 90 - Math.atan(dz / lBase) / PI_180;

						cyl.getTransforms().addAll(new Rotate(yRot, Rotate.Y_AXIS), new Rotate(xRot, Rotate.X_AXIS));

						cyl.setMaterial(mat);
						grSuper.getChildren().add(cyl);
					}
				}
			}
		}

		//Draw Surface
		double zSurface = zMaxSuperPos + 2.0;
		if (zMax < zSurface) zMax = zSurface;
		ArrayList<VertexSuper> vSurface = getSurface(Graph);

		for(VertexSuper v: vSurface) v.fUsed = false;

		for (VertexSuper v: vSurface) {
			Sphere sphere = new Sphere(0.3);
			sphere.setTranslateX(v.xPos - xMid + 0.5);
			sphere.setTranslateY(-zSurface);
			sphere.setTranslateZ(yMid - v.yPos - 0.5);

			mat = matCyls[(v.item - 'A') % matCyls.length];
			sphere.setMaterial(mat);
			grSuper.getChildren().add(sphere);

			for (VertexSuper vEdge: v.edgeTo) {
				if (!vEdge.fUsed) {
					xPosMid = (v.xPos + vEdge.xPos) / 2; yPosMid = (v.yPos + vEdge.yPos) / 2;
					dx = v.xPos - vEdge.xPos; dy = v.yPos - vEdge.yPos;
					Cylinder cyl = new Cylinder(0.05, Math.sqrt(dx * dx + dy * dy) );
					cyl.setTranslateX(xPosMid - xMid + 0.5); cyl.setTranslateY(-zSurface); cyl.setTranslateZ(yMid - yPosMid - 0.5);

					double rot = (Math.abs(dx) < 1E-6) ? ((dy < 0) ? 180 : 0) : ((dx < 0) ? Math.atan(dy/dx)/PI_180 - 90 : Math.atan(dy/dx)/PI_180 + 90);
					cyl.getTransforms().addAll(new Rotate(90, Rotate.X_AXIS), new Rotate(-rot, Rotate.Z_AXIS));

					cyl.setMaterial(mat);
					grSuper.getChildren().add(cyl);
				}
			}
			v.fUsed = true;
		}
	}

	public static double getXYZMax() {
		double xyMax = Math.max(Board.nCol, Board.nRow);
		if (!LeftButtonPanel.fBtn_SuperNode) return xyMax;
		double xyzMax = Math.max(xyMax, zMax);
		return xyzMax;
	}

	public static double getZMax() {
		if (!LeftButtonPanel.fBtn_SuperNode) return 1.0;
		return zMax;
	}

}
