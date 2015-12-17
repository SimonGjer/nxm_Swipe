import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;


public class LeftButtonPanel {

	public static Random random = new Random();

	public static boolean fBtn_Comp = false;
	public static boolean fBtn_BigComp = false;
	public static boolean fBtn_BruteForce = false;
	public static boolean fBtn_Random = false;
	public static boolean fBtn_BruteForceSurf = false;

	public static CheckBox cBoxSuper, cBoxR1, cBoxR2, cBoxR3, cBoxR4, cBoxR5;
	public static CheckBox cBoxApple, cBoxBlueberry, cBoxChestnut, cBoxAcorn;

	private static Label lblAppleFreq, lblChestNutFreq, lblBlueberryFreq, lblAcornFreq;

	private static HBox hBoxApple, hBoxChestNut, hBoxBlueberry, hBoxAcorn;

	public static VBox createPanel() {

		int spacing = 5;
		VBox leftPanel = new VBox(spacing);
		leftPanel.setPadding(new Insets(5));
		Button btn;
		CheckBox cBox;

		//Canvas canvas = new Canvas();
		//canvas.
		VBox vBoxFreq = new VBox();
		{
			vBoxFreq.setMinWidth(125);

			vBoxFreq.setStyle("-fx-border-color: black;");
			vBoxFreq.setPadding(new Insets(5));
			vBoxFreq.setSpacing(3);
			vBoxFreq.setAlignment(Pos.TOP_CENTER);

			btn = new Button("New Board"); btn.setId("New");
			btn.setOnAction( e -> { doNew(); });
			vBoxFreq.getChildren().add(btn);

			hBoxApple = new HBox(); hBoxApple.setSpacing(3);
			hBoxChestNut = new HBox(); hBoxChestNut.setSpacing(3);
			hBoxBlueberry = new HBox(); hBoxBlueberry.setSpacing(3);
			hBoxAcorn = new HBox(); hBoxAcorn.setSpacing(3);
			{
				Label lblApple = new Label(); lblApple.setGraphic(new ImageView(Item.iconApple));
				Label lblChestNut = new Label(); lblChestNut.setGraphic(new ImageView(Item.iconChestnut));
				Label lblBlueberry = new Label(); lblBlueberry.setGraphic(new ImageView(Item.iconBlueberry));
				Label lblAcorn = new Label(); lblAcorn.setGraphic(new ImageView(Item.iconAcorn));

				ImageView ivMinusApple = new ImageView(new Image("images/IconMinus_12px.png"));
				ImageView ivPlusApple = new ImageView(new Image("images/IconPlus_12px.png"));
				ImageView ivMinusChestNut = new ImageView(new Image("images/IconMinus_12px.png"));
				ImageView ivPlusChestNut = new ImageView(new Image("images/IconPlus_12px.png"));
				ImageView ivMinusBlueberry = new ImageView(new Image("images/IconMinus_12px.png"));
				ImageView ivPlusBlueberry = new ImageView(new Image("images/IconPlus_12px.png"));
				ImageView ivMinusAcorn = new ImageView(new Image("images/IconMinus_12px.png"));
				ImageView ivPlusAcorn = new ImageView(new Image("images/IconPlus_12px.png"));

				Button btnAppleMinus = new Button(""); btnAppleMinus.setGraphic(ivMinusApple); btnAppleMinus.setOnAction( e -> { ProbItem.minus('A'); updateFreq(); });
				Button btnApplePlus = new Button(""); btnApplePlus.setGraphic(ivPlusApple); btnApplePlus.setOnAction( e -> { ProbItem.plus('A'); updateFreq(); });

				Button btnChestNutMinus = new Button(""); btnChestNutMinus.setGraphic(ivMinusChestNut); btnChestNutMinus.setOnAction( e -> { ProbItem.minus('B'); updateFreq(); });
				Button btnChestNutPlus = new Button(""); btnChestNutPlus.setGraphic(ivPlusChestNut); btnChestNutPlus.setOnAction( e -> { ProbItem.plus('B'); updateFreq(); });

				Button btnBlueberryMinus = new Button(""); btnBlueberryMinus.setGraphic(ivMinusBlueberry); btnBlueberryMinus.setOnAction( e -> { ProbItem.minus('C'); updateFreq(); });
				Button btnBlueberryPlus = new Button(""); btnBlueberryPlus.setGraphic(ivPlusBlueberry); btnBlueberryPlus.setOnAction( e -> { ProbItem.plus('C'); updateFreq(); });

				Button btnAcornMinus = new Button(""); btnAcornMinus.setGraphic(ivMinusAcorn); btnAcornMinus.setOnAction( e -> { ProbItem.minus('D'); updateFreq(); });
				Button btnAcornPlus = new Button(""); btnAcornPlus.setGraphic(ivPlusAcorn); btnAcornPlus.setOnAction( e -> { ProbItem.plus('D'); updateFreq(); });


				lblAppleFreq = new Label();
				lblChestNutFreq = new Label();
				lblBlueberryFreq = new Label();
				lblAcornFreq = new Label();
				updateFreq();

				hBoxApple.getChildren().addAll(lblApple, btnAppleMinus, btnApplePlus, lblAppleFreq);
				hBoxChestNut.getChildren().addAll(lblChestNut, btnChestNutMinus, btnChestNutPlus, lblChestNutFreq);
				hBoxBlueberry.getChildren().addAll(lblBlueberry, btnBlueberryMinus, btnBlueberryPlus, lblBlueberryFreq);
				hBoxAcorn.getChildren().addAll(lblAcorn, btnAcornMinus, btnAcornPlus, lblAcornFreq);
				vBoxFreq.getChildren().addAll(hBoxApple, hBoxChestNut, hBoxBlueberry, hBoxAcorn);
			}
		}
		leftPanel.getChildren().add(vBoxFreq);

		HBox hBoxOpenSave = new HBox(); hBoxOpenSave.setAlignment(Pos.TOP_CENTER); 
		{
			btn = new Button("Open..."); btn.setId("Open");
			btn.setOnAction( e -> { doOpen(); });
			hBoxOpenSave.getChildren().add(btn);

			btn = new Button("Save..."); btn.setId("Save");
			btn.setOnAction( e -> { doSave(); });
			hBoxOpenSave.getChildren().add(btn);
		}
		leftPanel.getChildren().add(hBoxOpenSave);


		HBox colBtns = new HBox();

		btn = new Button("-Col."); btn.setId("Col -1");
		btn.setOnAction( e -> {	Board.colMinus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		colBtns.getChildren().add(btn);
		btn = new Button("+Col."); btn.setId("Col +1");
		btn.setOnAction( e -> {	Board.colPlus(); Board3D.update(); doBtn_Actions();	resetOngoing(); } );
		colBtns.getChildren().add(btn);
		leftPanel.getChildren().add(colBtns);

		HBox rowBtns = new HBox();

		btn = new Button("-Row"); btn.setId("Row -1");
		btn.setOnAction( e -> {	Board.rowMinus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		rowBtns.getChildren().add(btn);
		btn = new Button("+Row"); btn.setId("Row +1");
		btn.setOnAction( e -> {	Board.rowPlus(); Board3D.update(); doBtn_Actions(); resetOngoing(); } );
		rowBtns.getChildren().add(btn);
		leftPanel.getChildren().add(rowBtns);

		cBoxApple = new CheckBox("Apple"); cBoxApple.setId("Apple"); cBoxApple.setSelected(Item.fSelcApple);
		cBoxApple.setOnAction( e -> {
			Item.fSelcApple = cBoxApple.isSelected(); doBtn_Actions(); //if(cBoxApple.isSelected()) {} else {  }
		} );
		leftPanel.getChildren().add(cBoxApple);

		cBoxBlueberry = new CheckBox("Blueberry"); cBoxBlueberry.setId("Blueberry"); cBoxBlueberry.setSelected(Item.fSelcBlueberry);
		cBoxBlueberry.setOnAction( e -> {
			Item.fSelcBlueberry = cBoxBlueberry.isSelected(); doBtn_Actions(); //if(cBoxBlueberry.isSelected()) {} else {  }
		} );
		leftPanel.getChildren().add(cBoxBlueberry);

		cBoxChestnut = new CheckBox("Chestnut"); cBoxChestnut.setId("Chestnut"); cBoxChestnut.setSelected(Item.fSelcChestnut);
		cBoxChestnut.setOnAction( e -> {
			Item.fSelcChestnut = cBoxChestnut.isSelected(); doBtn_Actions(); //if(cBoxChestnut.isSelected()) {} else {  }
		} );
		leftPanel.getChildren().add(cBoxChestnut);

		cBoxAcorn = new CheckBox("Acorn"); cBoxAcorn.setId("Acorn"); cBoxAcorn.setSelected(Item.fSelcAcorn);
		cBoxAcorn.setOnAction( e -> {
			Item.fSelcAcorn = cBoxAcorn.isSelected(); doBtn_Actions(); //if(cBoxAcorn.isSelected()) {} else {  }
		} );
		leftPanel.getChildren().add(cBoxAcorn);


		cBox = new CheckBox("Components"); cBox.setId("Components");
		cBox.setOnAction( e -> {
			fBtn_Comp = !fBtn_Comp;
			if(fBtn_Comp) {	doComp(); } else { Path.grPathComp.getChildren().clear(); }
		} );
		leftPanel.getChildren().add(cBox);

		cBox = new CheckBox("Big Comp."); cBox.setId("Big Comp.");
		cBox.setOnAction( e -> {
			fBtn_BigComp = !fBtn_BigComp;
			if(fBtn_BigComp) { doBigComp(); } else { Path.grPathBigComp.getChildren().clear(); }
		} );
		leftPanel.getChildren().add(cBox);

		cBox = new CheckBox("Brute Force"); cBox.setId("Brute Force");
		cBox.setOnAction( e -> {
			fBtn_BruteForce = !fBtn_BruteForce;
			if(fBtn_BruteForce) { doBruteForce(); } else { resetOngoing(); RightPanel.textBrute.setText(""); Path.grPathBruteForce.getChildren().clear(); }
		} );
		leftPanel.getChildren().add(cBox);

		CheckBox cBoxBFSurf = new CheckBox("Brute Force Surf"); cBox.setId("Brute Force Surf");
		cBoxBFSurf.setOnAction( e -> {
			;;;System.out.println("Button: Brute Force Surf");
			fBtn_BruteForceSurf = !fBtn_BruteForceSurf;
			if(fBtn_BruteForceSurf) { doBruteForceSurf(); } else {
				resetOngoing(); RightPanel.textBruteSurf.setText(""); Path.grPathSurface.getChildren().clear();
				}
		} );
		leftPanel.getChildren().add(cBoxBFSurf);

		cBox = new CheckBox("Path Progress"); cBox.setId("Path Progress");
		cBox.setOnAction( e -> {	} );
		leftPanel.getChildren().add(cBox);

		cBox = new CheckBox("Path Steps"); cBox.setId("Path Steps");
		cBox.setOnAction( e -> {	} );
		leftPanel.getChildren().add(cBox);

		cBox = new CheckBox("Random"); cBox.setId("Random");
		cBox.setOnAction( e -> { fBtn_Random = !fBtn_Random;
		if(fBtn_Random) { doRandom(); } else { resetOngoing(); RightPanel.textRandom.setText(""); Path.grPathRnd.getChildren().clear(); }
		} );
		leftPanel.getChildren().add(cBox);

		cBox = new CheckBox("Rnd Big Comp."); cBox.setId("Rnd Big Comp.");
		cBox.setOnAction( e -> {	} );
		leftPanel.getChildren().add(cBox);

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


		return leftPanel;
	}

	public static void updateFreq() {
		lblAppleFreq.setText(" " + ProbItem.fqs[0] * 100 / ProbItem.sumFre + "%");
		lblChestNutFreq.setText(" " + ProbItem.fqs[1] * 100 / ProbItem.sumFre + "%");
		lblBlueberryFreq.setText(" " + ProbItem.fqs[2] * 100 / ProbItem.sumFre + "%");
		lblAcornFreq.setText(" " + ProbItem.fqs[3] * 100 / ProbItem.sumFre + "%");
	}

	public static void doBtn_Actions() {
		if (fBtn_Comp) doComp();
		if (fBtn_BigComp) doBigComp();
		if (fBtn_BruteForce) doBruteForce();
		if (fBtn_BruteForceSurf) doBruteForceSurf();
		if (cBoxSuper.isSelected()) doSuperNode();
		Path.update();
	}

	public static void doNew() { Board.newRndBoard(); Path.resetPath(); Super.reset(); Graph.setGraph(null); Board3D.update(); RightPanel.updateTextBoardInfo(); doBtn_Actions(); resetOngoing(); }
	public static void doNew(int nCol, int nRow) { Board.newRndBoard(nCol, nRow); doNew(); }
	public static void doOpen() { Board.openFile(); Board3D.update(); doBtn_Actions(); resetOngoing(); }
	public static void doSave() { Board.saveFile(); /*Board3D.update(); doBtn_Actions();*/ }

	public static void doComp() {
		boolean[][][] components = Component.getComponents(Board.getBoard());
		int[][] path = Path.compToPath(components);
		Path.drawPath3d(path, Path.grPathComp);
		RightPanel.updateComponents();
	}
	public static void doBigComp() {
		boolean[][][] compBig1 = Component.getNBigestComponents(Board.getBoard(), 1);
		int[][] sBigPath = Path.compToPath(compBig1);
		Path.drawPath3d(sBigPath, Path.grPathBigComp, Color.WHITESMOKE);
		RightPanel.updateBigComp();
	}
	public static void doBruteForce() {	EventCalls.resetBruteForce(); }

	public static void doBruteForceSurf() {	Path.update(); EventCalls.resetBruteForceSurf(); }

	public static void doSuperNode() {
		VertexSuper[][] G = Super.getGraphWithSuperNodesTmp(Board.getBoard());
		Graph.setGraph(G);
		if (fBtn_BruteForce) doBruteForce();
		if (fBtn_BruteForceSurf) doBruteForceSurf();
		Super.draw3d(G);
		Path.update();
		Camera.update();
	}
	public static void doRandom() {
		EventCalls.resetLongestRandomPath();
	}
	public static void resetOngoing() {
		EventCalls.resetBruteForce();
		EventCalls.resetLongestRandomPath();
		EventCalls.resetBruteForceSurf();
		RightPanel.updateStopBtn();
		if(fBtn_BruteForce) RightPanel.updateBruteForce();
		else if(fBtn_Random) RightPanel.updateRandom();
	}
}
