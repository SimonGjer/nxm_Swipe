
public class ShowBoard {

	public static void main(String[] args) {
		int n = 8, m = 9; // n = x = columns , m = y = rows
		char[][] board = new char[n][m];
		
		ProbItem pItem = new ProbItem();
		
		for(int r = 0; r < board[0].length; r++)
			for(int c = 0; c < board.length; c++)
				board[c][r] = pItem.getRndItem();
		
		
		
		
	}
	
	
	
	public void showBoard(char[][] board) {
		int n = board.length, m = board[0].length;
		
	}
	

}
