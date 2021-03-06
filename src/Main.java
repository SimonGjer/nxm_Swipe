////////////////////////////////////////////////////////////
//         Longest Path Problem in Cute vs Evil
// 
//         Algorithm Design Project autumn 2015
//
//             Jens Ravn Jensen, jrje@itu.dk
//              Peter Skovgaard, pnic@itu.dk 
//               Simon Gjerl�v, sgjc@itu.dk
//                 Waqas Ali, @itu.dk
////////////////////////////////////////////////////////////

import java.io.File;
import java.io.InputStream;
import java.util.Random;



import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


public class Main extends Application {

	private Timeline timeline;
	private AnimationTimer timer;

	Random random = new Random();

	static final double SQRT2 = Math.sqrt(2);

//	;;;public static Stage thisWindow;

	public static Group grSuper = new Group();
	public static Group grBoard = new Group();

	public static PerspectiveCamera camera;

	public static Group board3d; //Should be renamed to graphics or similar

	public static long tms = System.currentTimeMillis();
	public static long tmsLast = System.currentTimeMillis();
	public static long dT = 0;
	public static char[][] board = Board.getBoard();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		;;; System.out.println(System.getProperty("java.class.path"));
		;;; System.out.println(this.getClass());

		window.setTitle("Longest Path Problem in \'Cute vs Evil\'");

		BorderPane borderPane = new BorderPane();
		MenuBar menuBar = MenuTopBar.createMenuBar();
		VBox leftPanel = LeftButtonPanel.createPanel();
		VBox rightPanel = RightPanel.createPanel();
		HBox bottomPanel = BottomPanel.greateBottomPanel();
		board3d = Board3D.getBoard();

		camera = Camera.createCamera();

		Path.grPathComp.setTranslateY(-0.2);
		Path.grPathBigComp.setTranslateY(-0.4);
		Path.grPathBruteForce.setTranslateY(-0.6);
		Path.grPathRnd.setTranslateY(-0.8);
		Path.grPathSurface.setTranslateY(-5.1234); // "-5.1234" is just a number

		board3d.getChildren().addAll(Path.grPathComp, Path.grPathBigComp, Path.grPathBruteForce, Path.grPathRnd, grSuper, grBoard, Path.grPathSurface);
		SubScene subScene = new SubScene(board3d, 750, 750, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.WHITE);
		subScene.setCamera(camera);
		Group group = new Group();

		group.getChildren().add(subScene);

		borderPane.setTop(menuBar);
		borderPane.setLeft(leftPanel);
		borderPane.setCenter(group);
		borderPane.setRight(rightPanel);
//**    borderPane.setBottom(bottomPanel);


		Scene scene = new Scene(borderPane, 1100, 800);

		scene.setOnMouseClicked(e -> {
			Node picked = e.getPickResult().getIntersectedNode();
			;;;System.out.println("e.getX(): " + e.getX());
			;;;System.out.println("e.getPickResult().getIntersectedPoint(): " + e.getPickResult().getIntersectedPoint());
			;;;System.out.println(e.getPickResult().getIntersectedNode().getOpacity());
			;;;System.out.println(e.getPickResult().getIntersectedNode().getId());
			;;;System.out.println("picked: " + picked);
			if (picked != null && picked.getId() != null) {
				if (picked.getId().contains("Field")) {
					double pScale = (picked.getScaleX() > 1 ) ? 1.0 : 1.1;
					picked.setScaleX(pScale); picked.setScaleY(pScale);	picked.setScaleZ(pScale);
					String id = picked.getId();
					if (id != null) {
						if (id.contains("*")) id = id.substring(0, id.length() - 2); else id += " *";
					}
					picked.setId(id);
				}
			}
		});

