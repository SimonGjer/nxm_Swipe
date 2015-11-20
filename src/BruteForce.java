import java.util.ArrayList;


public class BruteForce {


	static final int UP = 0, RIGTHUP = 1, RIGTH = 2, RIGTHDOWN = 3, DOWN =4, DOWNLEFT = 5, LEFT = 6, LEFTUP = 7;

	static final int[] dCol = new int[]{0, 1, 1, 1, 0, -1, -1, -1 };
	static final int[] dRow = new int[]{-1, -1, 0, 1, 1, 1, 0, -1 };


	public static void bruteForce(boolean[][] comp) {

	}

	public static Vertex[][] boardToGraph (char[][] board) {
		int nCol = board.length, nRow = board[0].length;
		//		System.out.println("nRow: " + nRow);
		//		System.out.println("nCol: " + nCol);

		Vertex[][] vertex = new Vertex[nCol][nRow];

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				vertex[iCol][iRow] = new Vertex();
			}
		}
		;;;System.out.println("vertex[0][0].edgeTo = " + vertex[0][0].edgeTo);

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


	public static void drawGraph(Vertex[][] graph) {
		ArrayList<Integer[]> s = new ArrayList<>();

		int nCol = graph.length, nRow = graph[0].length;
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				Vertex v = graph[iCol][iRow];
				//				int[] check = new int[] {RIGTH, RIGTHDOWN, DOWN, DOWNLEFT};
				//				for(int i = 0; i < check.length; i++) {
				//					if (v.edgeTo[i] != null) s.add(new int[]{});
				//				}
				if (v.edgeTo[RIGTH] != null) s.add(new Integer[]{iCol, iRow, iCol + 1, iRow});
				if (v.edgeTo[RIGTHDOWN] != null) s.add(new Integer[]{iCol, iRow, iCol + 1, iRow + 1});
				if (v.edgeTo[DOWN] != null) s.add(new Integer[]{iCol, iRow, iCol, iRow + 1});
				if (v.edgeTo[DOWNLEFT] != null) s.add(new Integer[]{iCol, iRow, iCol - 1, iRow + 1});
			}
		}
		Swipe.drawSwipe(s);
	}


	public static ArrayList<Integer> findLongestPath(Vertex[][] graph) {
		int maxLength = 0;

		longestPath = new ArrayList<Integer>();

		int nCol = graph.length, nRow = graph[0].length;
		boolean[][] visited = new boolean[nCol][nRow];

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				visitNeighbors(iCol, iRow, visited, graph, new ArrayList<Integer>());
			}
		}

		return longestPath;
	}

	static ArrayList<Integer> longestPath;

	public static ArrayList<Integer> visitNeighbors(int iCol, int iRow, boolean[][] visited, Vertex[][] graph, ArrayList<Integer> path){
		visited[iCol][iRow] = true;
		path.add(iCol); path.add(iRow);
		if(path.size() > longestPath.size()) { longestPath = new ArrayList<Integer>(path); }

		for(int d = 0; d < 8; d++) {
			if(graph[iCol][iRow].edgeTo[d] != null) {
				if (!visited[iCol + dCol[d]][iRow + dRow[d]]) {
					visitNeighbors(iCol + dCol[d], iRow + dRow[d], visited, graph, path);
				}
			}
		}
		path.remove(path.size() - 1); path.remove(path.size() - 1);
		visited[iCol][iRow] = false;
		return longestPath;
	}

}
