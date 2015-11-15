
public class ShowBoard {

	public static void main(String[] args) { //Unit Testing
		int n = 8, m = 9; // n = x = columns , m = y = rows
		char[][] board = new char[n][m];
		
		ProbItem pItem = new ProbItem();
		
		for(int r = 0; r < board[0].length; r++)
			for(int c = 0; c < board.length; c++)
				board[c][r] = pItem.getRndItem();
		
		
		showBoard(board);
		
		
	}
	
	
	
	public static void showBoard(char[][] board) {
		int n = board.length, m = board[0].length; // n = x = columns , m = y = rows
		;;;System.out.println(n + " " + m); //**
		
		
		
		
	}
	

}
