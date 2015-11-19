public class Board {

	private static char[][] board;
	private static ProbItem pItem = new ProbItem();
	public static int n = 10, m = 10; // n = x = columns , m = y = rows

	public static char[][] getBoard() {
		return board;
	}

	public static char[][] newRndBoard() {
//		;;;System.out.println("newRndBoard() n x m " + n + " x " + m); //**

		char[][] b = new char[n][m];

		for (int r = 0; r < b[0].length; r++)
			for (int c = 0; c < b.length; c++)
				b[c][r] = pItem.getRndItem();

		board = b;
//		System.out.println("newRndBoard() - board[0][0] = " + board[0][0]);
		return board;
	}

	public static char[][] newRndBoard(int n, int m) {
		char[][] b = new char[n][m];

		for (int r = 0; r < b[0].length; r++)
			for (int c = 0; c < b.length; c++)
				b[c][r] = pItem.getRndItem();

		board = b;
		return board;
	}

	public static void rowPlus() { // Should we call it 'addRow' ?
		m++;
		buildBoard();
		for (int c = 0; c < n; c++)
			board[c][m - 1] = pItem.getRndItem();
	}

	public static void colPlus() {
		n++;
		buildBoard();
		for (int r = 0; r < m; r++)
			board[n - 1][r] = pItem.getRndItem();
	}

	public static void rowMinus() {
		if (m > 1) {
			m--;
			buildBoard();
		}
	}

	public static void colMinus() {
		if (n > 1) {
			n--;
			buildBoard();
		}
	}

	public static void buildBoard() {
		char[][] b = new char[n][m];
		int nRow = (b[0].length > board[0].length) ? board[0].length : b[0].length;
		int nCol = (b.length > board.length) ? board.length : b.length;

		for (int r = 0; r < nRow; r++)
			for (int c = 0; c < nCol; c++)
				b[c][r] = board[c][r];

		board = b;
	}
}