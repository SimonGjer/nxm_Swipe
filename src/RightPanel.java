import javax.swing.GroupLayout.Alignment;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class RightPanel {


	public static Text text;
	public static Group grStopBtn;
	public static Button btnStop;

	public static VBox createPanel() {
		VBox rightPanel = new VBox(5);

		rightPanel.setMinWidth(200);

		text = new Text();
		text.setText("");
		Font font = Font.font(null, FontWeight.BOLD, 14);
		text.setFont(font);

		grStopBtn = new Group();
		grStopBtn.setTranslateX(50); //Could not find a better solution

		rightPanel.getChildren().addAll(text, grStopBtn);

		btnStop = new Button("STOP");

		//		btnStop.setTextAlignment(TextAlignment.CENTER);
		Font fontStopBtn = Font.font(null, FontWeight.BOLD, 24);
		btnStop.setFont(fontStopBtn);;
		btnStop.setOnAction( e -> {	
			LeftButtonPanel.fBtn_BruteForce = false;
			LeftButtonPanel.fBtn_Random = false;
			LeftButtonPanel.resetOngoing();
			grStopBtn.getChildren().clear();
		});

		return rightPanel;
	}

	public static void setText(String txt) {
		text.setText(txt);
	}

	public static void updateBruteForce() {
		String txt = "Brute Force:"
				+ '\n' + "Longest Path Size: " + (EventCalls.currentLongestPath.size() / 2 - 1)
				+ '\n' + "Found at step: " + getNumWithDecades(BruteForce.iStepAtLongPath)
				+ '\n' + "Steps: " + getNumWithDecades(BruteForce.iStep)
				+ '\n' + getTxtRunning(!BruteForce.fDoneRe);
		RightPanel.setText(txt);
	}

	public static void updateRandom() {
		RightPanel.setText("Random:"
				+ '\n' + "Longest Path Size: " + (EventCalls.currentLongestPath.size() / 2 - 1)
				+ '\n' + "runs: " + getNumWithDecades(EventCalls.iCalls)
				+ '\n' + getTxtRunning(true));

		boolean fBtnStop = RightPanel.grStopBtn.getChildren().contains(RightPanel.btnStop);
		if (!fBtnStop) RightPanel.grStopBtn.getChildren().add(RightPanel.btnStop);
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
