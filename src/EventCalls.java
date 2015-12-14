import java.util.ArrayList;


public class EventCalls {
	public static int iCalls;

	public static ArrayList<Integer> currentLongestPath = new ArrayList<>();
	public static void doRandom() {

		long t = System.currentTimeMillis();
		boolean fUpdatePath = false;

		while (!fUpdatePath && t + 20 > System.currentTimeMillis()) {
			iCalls++;
			ArrayList<Integer> longestPath = RandomAlgorithm.random();
			if(longestPath.size() > currentLongestPath.size()) {
				fUpdatePath = true;
				currentLongestPath = longestPath;
				Path.drawPath3d(longestPath, Main.grPathRnd);
			}
		}
		RightPanel.updateRandom();
//		RightPanel.setText("Random:"
//				+ '\n' + "Longest Path Size: " + (currentLongestPath.size() / 2 - 1)
//				+ '\n' + "runs: " + getNumWithDecades(iCalls)
//				+ '\n' + getTxtRunning(true));
//
//		boolean fBtnStop = RightPanel.grStopBtn.getChildren().contains(RightPanel.btnStop);
//		if (!fBtnStop) RightPanel.grStopBtn.getChildren().add(RightPanel.btnStop);

	}

	public static void resetLongestRandomPath() {
		;;;System.out.println("resetLongestRandomPath()");
		currentLongestPath = new ArrayList<>();
		iCalls = 0;
		RightPanel.setTextGen("");
	}

	public static void doBruteForce() {
		
		long tRet = System.currentTimeMillis() + 20;
		boolean fUpdatePath = false;

		while (!fUpdatePath && tRet > System.currentTimeMillis()) {
			iCalls++;
			ArrayList<Integer> longestPath = BruteForce.doLongestPath(tRet);
			if(longestPath.size() > currentLongestPath.size()) {
				fUpdatePath = true;
				currentLongestPath = longestPath;
				Path.drawPath3d(longestPath, Main.grPathBruteForce);
			}
		}
		RightPanel.updateBruteForce();
//		String txt = "Brute Force:"
//				+ '\n' + "Longest Path Size: " + (currentLongestPath.size() / 2 - 1)
//				+ '\n' + "Found at step: " + getNumWithDecades(BruteForce.iStepAtLongPath)
//				//				+ '\n' + "Steps: " + BruteForce.iStep
//				+ '\n' + "Steps: " + getNumWithDecades(BruteForce.iStep)
//				+ '\n' + getTxtRunning(!BruteForce.fDoneRe);
//		
//		RightPanel.setText(txt);

		boolean fBtnStop = RightPanel.grStopBtn.getChildren().contains(RightPanel.btnStop);
		if (BruteForce.fDoneRe) {
			if (fBtnStop) RightPanel.grStopBtn.getChildren().clear();
		} else {
			if (!fBtnStop) RightPanel.grStopBtn.getChildren().add(RightPanel.btnStop);
		}

	}

	public static void resetBruteForce() {
		currentLongestPath = new ArrayList<>();
		iCalls = 0;
		BruteForce.iniLongestPath();
		RightPanel.setTextGen("");
	}

//	public static String getNumWithDecades(int num) { return getNumWithDecades(num + ""); }
//
//	public static String getNumWithDecades(String num) {
//		String numDec = "";
//		boolean fInsDel = false;
//		do {
//			int l = num.length();
//			numDec = num.substring((l - 3 < 0) ? 0 : l - 3, l) + (fInsDel ? ',' : "") + numDec;
//			fInsDel = true;
//			if (l > 3) num = num.substring(0, l - 3); else num ="";
//		} while (num.length() > 0);
//		return numDec;
//	}
//	
//	public static String getTxtRunning(boolean fRunning) {
//		long t = System.currentTimeMillis();
//		String txt;
//		if (fRunning) {
//			String dots = "";
//			for (int iDot = 0; iDot < ((t / 250) & 0b11); iDot++) dots += '.';
//			txt = "Running" + dots;
//		} else { txt = "Done"; }
//		return txt;
//	}
}
