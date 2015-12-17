import javax.swing.GroupLayout.Alignment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class RightPanel {

	public static Text textGen, textBoardSize, textHelp;
	public static Text textRandom, textBrute, textBruteSurf;
	public static Group grStopBtn;
	public static Button btnStop;
public static HBox hBoxStop;

	public static VBox createPanel() {
		VBox rightPanel = new VBox(5);

		rightPanel.setMinWidth(200);

		rightPanel.setStyle("-fx-border-color: black;");
		rightPanel.setPadding(new Insets(5));

		Font font = Font.font(null, FontWeight.BOLD, 14);

		textBoardSize = new Text();
		updateTextBoardInfo();
		textBoardSize.setFont(font);

		textGen = new Text();
		textGen.setText("");
		textGen.setFont(font);

		textHelp = new Text();
		textHelp.setText("Help:"
				+ "\n Arrow keys: Rotate"
				+ "\n PgUp / PgDn: Zoom"
				+ "\n Click: Select"
				+ "\n A, B, C, D: Change sel."
				+ "\n Numpad: Move cam. focus");
		textHelp.setFont(font);

		hBoxStop = new HBox();
		{
			hBoxStop.setAlignment(Pos.TOP_CENTER);
			grStopBtn = new Group();
			grStopBtn.setTranslateX(50); //Could not find a better solution


			btnStop = new Button("STOP");

			//		btnStop.setTextAlignment(TextAlignment.CENTER);
			Font fontStopBtn = Font.font(null, FontWeight.BOLD, 24);
			btnStop.setFont(fontStopBtn);;
			btnStop.setOnAction( e -> {	
				LeftButtonPanel.fBtn_BruteForce = false;
				LeftButtonPanel.fBtn_Random = false;
				LeftButtonPanel.fBtn_BruteForceSurf = false;
				LeftButtonPanel.resetOngoing();
				grStopBtn.getChildren().clear();
			});
			hBoxStop.getChildren().add(btnStop);
		}

		textRandom = new Text(); textRandom.setFont(font);
		textBrute = new Text(); textBrute.setFont(font);
		textBruteSurf = new Text(); textBruteSurf.setFont(font);



		rightPanel.getChildren().addAll(textBoardSize, textGen, textHelp);
		rightPanel.getChildren().addAll(textRandom, textBrute, textBruteSurf);

		return rightPanel;
	}

	public static void setTextGen(String txt) {
		textGen.setText(txt);
	}

	public static void updateTextBoardInfo() {
		Component.getComponents(Board.getBoard());
		int nComp = Component.nComp;
		int nBigComp = Component.nBigComp;
		textBoardSize.setText("Info.:"
				+ "\n Board Size: " + Board.nCol + " x " + Board.nRow
				+ "\n Vertices: " + Board.nCol * Board.nRow
				+ "\n No of Components: " + nComp
				+ "\n Biggest Component: " + nBigComp);
	}

	public static void updateBruteForce() {
		String txt = "Brute Force:"
				+ '\n' + "Longest Path Size: " + (EventCalls.currentLongestPath.size() / 2 )
				+ '\n' + "Found at step: " + getNumWithDecades(BruteForce.iStepAtLongPath)
				+ '\n' + "Steps: " + getNumWithDecades(BruteForce.iStepRec)
				+ '\n' + "Time: " + getNumWithDecades((int) EventCalls.tSumBrute) + " ms"
				+ '\n' + getTxtRunning(!BruteForce.fDoneRe) + '\n';
		RightPanel.textBrute.setText(txt);
	}

	public static void updateBigComp() { RightPanel.setTextGen(Component.txtBigComp); }

	public static void updateComponents() {	RightPanel.setTextGen(Component.txtComps);	}

	public static void updateRandom() {
		RightPanel.textRandom.setText("Random:"
				+ '\n' + "Longest Path Size: " + (EventCalls.currentLongestPath.size() / 2 )
				+ '\n' + "runs: " + getNumWithDecades(EventCalls.iCall)
				+ '\n' + "Time: " + getNumWithDecades((int) EventCalls.tSumRandom) + " ms"
				+ '\n' + getTxtRunning(true));

		boolean fBtnStop = RightPanel.grStopBtn.getChildren().contains(RightPanel.btnStop);
		if (!fBtnStop) RightPanel.grStopBtn.getChildren().add(RightPanel.btnStop);
	}
	
	public static void updateBruteForceSurf() {
		String txt = "Brute Force Surf:"
//				+ '\n' + "Longest Path Size: " + (EventCalls.currentLongestPathSurf.size() / 2 )
				+ '\n' + "Heaviest Path: " + (BruteForce.wLPSurf)
				+ '\n' + "Found at step: " + getNumWithDecades(BruteForce.iStepAtLongPathSurf)
				+ '\n' + "Steps: " + getNumWithDecades(BruteForce.iStepSurf)
				+ '\n' + "Time: " + getNumWithDecades((int) EventCalls.tSumBruteSurf) + " ms"
				+ '\n' + getTxtRunning(!BruteForce.fDoneSurf) + '\n';
		RightPanel.textBruteSurf.setText(txt);
	}

	public static void updateStopBtn() {
		boolean fBtnStop = RightPanel.grStopBtn.getChildren().contains(RightPanel.btnStop);
		if (LeftButtonPanel.fBtn_BruteForce || LeftButtonPanel.fBtn_Random) {
			if (!fBtnStop) RightPanel.grStopBtn.getChildren().add(RightPanel.btnStop);
		} else {
			if (fBtnStop) RightPanel.grStopBtn.getChildren().clear();
		}
	}

	public static String getNumWithDecades(int num) { return getNumWithDecades(num + ""); }

	public static String getNumWithDecades(String num) {
		String numDec = "";
		boolean fInsDel = false;
		do {
			int l = num.length();
			numDec = num.substring((l - 3 < 0) ? 0 : l - 3, l) + (fInsDel ? ',' : "") + numDec;
			fInsDel = true;
			if (l > 3) num = num.substring(0, l - 3); else num ="";
		} while (num.length() > 0);
		return numDec;
	}

	public static String getTxtRunning(boolean fRunning) {
		long t = System.currentTimeMillis();
		String txt;
		if (fRunning) {
			String dots = "";
			for (int iDot = 0; iDot < ((t / 250) & 0b11); iDot++) dots += '.';
			txt = "Running" + dots;
		} else { txt = "Done"; }
		return txt;
	}
}
