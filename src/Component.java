import java.util.ArrayList;


public class Component {

	private static ArrayList<boolean[][]> components; //[nBoard][x][y]

	private static int nCol, nRow;
	private static boolean[][] compUsed;
	private static char[][] b;
	private static boolean[][] compRet;

	public static ArrayList<boolean[][]> getComponents(char[][] board) {
		b = board;
		nCol = board.length; nRow = board[0].length;
		compUsed = new boolean[nCol][nRow];
		components = new ArrayList<boolean[][]>();
		//		int iComp = 0;

		//		ArrayList<CComponent> lComps;

		for (int iRow = 0; iRow < nRow; iRow++)
			for (int iCol = 0; iCol < nCol; iCol++) {
				if (compUsed[iCol][iRow]) continue;
//								compRet = DFS(iCol, iRow);
				components.add(DFS(iCol, iRow));
				//				iComp++;
				
//				;;;Swipe.drawSwipe(Swipe.transformComp(components)); ShowBoard.rePaint();
			}
		return components;
	}

	public static boolean[][][] getNBigestComponents(Board board, int nRet) {
		return null;
	}

	public static boolean[][] getBigestComponent(Board board) {
		return null;
	}

	//	private static boolean[][] bDFSret;
	//	private static boolean[][] cUsedDFS;;

	private static char item;

	private static boolean[][] DFS(int col, int row) {
		compRet = new boolean[nCol][nRow];
		item = b[col][row];
		compUsed[col][row] = true;
		compRet[col][row] = true;
		return DFSsub(col, row);
	}
	private static boolean[][] DFSsub(int col, int row) {
		int aCol = col - 1, bCol = col + 1, aRow = row - 1, bRow = row + 1;
		if (aCol < 0) aCol = 0;
		if (aRow < 0) aRow = 0;
		if (bCol >= nCol) bCol = nCol - 1;
		if (bRow >= nRow) bRow = nRow - 1;


		for(int iRow = aRow; iRow <= bRow; iRow++) {
			for(int iCol = aCol; iCol <= bCol; iCol++) {
				if (iCol == col && iRow == row) continue;
				if (b[iCol][iRow] != item) continue;
				if (compUsed[iCol][iRow]) continue;
				compUsed[iCol][iRow] = true;
				compRet[iCol][iRow] = true;
				DFSsub(iCol, iRow);
			}
		}
		return compRet;
	}

//	class CComponent implements Comparable<CComponent>{
//		int size;
//		boolean[][] board;
//
//		@Override
//		public int compareTo(CComponent c) {
//			return size - c.size;
//		}
//	}

}
