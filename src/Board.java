
public class Board {

	public static char[][] board;
	public static ProbItem pItem = new ProbItem();
	public static int n = 8, m = 9; // n = x = columns , m = y = rows


	public static char[][] getBoard() {
		return board;
	}

	public static char[][] newRndBoard() {
		System.out.println("newRndBoard() n x m " + n + " x " + m); //**

		char[][] b = new char[n][m];

		for(int r = 0; r < b[0].length; r++)
			for(int c = 0; c < b.length; c++)
				b[c][r] = pItem.getRndItem();

		board = b;
		System.out.println("newRndBoard() - board[0][0] = " + board[0][0]);
		return board;
	}

	public static char[][] newRndBoard(int n, int m) {
		char[][] b = new char[n][m];

		for(int r = 0; r < b[0].length; r++)
			for(int c = 0; c < b.length; c++)
				b[c][r] = pItem.getRndItem();

		board = b;
		return board;
	}


	public static void rowPlus() {
		m++;
		char[][] b = new char[n][m];
		for(int r = 0; r < b[0].length; r++)
			for(int c = 0; c < b.length; c++)
				b[c][r] = (r <  b[c].length - 1) ? board[c][r] : pItem.getRndItem(); 

				board = b;
	}

	public static void rowMinus() {
		if (m > 1 ) {m--;
		char[][] b = new char[n][m];
		for(int r = 0; r < b[0].length; r++)
			for(int c = 0; c < b.length; c++)
				b[c][r] = board[c][r]; 

		board = b;
		}
	}

	public static void colPlus() {
		n++;
		char[][] b = new char[n][m];
		for(int r = 0; r < b[0].length; r++)
			for(int c = 0; c < b.length; c++)
				b[c][r] = (c < board.length) ? board[c][r] : pItem.getRndItem(); 

				board = b;
	}
	public static void colMinus() {
		if (n > 1 ) {n--;
		char[][] b = new char[n][m];
		for(int r = 0; r < b[0].length; r++)
			for(int c = 0; c < b.length; c++)
				b[c][r] = board[c][r]; 

		board = b;
		}
	}
}
