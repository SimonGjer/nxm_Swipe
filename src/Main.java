import java.io.File;
import java.io.InputStream;
import java.util.Random;

import com.sun.scenario.effect.Effect;

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
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
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


	static final double SQRT2 = Math.sqrt(2);


	;;;public static Stage thisWindow;

	public static Group grPath = new Group();
	public static Group grSuper = new Group();
//	public static Group grCamera = new Group();
	
	public static PerspectiveCamera camera;

	public static void main(String[] args) {
		myLaunch(args);
	}

	public static void myLaunch(String[] args) { // ?????
		;;;System.out.println("Start");
		launch(args);
		;;;System.out.println("After: launch(args);");
	}

	;;;public static Box thisBox;

	public static Group board3d;

	public static long tms = System.currentTimeMillis();
	public static long tmsLast = System.currentTimeMillis();
	public static long dT = 0;

	@Override
	public void start(Stage window) throws Exception {
		;;; System.out.println(System.getProperty("java.class.path"));
		;;; System.out.println(this.getClass());

		window.setTitle(Board.nCol + " x " + Board.nRow + " Swipe");

		BorderPane borderPane = new BorderPane();
		MenuBar menuBar = MenuTopBar.createMenuBar();
		VBox leftPanel = LeftButtonPanel.createPanel();
		board3d = Board3D.getBoard(Board.getBoard());

		camera = Camera.createCamera();
//		grCamera.getChildren().add(camera);
		
		//		board3d.getChildren().add(camera);

		board3d.getChildren().addAll(grPath, grSuper);
		SubScene subScene = new SubScene(board3d, 900, 750, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.WHITE);
		subScene.setCamera(camera);
		Group group = new Group();

		group.getChildren().add(subScene);


		borderPane.setTop(menuBar);
		borderPane.setLeft(leftPanel);
		borderPane.setCenter(group);


		Scene scene = new Scene(borderPane, 1000, 800);

		scene.setOnMouseClicked(e -> {
			Node picked = e.getPickResult().getIntersectedNode();
			;;;System.out.println("e.getX(): " + e.getX());
			;;;System.out.println("e.getPickResult().getIntersectedPoint(): " + e.getPickResult().getIntersectedPoint());
			;;;System.out.println(e.getPickResult().getIntersectedNode().getOpacity());
			;;;System.out.println(e.getPickResult().getIntersectedNode().getId());
			if (picked != null) {
				double scalar = 1.3;
				if(picked.getScaleX() > 1) scalar = 1;
				picked.setScaleX(scalar);
				picked.setScaleY(scalar);
				picked.setScaleZ(scalar);
			}
		});

		scene.setOnKeyPressed(e -> {
			KeyCode k = e.getCode();
//			;;;System.out.println("key " + k);
			int dRot = (e.isShiftDown()) ? 10 : 1; 
			if (k == KeyCode.LEFT) { camera.setRotationAxis(Rotate.Y_AXIS); camera.setRotate(camera.getRotate() - dRot); }
			if (k == KeyCode.RIGHT) { camera.setRotationAxis(Rotate.Y_AXIS); camera.setRotate(camera.getRotate() + dRot); }
			if (k == KeyCode.DOWN) { Camera.rotateX(dRot); }
			if (k == KeyCode.UP) { Camera.rotateX(-dRot); }
			if (k == KeyCode.PAGE_DOWN) { Camera.distLookAt(-dRot); }
			if (k == KeyCode.PAGE_UP) { Camera.distLookAt(dRot); }
		});

		//		;;;System.out.println("subScene.isDepthBuffer(): " + subScene.isDepthBuffer());
		//		;;;System.out.println("scene.isDepthBuffer(): " + scene.isDepthBuffer());
		window.setScene(scene);


		timer = new AnimationTimer() { //https://docs.oracle.com/javafx/2/animations/basics.htm#CJAFADFJ
			@Override
			public void handle(long l) {
				tms = System.currentTimeMillis();
				dT = tms - tmsLast;
				tmsLast = tms;

//				if (LeftButtonPanel.fBtnTmp1) {
					//					thisBox.setTranslateX(random.nextInt(5) + 2);
					//					System.out.println("camera.getRotationAxis() = " + camera.getRotationAxis());
//					camera.setRotationAxis(new Point3D(0.0, 1.0, 0.0));
//					camera.setRotate(System.currentTimeMillis() / 70.0);
					thisBox.setRotate(System.currentTimeMillis() / 100.0);
//				}
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
