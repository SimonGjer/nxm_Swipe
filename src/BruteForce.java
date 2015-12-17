import java.util.ArrayList;
import java.util.Stack;


public class BruteForce implements Runnable {


	static final int UP = 0, RIGTHUP = 1, RIGTH = 2, RIGTHDOWN = 3, DOWN = 4, DOWNLEFT = 5, LEFT = 6, LEFTUP = 7;

	static final int[] dCol = new int[]{0, 1, 1, 1, 0, -1, -1, -1 };
	static final int[] dRow = new int[]{-1, -1, 0, 1, 1, 1, 0, -1 };

	public static VertexSimple[][] boardToGraph (char[][] board) {
		int nCol = board.length, nRow = board[0].length;

		VertexSimple[][] vertex = new VertexSimple[nCol][nRow];

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				vertex[iCol][iRow] = new VertexSimple();
			}
		}

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {

				if(iCol + 1 < nCol) {
					if (board[iCol][iRow] == board[iCol + 1][iRow]) {
						vertex[iCol][iRow].edgeTo[RIGTH] = vertex[iCol + 1][iRow];
						vertex[iCol + 1][iRow].edgeTo[LEFT] = vertex[iCol][iRow];
					}
				}

				if(iCol > 0 && iRow + 1 < nRow) {
					if (board[iCol][iRow] == board[iCol - 1][iRow + 1]) {
						vertex[iCol][iRow].edgeTo[DOWNLEFT] = vertex[iCol - 1][iRow + 1];
						vertex[iCol - 1][iRow + 1].edgeTo[RIGTHUP] = vertex[iCol][iRow];
					}
				}

				if(iRow + 1 < nRow) {
					if (board[iCol][iRow] == board[iCol][iRow + 1]) {
						vertex[iCol][iRow].edgeTo[DOWN] = vertex[iCol][iRow + 1];
						vertex[iCol][iRow + 1].edgeTo[UP] = vertex[iCol][iRow];
					}
				}

				if(iCol + 1 < nCol && iRow + 1 < nRow) {
					if (board[iCol][iRow] == board[iCol + 1][iRow + 1]) {
						vertex[iCol][iRow].edgeTo[RIGTHDOWN] = vertex[iCol + 1][iRow + 1];
						vertex[iCol + 1][iRow + 1].edgeTo[LEFTUP] = vertex[iCol][iRow];
					}
				}
			}
		}
		return vertex;
	}


	public static int iStep, iStepSurf;
	public static int iStepAtLongPath, iStepAtLongPathSurf;
	private static ArrayList<Integer> longestPath;
	private static ArrayList<Double> longestPathSurf;
	public static int wSurf, wLPSurf;
	private static boolean[][] fVisitedRe;
	//	private static boolean[] fVisitSurf;
	private static int iColRe, iRowRe;
	//	private static int iVSurf;
	public static boolean fDoneRe, fDoneSurf;
	private static char[][] board;
	private static ArrayList<VertexSuper> vSurfaces;
	private static char iniItem, iniItemSurf;	
	private static long tRet;
	private static boolean fRetDueToTime;
	//	private static int iCall;
	private static ArrayList<Integer> path;
	private static ArrayList<Double> pathSurf;
	private static VertexSuper vCurSurf;
	private static int ivSurf;

	public static void iniLongestPath() {
		Buffer.resetPaths();
		longestPath = new ArrayList<Integer>();
		longestPathSurf = new ArrayList<Double>();
		iStep = 0; iStepAtLongPath = 0;
		iStepSurf = 0; iStepAtLongPathSurf = 0;

		//		iCall = 0;
		fDoneRe = false;
		iColRe = 0; iRowRe = 0;
		int nCol = Board.nCol, nRow = Board.nRow;
		fVisitedRe = new boolean[nCol][nRow];
		//		fVisitSurf = new boolean[nCol * nRow];
		path = new ArrayList<Integer>();
		pathSurf = new ArrayList<Double>();
		fRetDueToTime = false;

		ivSurf = 0;
		fDoneSurf = false;
		wSurf = 0;
		wLPSurf = 0;

		if (vSurfaces != null) for(VertexSuper v : vSurfaces) v.fUsed = false;
	}

	public static ArrayList<Integer> doLongestPath(long tReturn) {
		tRet = tReturn;
		//		iCall++;

		int nCol = Board.nCol, nRow = Board.nRow;
		board = Board.getBoard();
		if (fRetDueToTime) {
			fRetDueToTime = false;
			visitNeighbors(iColRe, iRowRe);
		} else {
			path = new ArrayList<Integer>();
			if (iColRe == nCol) { iColRe = 0; iRowRe++; }
			if (iRowRe == nRow) { fDoneRe = true; }
			if (!fDoneRe) { iniItem = board[iColRe][iRowRe]; visitNeighbors(iColRe, iRowRe); }
			if (!fRetDueToTime) iColRe++;
		}
		return longestPath;
	}

	private static ArrayList<Integer> visitNeighbors(int iCol, int iRow) {
		if (tRet < System.currentTimeMillis()) { fRetDueToTime = true; return longestPath; }
		iStep++;
		int nCol = Board.nCol, nRow = Board.nRow;
		fVisitedRe[iCol][iRow] = true;
		path.add(iCol); path.add(iRow);
		Buffer.addEachPath(path);
		if(path.size() > longestPath.size()) {
			longestPath = new ArrayList<Integer>(path);
			iStepAtLongPath = iStep;
			Buffer.addBestPath(longestPath);
		}

		for(int d = 0; d < 8; d++) {
			int cCol = iCol + dCol[d], cRow = iRow + dRow[d];
			if (cCol >= 0 && cCol < nCol) {
				if (cRow >= 0 && cRow < nRow) {
					if(board[cCol][cRow] == iniItem) {
						if (!fVisitedRe[cCol][cRow]) {
							visitNeighbors(cCol, cRow);
						}
					}
				}
			}
		}
		path.remove(path.size() - 1); path.remove(path.size() - 1);
		fVisitedRe[iCol][iRow] = false;

		return longestPath;
	}


	public static ArrayList<Double> doLongestPathSurf(long tReturn) {
		//		;;;System.out.println("longestPathSurf.size(): " + longestPathSurf.size());
		tRet = tReturn;

		vSurfaces = Super.vSurfaces;
		if (vSurfaces == null) {
			vSurfaces = Super.getSurface(Super.getGraphWithSuperNodesTmp(Board.getBoard()));
			for(VertexSuper v : vSurfaces) v.fUsed = false;
		}

		if (fRetDueToTime) {
			fRetDueToTime = false;
			visitNeighbors(vCurSurf);
		} else {
			pathSurf = new ArrayList<Double>();
			if (ivSurf == vSurfaces.size()) { fDoneSurf = true; }
			if (!fDoneSurf) { vCurSurf = vSurfaces.get(ivSurf); iniItemSurf = vCurSurf.item; visitNeighbors(vCurSurf); }
			if (!fRetDueToTime) ivSurf++;
		}
		return longestPathSurf;
	}

	private static ArrayList<Double> visitNeighbors(VertexSuper v) {
		//		;;;System.out.println("visitNeighbors()");
		//				if (tRet < System.currentTimeMillis()) { fRetDueToTime = true; return longestPathSurf; }
		iStepSurf++;
		//		System.out.println("v.fUsed: " + v.fUsed);
		v.fUsed = true;
		wSurf += v.value;

		pathSurf.add(v.xPos); pathSurf.add(v.yPos);
		Buffer.addEachPathSurf(pathSurf);
		if(wSurf > wLPSurf) {
			wLPSurf = wSurf;
			longestPathSurf = new ArrayList<Double>(pathSurf);
			iStepAtLongPathSurf = iStepSurf;
			Buffer.addBestPathSurf(longestPathSurf);
		}

		for(int i = 0; i < v.edgeTo.size(); i++) {
			VertexSuper vv = v.edgeTo.get(i);
			if(vv.item == iniItemSurf) {
				if (!vv.fUsed) {
					visitNeighbors(vv);
				}
			}
		}
		pathSurf.remove(pathSurf.size() - 1); pathSurf.remove(pathSurf.size() - 1);

		wSurf -= v.value;
		v.fUsed = false;
		return longestPathSurf;
	}

	@Override
	public void run() {	}

}
