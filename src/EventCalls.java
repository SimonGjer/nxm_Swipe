import java.util.ArrayList;

import javafx.scene.paint.Color;


public class EventCalls {
	public static int iCall, iCallSurf;
	public static long tSumRandom, tSumBrute, tSumBruteSurf;


	public static ArrayList<Integer> currentLongestPath = new ArrayList<>();
	public static ArrayList<Double> currentLongestPathSurf = new ArrayList<>();

	public static void doRandom() {
		long t = System.currentTimeMillis();
		boolean fUpdatePath = false;

		while (!fUpdatePath && t + 20 > System.currentTimeMillis()) {
			iCall++;
			long t1 = System.currentTimeMillis();
			ArrayList<Integer> longestPath = RandomAlgorithm.random();
			long t2 = System.currentTimeMillis();
			tSumRandom += t2 - t1;
			if(longestPath.size() > currentLongestPath.size()) {
				fUpdatePath = true;
				currentLongestPath = longestPath;
				Path.drawPath3d(longestPath, Path.grPathRnd);
			}
		}
		RightPanel.updateRandom();
	}

	public static void resetLongestRandomPath() {
		currentLongestPath = new ArrayList<>();
		iCall = 0;
		tSumRandom = 0;
		RightPanel.setTextGen("");
	}



	public static void doBruteForce() {

		long tRet = System.currentTimeMillis() + 20;
		boolean fUpdatePath = false;

		while (!fUpdatePath && tRet > System.currentTimeMillis()) {
			iCall++;
			long t1 = System.currentTimeMillis();
			ArrayList<Integer> longestPath = BruteForce.findLongestPath(tRet);
			long t2 = System.currentTimeMillis();
			tSumBrute += t2 - t1;
			if(longestPath.size() > currentLongestPath.size()) {
				fUpdatePath = true;
				currentLongestPath = longestPath;
				Path.drawPath3d(longestPath, Path.grPathBruteForce, Color.ORANGE);
			}
		}
		RightPanel.updateBruteForce();

		boolean fBtnStop = RightPanel.grStopBtn.getChildren().contains(RightPanel.btnStop);
		if (BruteForce.fDoneRec) {
			if (fBtnStop) RightPanel.grStopBtn.getChildren().clear();
		} else {
			if (!fBtnStop) RightPanel.grStopBtn.getChildren().add(RightPanel.btnStop);
		}

	}

	public static void resetBruteForce() {
		currentLongestPath = new ArrayList<>(); //**
		iCall = 0;
		BruteForce.iniLongestPath();
		tSumBrute = 0;
		RightPanel.setTextGen(""); //**
	}



	public static void doBruteForceSurf() {

		long tRet = System.currentTimeMillis() + 20;
		boolean fUpdatePath = false;

		while (!fUpdatePath && tRet > System.currentTimeMillis()) {
			iCallSurf++;
			long t1 = System.currentTimeMillis();
			ArrayList<Double> longestPathSurf = BruteForce.doLongestPathSurf(tRet);
			long t2 = System.currentTimeMillis();
			tSumBruteSurf += t2 - t1;
			if(longestPathSurf.size() > currentLongestPathSurf.size()) {
				fUpdatePath = true;
				currentLongestPathSurf = longestPathSurf;
				;;;System.out.println("currentLongestPathSurf.size(): " + currentLongestPathSurf.size());
				//				;;;Path.drawDoublePath3d(longestPathSurf, Path.grPathBruteForce);;;
				Path.drawDoublePath3d(longestPathSurf, Path.grPathSurface);
			}
		}
		RightPanel.updateBruteForceSurf();

		boolean fBtnStop = RightPanel.grStopBtn.getChildren().contains(RightPanel.btnStop);
		if (BruteForce.fDoneSurf) {
			if (fBtnStop) RightPanel.grStopBtn.getChildren().clear();
		} else {
			if (!fBtnStop) RightPanel.grStopBtn.getChildren().add(RightPanel.btnStop);
		}
	}
	public static void resetBruteForceSurf() {
		currentLongestPathSurf = new ArrayList<>();
		iCallSurf = 0;
		BruteForce.iniLongestPath();
		tSumBruteSurf = 0;
		RightPanel.setTextGen(""); //**
	}
}
