import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;


public class LeftButtonPanel {

	public static Random random = new Random();

	public static boolean fBtnTmp1 = false;
	public static boolean fBtnTmp2 = false;
	public static boolean fBtn_Comp = false;
	public static boolean fBtn_BigComp = false;
	public static boolean fBtn_BruteForce = false;
	public static boolean fBtn_SuperNode = false;
	public static boolean fBtn_Random = false;

	public static VBox createPanel() {

		int spacing = 5;

		VBox leftPanel = new VBox(spacing);

		ArrayList<Button> btns = new ArrayList<>();

		Button btn;

		btn = new Button("New Board"); btn.setId("New");
		btn.setOnAction( e -> {
			Board.newRndBoard(); Path.resetPath(); Graph.setGraph(null); Board3D.update();
			doBtn_Actions(); resetOngoing();
		});
		btns.add(btn);

		btn = new Button("Open"); btn.setId("Open");
		btn.setOnAction( e -> { Board.openFile(); Board3D.update(); doBtn_Actions(); resetOngoing(); });
		btns.add(btn);

		btn = new Button("Save"); btn.setId("Save");
		btn.setOnAction( e -> { Board.saveFile(); Board3D.update(); doBtn_Actions(); });
		btns.add(btn);

		btn = new Button("Item Freq."); btn.setId("Item Freq");
		btn.setOnAction( e -> {	} );
		btns.add(btn);


		btn = new Button("Col -1"); btn.setId("Col -1");
		btn.setOnAction( e -> {	Board.colMinus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		btns.add(btn);
		btn = new Button("Col +1"); btn.setId("Col +1");
		btn.setOnAction( e -> {	Board.colPlus(); Board3D.update(); doBtn_Actions();	resetOngoing(); } );
		btns.add(btn);
		btn = new Button("Row -1"); btn.setId("Row -1");
		btn.setOnAction( e -> {	Board.rowMinus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		btns.add(btn);
		btn = new Button("Row +1"); btn.setId("Row +1");
		btn.setOnAction( e -> {	Board.rowPlus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		btns.add(btn);

		btn = new Button("Components"); btn.setId("Components");
		btn.setOnAction( e -> {
			fBtn_Comp = !fBtn_Comp;
			if(fBtn_Comp) {	doComp(); } else { Path.resetPath3d(); }
		} );
		btns.add(btn);

		btn = new Button("Big Comp."); btn.setId("Big Comp.");
		btn.setOnAction( e -> {
			fBtn_BigComp = !fBtn_BigComp;
			if(fBtn_BigComp) { doBigComp(); } else { Path.resetPath3d(); }
		} );
		btns.add(btn);

		btn = new Button("Brute Force"); btn.setId("Brute Force");
		btn.setOnAction( e -> {
			System.out.println("Button: Brute Force");
			fBtn_BruteForce = !fBtn_BruteForce;
			if(fBtn_BruteForce) { doBruteForce(); } else { resetOngoing(); Path.resetPath3d(); }
		} );
		btns.add(btn);

		btn = new Button("Path Progress"); btn.setId("Path Progress");
		btn.setOnAction( e -> {	} );
		btns.add(btn);

		btn = new Button("Path Steps"); btn.setId("Path Steps");
		btn.setOnAction( e -> {	} );
		btns.add(btn);

		btn = new Button("Random"); btn.setId("Random");
		btn.setOnAction( e -> {	fBtn_Random = !fBtn_Random;
		if(fBtn_Random) { doRandom(); } else { resetOngoing(); Path.resetPath3d(); }
		} );
		btns.add(btn);

		btn = new Button("Rnd Big Comp."); btn.setId("Rnd Big Comp.");
		btn.setOnAction( e -> {	} );
		btns.add(btn);

		btn = new Button("Supernode"); btn.setId("Supernode");
		btn.setOnAction( e -> {
			fBtn_SuperNode = !fBtn_SuperNode;
			if(fBtn_SuperNode) { doSuperNode(); } else { Main.grSuper.getChildren().clear(); }
		} );
		btns.add(btn);

		btn = new Button("Tmp 1"); btn.setId("Tmp 1");
		btn.setOnAction( e -> { fBtnTmp1 = !fBtnTmp1; } );
		btns.add(btn);

		btn = new Button("Tmp 2"); btn.setId("Tmp 2");
		btn.setOnAction( e -> {
			fBtnTmp2 = !fBtnTmp2;
			//			PerspectiveCamera camera = Main.camera;
			//			camera.setRotationAxis(Rotate.X_AXIS);
			//			if (fBtnTmp2) {	camera.setRotate(40); camera.setTranslateZ(-2);
			//			} else { camera.setRotate(0); camera.setTranslateZ(0); }
		} );
		btns.add(btn);
		btn = new Button("Tmp 3"); btn.setId("Tmp 3");
		btn.setOnAction( e -> {});
		btns.add(btn);


		for (int i = 0; i < btns.size(); i++) {
			btn = btns.get(i);
			leftPanel.getChildren().addAll(btn);
		}

		return leftPanel;
	}

	public static void doBtn_Actions() {
		if (fBtn_Comp) doComp();
		if (fBtn_BigComp) doBigComp();
		if (fBtn_BruteForce) doBruteForce();
		if (fBtn_SuperNode) doSuperNode();
	}

	public static void doComp() {
		boolean[][][] components = Component.getComponents(Board.getBoard());
		int[][] path = Path.compToPath(components);
		Path.drawPath3d(path);
	}
	public static void doBigComp() {
		boolean[][][] compBig1 = Component.getNBigestComponents(Board.getBoard(), 1);
		int[][] sBigPath = Path.compToPath(compBig1);
		Path.drawPath3d(sBigPath, Color.WHITESMOKE);
	}
	public static void doBruteForce() {
//		;;;System.out.println("LeftButtonPanel.doBruteForce()");
		EventCalls.resetBruteForce();
		//		VertexSimple[][] vertex = BruteForce.boardToGraph(Board.getBoard());
		//		ArrayList<Integer> longestPath = BruteForce.findLongestPath(vertex);
		//		Path.drawPath3d(longestPath);
	}
	public static void doSuperNode() {
		VertexSuper[][] G = Super.getGraphWithSuperNodesTmp(Board.getBoard());
		Graph.setGraph(G);
		Super.draw3d(G);
	}
	public static void doRandom() {
		//		;;;System.out.println("doRandom()");
		EventCalls.resetLongestRandomPath();
		//		while(true){
		//			ArrayList<Integer> currentLongestPath = new ArrayList<>();
		//			ArrayList<Integer> longestPath = RandomAlgorithm.random();
		//			Main.mainScene.requestLayout();
		//			if(longestPath.size() > currentLongestPath.size()) {
		//				currentLongestPath = longestPath;
		//				Path.drawPath3d(longestPath);
		//			}
		//		}
	}
	public static void resetOngoing() {
		EventCalls.resetBruteForce();
		EventCalls.resetLongestRandomPath();
		RightPanel.updateStopBtn();
		if(fBtn_BruteForce) RightPanel.updateBruteForce();
		else if(fBtn_Random) RightPanel.updateRandom();
	}
}
