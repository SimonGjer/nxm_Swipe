import java.util.ArrayList;
import java.util.Arrays;


public class Component {

	//	private static ArrayList<boolean[][]> components; //[nBoard][x][y]

	private static int nCol, nRow;
	private static boolean[][] compUsed;
	private static char[][] b;
	private static boolean[][] compRet;
	private static Component cComp = new Component();
	public static String txtBigComp = "";
	public static String txtComps = "";

	public static boolean[][][] getComponents(char[][] board) {
		b = board;
		nCol = board.length; nRow = board[0].length;
		compUsed = new boolean[nCol][nRow];
		ArrayList<boolean[][]> components = new ArrayList<boolean[][]>();
		ArrayList<Integer> compSize = new ArrayList<Integer>();

		for (int iRow = 0; iRow < nRow; iRow++) {
			for (int iCol = 0; iCol < nCol; iCol++) {
				if (compUsed[iCol][iRow]) continue;
				components.add(DFS(iCol, iRow));
				compSize.add(size);
			}
		}
		//Sorting...(not nice)
		ComponentsForSort[] tmpSort = new ComponentsForSort[components.size()];
		for(int i = 0; i < components.size(); i++) 
			tmpSort[i] = cComp.new ComponentsForSort(components.get(i), compSize.get(i));

		Arrays.sort(tmpSort);

		boolean[][][] compRet = new boolean[components.size()][0][0];

		txtBigComp = "Components: " + tmpSort.length + "\n\nBiggest Comp. Size: " + tmpSort[0].getSize();
		txtComps = "Components: " + tmpSort.length + '\n';

		for(int i = 0; i < tmpSort.length; i++) {
			compRet[i] = tmpSort[i].getBoard();
			txtComps += "\n" + (i + 1) + ". Component size: " + tmpSort[i].getSize();
			//			System.out.println("Component size: " + tmpSort[i].getSize());
		}
		return compRet;
	}

	public static boolean[][][] getNBigestComponents(char[][] board, int nRet) {
		boolean[][][] components = getComponents(board);
		nRet = Math.min(nRet, components.length);
		boolean[][][] nCompRet = new boolean[nRet][0][0];
		for(int i = 0; i < nRet; i++)
			nCompRet[i] = components[i];
		return nCompRet;
	}

	private static char item;
	private static int size;

	private static boolean[][] DFS(int col, int row) {
		compRet = new boolean[nCol][nRow];
		item = b[col][row];
		compUsed[col][row] = true;
		compRet[col][row] = true;
		size = 1;
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
				size++;
				DFSsub(iCol, iRow);
			}
		}
		return compRet;
	}

	class ComponentsForSort implements Comparable<ComponentsForSort>{
		boolean[][] board;
		int size;

		private ComponentsForSort(boolean[][] board, int size) {
			this.board = board; this.size = size;
		}

		private boolean[][] getBoard() { return board; }
		private int getSize() { return size; }


		@Override
		public int compareTo(ComponentsForSort comp) {
			return comp.size - size;
		}
	}
}
