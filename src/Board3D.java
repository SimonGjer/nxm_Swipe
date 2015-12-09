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

	public static Group getBoard(char[][] board) {

		Image[] imgs = new Image[4];
		Image[] imgsGray = new Image[8];

		String[] txtImgGray = new String[] {"AppleGrayBox.png", "AppleDarkGrayBox.png", "ChestnutGrayBox.png", "ChestnutDarkGrayBox.png", "BlueBerryGrayBox.png", "BlueBerryDarkGrayBox.png", "AcornGrayBox.png", "AcornDarkGrayBox.png"};
		try {
			//			imgFlower = new Image("http://sample.com/res/flower.png");
			//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			//			InputStream input = classLoader.getResourceAsStream("images/Acorn.png");
			imgs[0] = new Image("images/Apple.png");
			imgs[1] = new Image("images/BlueBerry.png");
			imgs[2] = new Image("images/Chestnut.png");
			imgs[3] = new Image("images/Acorn.png");
			for(int i = 0; i < txtImgGray.length; i++) 
				imgsGray[i] = new Image("images/" + txtImgGray[i]);

		} catch(Exception e) { System.out.println("Error Exception: " + e); }

		matItems = new PhongMaterial[imgsGray.length];
		for(int i = 0; i < matItems.length; i++) {
			matItems[i] = new PhongMaterial();
			matItems[i].setDiffuseMap(imgsGray[i]);
		}


		PhongMaterial matRed = new PhongMaterial(Color.RED);

		//		char[][] board = Board.getBoard();

		int nCol = Board.nCol, nRow = Board.nRow;
		double xMid = nCol / 2.0, yMid = nRow / 2.0;

		PhongMaterial matGray = new PhongMaterial(Color.GRAY);
		PhongMaterial matDarkGray = new PhongMaterial(Color.DARKGRAY);
		;;;PhongMaterial matGreen = new PhongMaterial(Color.GREEN);
		PhongMaterial matWhite = new PhongMaterial(Color.WHITE);

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
				try { boxPic.setMaterial(matItems[i]); } catch(Exception e) { System.err.println("Error: " + e);  }
				boxPics[iCol][iRow] = boxPic;
			}
		}

		;;;Box testBox = new Box(.5, .9, .5);
		;;;testBox.setMaterial(matRed);
		;;;testBox.setDrawMode(DrawMode.FILL); //LINE
		;;;testBox.setTranslateX(nCol / 2.0 + 0.5);
		;;;testBox.setTranslateZ(nRow / 2.0 + 0.5);

		//		Sphere testSphere = new Sphere(5);
//		Cylinder testCyl = new Cylinder(0.1, 1); //Radius, length
//
//		Image imgCyl = new Image("images/Swipe_0_80.png");
//		imgCyl = new Image("images/Apple.png");
//
//		PhongMaterial matCyl = new PhongMaterial();
//		matCyl.setDiffuseColor(Color.WHITESMOKE);
//		matCyl.setSpecularColor(Color.WHITE);
//
//		testCyl.setMaterial(matCyl);
//		//		testCyl.setBlendMode(BlendMode.MULTIPLY);
//		testCyl.setTranslateY(-0.3);
//		testCyl.setRotationAxis(Rotate.X_AXIS);
//		testCyl.setRotate(90);
//		//		testCyl.setOpacity(0.3);
//
//
//		//		AmbientLight light = new AmbientLight(Color.RED);
//		//		light.getScope().addAll(testCyl, boxsPic[1][1]);
//		Group cylGr = new Group();
//		cylGr.getChildren().add(testCyl);
//		//		
//		cylGr.setRotationAxis(Rotate.Y_AXIS);
//		cylGr.setRotate(45);
//		//				cylGr.setOpacity(.2);
//
//		//cylGr.getChildren().add(light);
//
//		testCyl.setId("Test Cylinder");
//
//		cylGr.setOnMouseEntered(e -> {
//			System.out.println("***");
//			Node entered = e.getPickResult().getIntersectedNode();
//			if (entered != null) {
//				entered.setScaleY(Math.sqrt(2));
//			}
//		});



		Rotate rotX90 = new Rotate(90, 0, 0, 0, Rotate.X_AXIS);
		Rotate rotZ30 = new Rotate(30, 0, 0, 0, Rotate.Z_AXIS);

//		Cylinder testCyl2 = new Cylinder(0.1, 1);
//		testCyl2.setMaterial(matCyl);
//		testCyl2.setTranslateX(2);
//		testCyl2.getTransforms().addAll(rotX90, rotZ30);


		Box boxBoard = new Box(nCol + 0.2 , 0.5, nRow + 0.2);
		boxBoard.setMaterial(new PhongMaterial(Color.BLACK));
		boxBoard.setTranslateY(0.25);

		//		Color.rgb(156,216,255)
		;;;Main.thisBox = thisBox = testBox;
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

		// Create and position camera
		//		PerspectiveCamera camera = new PerspectiveCamera(true);
		//		camera.getTransforms().addAll(new Rotate(0, Rotate.Y_AXIS), new Rotate(-90, Rotate.X_AXIS), new Translate(0, -0, -15));
		board3d = new Group();

		board3d.getChildren().addAll(boxBoard, testBox); //, cylGr, testCyl2);

		for (int iRow = 0; iRow < nRow; iRow++) {
			for (int iCol = 0; iCol < nCol; iCol++) {
				board3d.getChildren().addAll(/*boxsGrey[iCol][iRow],*/ boxPics[iCol][iRow]);
			}
		}


		return board3d;
	}

	public static void update() {
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
	}
}
