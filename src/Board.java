import java.util.Random;

public class Board {

	private static char[][] board;
	private static ProbItem pItem = new ProbItem();
	public static int nCol = 7, nRow = 7; // n = x = columns , m = y = rows

	public static Random random = new Random();
	
	private static double[][] rnd; 
	
	public static char[][] getBoard() {
		return board;
	}

	public static char[][] newRndBoard() {
		rnd = new double[nCol][nRow];
		for (int iRow = 0; iRow < nRow; iRow++)
			for (int iCol = 0; iCol < nCol; iCol++)
		rnd[iCol][iRow] = random.nextDouble();
		return newRndBoard(nCol, nRow);
	}

	public static char[][] newRndBoard(int n, int m) {
		nCol = n; nRow = m;
		char[][] b = new char[n][m];
		
		for (int r = 0; r < nRow; r++)
			for (int c = 0; c < nCol; c++)
				b[c][r] = pItem.getItem(rnd[c][r]);

		board = b;
		return board;
	}

	public static void rowPlus() { // Should we call it 'addRow' ?
		nRow++;
		buildBoard();
		for (int c = 0; c < nCol; c++)
			board[c][nRow - 1] = pItem.getItem(rnd[c][nRow - 1] = random.nextDouble());
	}

	public static void colPlus() {
		nCol++;
		buildBoard();
		for (int r = 0; r < nRow; r++)
			board[nCol - 1][r] = pItem.getItem(rnd[nCol - 1][r] = random.nextDouble());
	}

	public static void rowMinus() {
		if (nRow > 1) {
			nRow--;
			buildBoard();
		}
	}

	public static void colMinus() {
		if (nCol > 1) {
			nCol--;
			buildBoard();
		}
	}

	public static void buildBoard() {
		char[][] b = new char[nCol][nRow];
		double[][] tmpRnd = new double[nCol][nRow];
		int nRow = (b[0].length > board[0].length) ? board[0].length : b[0].length;
		int nCol = (b.length > board.length) ? board.length : b.length;

		for (int r = 0; r < nRow; r++)
			for (int c = 0; c < nCol; c++) {
				b[c][r] = board[c][r];
				tmpRnd[c][r] = rnd[c][r];
			}
		board = b;
		rnd = tmpRnd;
	}
}