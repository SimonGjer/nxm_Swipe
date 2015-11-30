import java.io.File;
import java.io.InputStream;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent> {

	Label lblText;
	Button btnClick;
	Button button, button2, button3;

	private Timeline timeline;
	private AnimationTimer timer;

	Random random = new Random();

	;;;public static Stage thisWindow;

	public static void main(String[] args) {
		myLaunch(args);
		//		System.out.println("Start");
		//		launch(args);
		//		System.out.println("After: launch(args);");
	}

	public static void myLaunch(String[] args) {
		System.out.println("Start");
		launch(args);
		System.out.println("After: launch(args);");
	}

	;;;public static Box thisBox;

	@Override
	public void start(Stage window) throws Exception {

		;;; System.out.println(System.getProperty("java.class.path"));
		;;; System.out.println(this.getClass());

		window.setTitle("nxn Swipe");
		BorderPane borderPane = new BorderPane();

		MenuBar menuBar = MenuTopBar.createMenuBar();
		VBox leftPanel = LeftButtonPanel.createPanel();

		Image[] imgs = new Image[4];
		Image[] imgsGray = new Image[8];
		String[] txtImgGray = new String[]{"AppleGrayBox.png", "AppleDarkGrayBox.png", "ChestnutGrayBox.png", "ChestnutDarkGrayBox.png", "BlueBerryGrayBox.png", "BlueBerryDarkGrayBox.png", "AcornGrayBox.png", "AcornDarkGrayBox.png"};
		Image imgGray = null, imgDarkGray = null;
		try {
			//			imgFlower = new Image("http://sample.com/res/flower.png");
			//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			//			InputStream input = classLoader.getResourceAsStream("images/Acorn.png");
			imgs[0] = new Image("images/Apple.png");
			imgs[1] = new Image("images/BlueBerry.png");
			imgs[2] = new Image("images/Chestnut.png");
			imgs[3] = new Image("images/Acorn.png");
			for(int i=0; i < txtImgGray.length; i++) { imgsGray[i] = new Image("images/" + txtImgGray[i]); }
			imgGray = new Image("images/GrayBox.png");
			imgDarkGray = new Image("images/DarkGrayBox.png");
		} catch(Exception e) { System.out.println("Error Exception: " + e); }

		PhongMaterial[] matItems = new PhongMaterial[imgsGray.length];
		for(int i = 0; i < matItems.length; i++) {
			matItems[i] = new PhongMaterial();
			matItems[i].setDiffuseMap(imgsGray[i]);
		}


		PhongMaterial matRed = new PhongMaterial(Color.RED);

		//		Sphere testSphrere = new Sphere(1);
		//		PhongMaterial acornMaterial = new PhongMaterial();
		//		acornMaterial.setDiffuseMap( imgs[0] ); // earthMaterial.setBumpMap( img ); earthMaterial.setSpecularMap( img );
		//		testSphrere.setMaterial( acornMaterial );

		char[][] board = Board.newRndBoard();

		int nCol = Board.nCol, nRow = Board.nRow;
		double xMid = nCol / 2.0, yMid = nRow / 2.0;

		PhongMaterial matGray = new PhongMaterial(Color.GRAY);
		PhongMaterial matDarkGray = new PhongMaterial(Color.DARKGRAY);

		;;;System.out.println("GRAY " + (int) (255 - Color.GRAY.getBlue() * 256));
		;;;System.out.println("DARKGRAY " + (int) (255 - Color.DARKGRAY.getBlue() * 256));

		Box boxGrey, boxPic;
		Box[][] boxsGrey = new Box[nCol][nRow];
		Box[][] boxsPic = new Box[nCol][nRow];
		for (int iRow = 0; iRow < nRow; iRow++) {
			for (int iCol = 0; iCol < nCol; iCol++) {
				boxGrey = new Box(1.0 ,0.1, 1.0);
				boxGrey.setTranslateX(iCol - xMid);
				boxGrey.setTranslateY(-0.10);
				boxGrey.setTranslateZ(iRow - yMid);
				boxGrey.setMaterial(((iCol & 1) == (iRow & 1)) ? matDarkGray : matGray);
				boxsGrey[iCol][iRow] = boxGrey;

				boxPic = new Box(1.0 ,0.0, 1.0);
				boxPic.setTranslateX(iCol - xMid);
				boxPic.setTranslateY(0.0);
				boxPic.setTranslateZ(iRow - yMid);
				//				ImageView imgViewBot = new ImageView(), imgViewTop = new ImageView();

				//				int v = board[iCol][iRow] % 4;
				//				imgViewBot.setImage(((iCol & 1) == (iRow & 1)) ? imgsGray : imgsGray);
				//				imgViewTop.setImage(imgs[board[iCol][iRow] % 4]);
				//				imgViewTop.setBlendMode(BlendMode.OVERLAY);

				//				Group blend = new Group(imgViewBot, imgViewTop);

				//				boxPic.addAll(imgViewBot, imgViewTop);


				int i = (board[iCol][iRow] % 4) * 2 + (((iCol & 1) == (iRow & 1)) ? 0 : 1);
				
				;;;System.out.println("i " + i);
				try { boxPic.setMaterial(matItems[i]); } catch(Exception e) { ;;;System.out.println(e);  }
				
				if (random.nextInt(10) >= 7)  boxPic.setMaterial(matRed);
				
				boxsPic[iCol][iRow] = boxPic;
			}
		}

		//		Box boxBoard = new Box(Board.nCol, 0.0, Board.nRow);
		//		boxBoard.setMaterial(acornMaterial);

		Box testBox = new Box(2, 2, 2);
		System.out.println("getDrawMode: " + testBox.getDrawMode());
		testBox.setMaterial(matRed);
		testBox.setDrawMode(DrawMode.FILL); //LINE

		//		Color.rgb(156,216,255)

		thisBox = testBox;

		ImageView imgBoard = new ImageView();
		//		Text text = new Text("This is a test");
		//		 text.setX(3);
		//		 text.setY(3);
		//		 text.setFont(new Font(20));

		//		Canvas canvas = new Canvas(400,400);

		// Create and position camera
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.getTransforms().addAll(new Rotate(-20, Rotate.Y_AXIS), new Rotate(-20, Rotate.X_AXIS), new Translate(0, 0, -15 - random.nextInt(10)));
		Group root = new Group();
		root.getChildren().add(camera);
		root.getChildren().addAll(testBox);

		for (int iRow = 0; iRow < nRow; iRow++) {
			for (int iCol = 0; iCol < nCol; iCol++) {
				root.getChildren().addAll(/*boxsGrey[iCol][iRow],*/ boxsPic[iCol][iRow]);
			}
		}


		SubScene subScene = new SubScene(root, 900, 750, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.WHITE);
		subScene.setCamera(camera);
		Group group = new Group();
		group.getChildren().add(subScene);





		borderPane.setTop(menuBar);
		borderPane.setLeft(leftPanel);
		borderPane.setCenter(group);


		Scene scene = new Scene(borderPane, 1000, 800);
		System.out.println("subScene.isDepthBuffer(): " + subScene.isDepthBuffer());
		System.out.println("scene.isDepthBuffer(): " + scene.isDepthBuffer());
		window.setScene(scene);


		timer = new AnimationTimer() { //https://docs.oracle.com/javafx/2/animations/basics.htm#CJAFADFJ
			@Override
			public void handle(long l) {
				if (LeftButtonPanel.fFtn1) {
					//					thisBox.setTranslateX(random.nextInt(5) + 2);
					//					System.out.println("camera.getRotationAxis() = " + camera.getRotationAxis());
					camera.setRotationAxis(new Point3D(0.0, 1.0, 0.0));
					camera.setRotate(System.currentTimeMillis() / 10.0);
					thisBox.setRotate(System.currentTimeMillis() / 100.0);
				}
			}
		};


		thisWindow = window;

		window.show();

		timer.start(); 

		;;;System.out.println("After: window.show();");
		//		if (false) { winAbout.popUp(); }
		//		if (false) {//Test
		//			lblText = new Label("Some text");
		//			btnClick = new Button("Click it");
		//			button = new Button();
		//			button.setText("Click me too");
		//			button.setOnAction(this);
		//			button2 = new Button("Button 2");
		//			button2.setOnAction(this);
		//			button3 = new Button("Button 3");
		//			button3.setOnAction(e -> System.out.println("Wow !!!"));
		//
		//			btnClick.setOnAction(new EventHandler<ActionEvent>() {
		//				@Override
		//				public void handle(ActionEvent e) {
		//					lblText.setText("New Text");
		//				}
		//			});
		//
		//			VBox layout = new VBox(10);
		//			layout.getChildren().addAll(lblText, btnClick, button, button2, button3); // root.getChildren().add(btnClick);
		//
		//			layout.setAlignment(Pos.CENTER);
		//
		//			Scene scene = new Scene(layout, 300, 250);
		//
		//			window.setScene(scene);
		//
		//			window.setTitle("Cow movie");
		//
		//			window.setOnCloseRequest(e -> {
		//				e.consume();
		//				System.out.println("Øv Bøv - Farvel");
		//				window.close();
		//			} );
		//			
		//			window.show();
		//		} 

	}

	@Override
	public void handle(ActionEvent e) {
		lblText.setText("You clicked me!");
		if (e.getSource() == button) System.out.println("Button pressed");
		if (e.getSource() == button2) System.out.println("Button 2 pressed");
	}

}
