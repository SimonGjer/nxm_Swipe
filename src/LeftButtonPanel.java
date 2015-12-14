import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
//	public static boolean fBtn_SuperNode = false;
	public static boolean fBtn_Random = false;

	public static CheckBox cBoxSuper, cBoxR1, cBoxR2, cBoxR3, cBoxR4, cBoxR5;

	public static VBox createPanel() {

		int spacing = 5; VBox leftPanel = new VBox(spacing);

		//		ArrayList<Button> btns = new ArrayList<>();

		Button btn;
		CheckBox cBox;

		btn = new Button("New Board"); btn.setId("New");
		btn.setOnAction( e -> {
			Board.newRndBoard(); Path.resetPath(); Graph.setGraph(null); Board3D.update();
			doBtn_Actions(); resetOngoing();
		});
		leftPanel.getChildren().add(btn);

		btn = new Button("Open..."); btn.setId("Open");
		btn.setOnAction( e -> { Board.openFile(); Board3D.update(); doBtn_Actions(); resetOngoing(); });
		leftPanel.getChildren().add(btn);

		btn = new Button("Save..."); btn.setId("Save");
		btn.setOnAction( e -> { Board.saveFile(); Board3D.update(); doBtn_Actions(); });
		leftPanel.getChildren().add(btn);

		btn = new Button("Item Freq..."); btn.setId("Item Freq");
		btn.setOnAction( e -> {	} );
		leftPanel.getChildren().add(btn);


		btn = new Button("Col -1"); btn.setId("Col -1");
		btn.setOnAction( e -> {	Board.colMinus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		leftPanel.getChildren().add(btn);
		btn = new Button("Col +1"); btn.setId("Col +1");
		btn.setOnAction( e -> {	Board.colPlus(); Board3D.update(); doBtn_Actions();	resetOngoing(); } );
		leftPanel.getChildren().add(btn);
		btn = new Button("Row -1"); btn.setId("Row -1");
		btn.setOnAction( e -> {	Board.rowMinus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		leftPanel.getChildren().add(btn);
		btn = new Button("Row +1"); btn.setId("Row +1");
		btn.setOnAction( e -> {	Board.rowPlus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		leftPanel.getChildren().add(btn);

		cBox = new CheckBox("Components"); cBox.setId("Components");
		cBox.setOnAction( e -> {
			fBtn_Comp = !fBtn_Comp;
			if(fBtn_Comp) {	doComp(); } else { Main.grPathComp.getChildren().clear(); }
		} );
		leftPanel.getChildren().add(cBox);

		cBox = new CheckBox("Big Comp."); cBox.setId("Big Comp.");
		cBox.setOnAction( e -> {
			fBtn_BigComp = !fBtn_BigComp;
			if(fBtn_BigComp) { doBigComp(); } else { Main.grPathBigComp.getChildren().clear(); }
		} );
		leftPanel.getChildren().add(cBox);

		cBox = new CheckBox("Brute Force"); cBox.setId("Brute Force");
		cBox.setOnAction( e -> {
			System.out.println("Button: Brute Force");
			fBtn_BruteForce = !fBtn_BruteForce;
			if(fBtn_BruteForce) { doBruteForce(); } else { resetOngoing(); Main.grPathBruteForce.getChildren().clear(); }
		} );
		leftPanel.getChildren().add(cBox);

		btn = new Button("Path Progress"); btn.setId("Path Progress");
		btn.setOnAction( e -> {	} );
		leftPanel.getChildren().add(btn);

		btn = new Button("Path Steps"); btn.setId("Path Steps");
		btn.setOnAction( e -> {	} );
		leftPanel.getChildren().add(btn);

		cBox = new CheckBox("Random"); cBox.setId("Random");
		cBox.setOnAction( e -> {	fBtn_Random = !fBtn_Random;
		if(fBtn_Random) { doRandom(); } else { resetOngoing(); Path.resetPath3d(); }
		} );
		leftPanel.getChildren().add(cBox);

		btn = new Button("Rnd Big Comp."); btn.setId("Rnd Big Comp.");
		btn.setOnAction( e -> {	} );
		leftPanel.getChildren().add(btn);

		cBoxSuper = new CheckBox("Supernode"); cBoxSuper.setId("Supernode");
		cBoxSuper.setSelected(Super.fDrawSurface);
		cBoxSuper.setOnAction( e -> { Super.fDrawSurface = cBoxSuper.isSelected();
		if(cBoxSuper.isSelected()) { Main.grSuper.getChildren().clear(); doSuperNode(); } else { Main.grSuper.getChildren().clear(); Camera.update(); }
		} );
		leftPanel.getChildren().add(cBoxSuper);


		cBoxR1 = new CheckBox("R1: Chain"); cBoxR1.setId("R1");
		cBoxR1.setSelected(Super.fRule[0]);
		cBoxR1.setOnAction( e -> { Super.fRule[0] = cBoxR1.isSelected();
		if (cBoxSuper.isSelected()) { Main.grSuper.getChildren().clear(); doSuperNode(); } 
		} );
		leftPanel.getChildren().add(cBoxR1);

		cBoxR2 = new CheckBox("R2: Col. Tri."); cBoxR2.setId("R2");
		cBoxR2.setSelected(Super.fRule[1]);
		cBoxR2.setOnAction( e -> { Super.fRule[1] = cBoxR2.isSelected();
		if (cBoxSuper.isSelected()) { Main.grSuper.getChildren().clear(); doSuperNode(); }
		} );
		leftPanel.getChildren().add(cBoxR2);

		cBoxR3 = new CheckBox("R3: Tri del edge"); cBoxR3.setId("R3");
		cBoxR3.setSelected(Super.fRule[2]);
		cBoxR3.setOnAction( e -> { Super.fRule[2] = cBoxR3.isSelected();
		if (cBoxSuper.isSelected()) { Main.grSuper.getChildren().clear(); doSuperNode(); }
		} );
		leftPanel.getChildren().add(cBoxR3);

		cBoxR4 = new CheckBox("R4: W3 del edge"); cBoxR4.setId("R4");
		cBoxR4.setSelected(Super.fRule[3]);
		cBoxR4.setOnAction( e -> { Super.fRule[3] = cBoxR4.isSelected();
		if (cBoxSuper.isSelected()) { Main.grSuper.getChildren().clear(); doSuperNode(); }
		} );
		leftPanel.getChildren().add(cBoxR4);

		cBoxR5 = new CheckBox("R5: Col K4"); cBoxR5.setId("R5");
		cBoxR5.setSelected(Super.fRule[4]);
		cBoxR5.setOnAction( e -> { Super.fRule[4] = cBoxR5.isSelected();
		if (cBoxSuper.isSelected()) { Main.grSuper.getChildren().clear(); doSuperNode(); }
		} );
		leftPanel.getChildren().add(cBoxR5);



		btn = new Button("Tmp 1"); btn.setId("Tmp 1");
		btn.setOnAction( e -> { fBtnTmp1 = !fBtnTmp1; } );
		leftPanel.getChildren().add(btn);

		return leftPanel;
	}

	public static void doBtn_Actions() {
		if (fBtn_Comp) doComp();
		if (fBtn_BigComp) doBigComp();
		if (fBtn_BruteForce) doBruteForce();
		if (cBoxSuper.isSelected()) doSuperNode();
	}

	public static void doComp() {
		boolean[][][] components = Component.getComponents(Board.getBoard());
		int[][] path = Path.compToPath(components);
		Path.drawPath3d(path, Main.grPathComp);
		RightPanel.updateComponents();
	}
	public static void doBigComp() {
		boolean[][][] compBig1 = Component.getNBigestComponents(Board.getBoard(), 1);
		int[][] sBigPath = Path.compToPath(compBig1);
		Path.drawPath3d(sBigPath,Main.grPathBigComp ,Color.WHITESMOKE);
		RightPanel.updateBigComp();
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
		Camera.update();
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
