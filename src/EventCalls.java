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

}