		scene.setOnKeyPressed(e -> {
			KeyCode k = e.getCode();
			//						;;;System.out.println("key " + k);
			boolean fControl = e.isControlDown();
			if (fControl) {
				if (k == KeyCode.A) {
					for (Node node : grBoard.getChildren()) {
						String id = node.getId();
						if(id != null && id.contains("Field")) {
							double pScale = (node.getScaleX() > 1 ) ? 1.0 : 1.1;
							node.setScaleX(pScale); node.setScaleY(pScale);	node.setScaleZ(pScale);
							if (id.contains("*")) id = id.substring(0, id.length() - 2); else id += " *";
							node.setId(id);
						}
					}
				}
			} else {

				int dRot = (e.isShiftDown()) ? 10 : 1; 
				if (k == KeyCode.LEFT) { camera.setRotationAxis(Rotate.Y_AXIS); camera.setRotate(camera.getRotate() - dRot); }
				else if (k == KeyCode.RIGHT) { camera.setRotationAxis(Rotate.Y_AXIS); camera.setRotate(camera.getRotate() + dRot); }
				else if (k == KeyCode.DOWN) { Camera.rotateX(dRot); }
				else if (k == KeyCode.UP) { Camera.rotateX(-dRot); }
				else if (k == KeyCode.PAGE_DOWN) { Camera.distLookAt(-dRot); }
				else if (k == KeyCode.PAGE_UP) { Camera.distLookAt(dRot); }
				else if (k == KeyCode.A) Board3D.replaceSelectedWith(board3d, 'A');
				else if (k == KeyCode.B) Board3D.replaceSelectedWith(board3d, 'C');
				else if (k == KeyCode.C) Board3D.replaceSelectedWith(board3d, 'B');
				else if (k == KeyCode.D) Board3D.replaceSelectedWith(board3d, 'D');
				else if (k == KeyCode.NUMPAD4 || k == KeyCode.DIGIT4) Camera.dMoveX(-0.25 * dRot);
				else if (k == KeyCode.NUMPAD6 || k == KeyCode.DIGIT6) Camera.dMoveX(0.25 * dRot);
				else if (k == KeyCode.NUMPAD2 || k == KeyCode.DIGIT2) Camera.dMoveY(-0.25 * dRot);
				else if (k == KeyCode.NUMPAD8 || k == KeyCode.DIGIT8) Camera.dMoveY(0.25 * dRot);
				else if (k == KeyCode.NUMPAD7 || k == KeyCode.DIGIT7) Camera.dMoveXY(-0.25 * dRot, 0.25 * dRot);
				else if (k == KeyCode.NUMPAD9 || k == KeyCode.DIGIT9) Camera.dMoveXY(0.25 * dRot, 0.25 * dRot);
				else if (k == KeyCode.NUMPAD1 || k == KeyCode.DIGIT1) Camera.dMoveXY(-0.25 * dRot, -0.25 * dRot);
				else if (k == KeyCode.NUMPAD3 || k == KeyCode.DIGIT3) Camera.dMoveXY(0.25 * dRot, -0.25 * dRot);
				else if (k == KeyCode.NUMPAD0 || k == KeyCode.DIGIT0) Camera.resetRot();
				else if (k == KeyCode.SUBTRACT || k == KeyCode.MINUS) Camera.dMoveZ(0.25 * dRot);
				else if (k == KeyCode.ADD || k == KeyCode.PLUS) Camera.dMoveZ(-0.25 * dRot);
				else if (k == KeyCode.NUMPAD5 || k == KeyCode.DIGIT5) Camera.resetMove();
			}
		});

		window.setScene(scene);

		timer = new AnimationTimer() { //https://docs.oracle.com/javafx/2/animations/basics.htm#CJAFADFJ
			@Override
			public void handle(long l) {
				tms = System.currentTimeMillis();
				dT = tms - tmsLast;
				tmsLast = tms;
				if (LeftButtonPanel.fBtn_Random) EventCalls.doRandom();
				if (LeftButtonPanel.fBtn_BruteForce && !BruteForce.fDoneRec) EventCalls.doBruteForce();
				if (LeftButtonPanel.fBtn_BruteForceSurf && !BruteForce.fDoneSurf) EventCalls.doBruteForceSurf();
			}
		};
//		;;;thisWindow = window;
		window.show();
		timer.start(); 
	}
}
