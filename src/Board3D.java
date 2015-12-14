import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


public class Board3D {

	;;;public static Box thisBox;
	public static Group board3d;

	private static PhongMaterial[] matItems;
	private static Box[][] boxPics;

	public static Image[] imgs = new Image[4];
	public static Image[] imgsGray = new Image[8];
	public static String[] txtImgGray;
	public static Box boxBoard;
	public static Group grBoard = Main.grBoard;

	public static Group getBoard() {
		txtImgGray = new String[] {"AppleGrayBox.png", "AppleDarkGrayBox.png", "ChestnutGrayBox.png", "ChestnutDarkGrayBox.png", "BlueBerryGrayBox.png", "BlueBerryDarkGrayBox.png", "AcornGrayBox.png", "AcornDarkGrayBox.png"};
		try { //	imgFlower = new Image("http://sample.com/res/flower.png"); //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();//InputStream input = classLoader.getResourceAsStream("images/Acorn.png");
			imgs[0] = new Image("images/Apple.png"); imgs[1] = new Image("images/BlueBerry.png");
			imgs[2] = new Image("images/Chestnut.png");	imgs[3] = new Image("images/Acorn.png");
			for(int i = 0; i < txtImgGray.length; i++) imgsGray[i] = new Image("images/" + txtImgGray[i]);
		} catch(Exception e) { System.out.println("Error Exception: " + e); }

		matItems = new PhongMaterial[imgsGray.length];
		for(int i = 0; i < matItems.length; i++) {
			matItems[i] = new PhongMaterial();
			matItems[i].setDiffuseMap(imgsGray[i]);
		}

		//		int nCol = Board.nCol, nRow = Board.nRow;
		//
		//		PhongMaterial matRed = new PhongMaterial(Color.RED);
		//		PhongMaterial matGray = new PhongMaterial(Color.GRAY); PhongMaterial matDarkGray = new PhongMaterial(Color.DARKGRAY);
		//		;;;PhongMaterial matGreen = new PhongMaterial(Color.GREEN);	PhongMaterial matWhite = new PhongMaterial(Color.WHITE);



		//		;;;testBox = new Box(.5, .9, .5); ;;;testBox.setMaterial(matRed); ;;;testBox.setDrawMode(DrawMode.FILL); //LINE
		//		;;;testBox.setTranslateX(nCol / 2.0 + 0.5); ;;;testBox.setTranslateZ(nRow / 2.0 + 0.5); //;;;Main.thisBox = thisBox = testBox;

		//		Image imgCyl = new Image("images/Swipe_0_80.png");
		//		imgCyl = new Image("images/Apple.png");
		//
		//		//		AmbientLight light = new AmbientLight(Color.RED);
		//		//		light.getScope().addAll(testCyl, boxsPic[1][1]);

		//		cylGr.setOnMouseEntered(e -> {
		//			System.out.println("***");
		//			Node entered = e.getPickResult().getIntersectedNode();
		//			if (entered != null) {
		//				entered.setScaleY(Math.sqrt(2));
		//			}
		//		});



		//		Color.rgb(156,216,255)

		//		Light light = new Light();
		//		AmbientLight light = new AmbientLight();
		//		light.setBlendMode(BlendMode.RED); // ???
		//		light.setTranslateX(10);
		//		light.setTranslateY(-10);
		//		light.setTranslateZ(10);


		//		System.out.println("light.getBlendMode()= " + light.getBlendMode());

		//		ImageView imgBoard = new ImageView();
		//		Text text = new Text("This is a test");
		//		 text.setX(3);
		//		 text.setY(3);
		//		 text.setFont(new Font(20));

		//		Canvas canvas = new Canvas(400,400);

		board3d = new Group();

		update();

		return board3d;
	}

	public static void update() {
		char[][] board = Board.getBoard();
		int nCol = Board.nCol, nRow = Board.nRow;
		double xMid = nCol / 2.0, yMid = nRow / 2.0;

		boxBoard = new Box(nCol + 0.2 , 0.5, nRow + 0.2); //The big black box that every field is placed on
		boxBoard.setMaterial(new PhongMaterial(Color.BLACK));
		boxBoard.setTranslateY(0.25);

		Box boxPic;
		boxPics = new Box[nCol][nRow];
		for (int iRow = 0; iRow < nRow; iRow++) {
			for (int iCol = 0; iCol < nCol; iCol++) {

				boxPic = new Box(0.97 ,0.01 , 0.97);
				boxPic.setTranslateX(iCol - xMid + 0.5);
				boxPic.setTranslateY(0.0);
				boxPic.setTranslateZ(- iRow + yMid - 0.5);
				boxPic.setId("Field " + iCol + ' ' + iRow);

				int i = ((board[iCol][iRow] - 'A') % matItems.length) * 2 + ((((iCol + iRow) & 1) == 0) ? 1 : 0);
				boxPic.setMaterial(matItems[i]); // try { boxPic.setMaterial(matItems[i]); } catch(Exception e) { System.err.println("Error: " + e);  }
				boxPics[iCol][iRow] = boxPic;
			}
		}

		grBoard.getChildren().clear();
		grBoard.getChildren().add(boxBoard); //, cylGr, testCyl2);

		for (int iRow = 0; iRow < nRow; iRow++) {
			for (int iCol = 0; iCol < nCol; iCol++) {
				grBoard.getChildren().add(/*boxsGrey[iCol][iRow],*/ boxPics[iCol][iRow]);
			}
		}
		Camera.update();
	}


	public static void updateGraphics() {
		if (board3d == null) return;
		update(board3d, Board.getBoard());
	}

	public static void update(Group board3d, char[][] board) {
		if (board3d == null) return;
		//		;;;System.out.println("board3d.getChildren().size(): " + board3d.getChildren().size());
		int nCol = Board.nCol, nRow = Board.nRow;
		for (int iRow = 0; iRow < nRow; iRow++) {
			for (int iCol = 0; iCol < nCol; iCol++) {
				int i = ((board[iCol][iRow] - 'A') % 4) * 2 + ((((iCol + iRow) & 1) == 0) ? 1 : 0);
				boxPics[iCol][iRow].setMaterial(matItems[i]);
			}
		}		

		//		for(Node iNode : board3d.getChildren()) {
		//			//			System.out.println(iNode.getId());
		//			String txt = iNode.getId();
		//			if (txt != null && txt.contains("Field")) {}
		//		}
		Camera.update();
	}

	public static void replaceSelectedWith(Group board3d, char ch) {
		char[][] board = Board.getBoard();
		for(Node node : grBoard.getChildren()) {
			String id = node.getId();
			if (id != null) {
				if (id.contains("Field") && id.contains("*")) {
					//					System.out.println("node: " + node);
					String[] s = id.split(" ");
					int x = Integer.parseInt(s[1]);
					int y = Integer.parseInt(s[2]);
					board[x][y] = ch;

					//Deselect nodes
					node.setScaleX(1);
					node.setScaleY(1);
					node.setScaleZ(1);
					node.setId(id.substring(0, id.length() - 2));
				}
			}
		}
		Board3D.update();
		LeftButtonPanel.resetOngoing();
		LeftButtonPanel.doBtn_Actions();
	}
}
