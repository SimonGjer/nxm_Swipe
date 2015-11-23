import java.util.ArrayList;
import java.util.Stack;


public class BruteForce {


	static final int UP = 0, RIGTHUP = 1, RIGTH = 2, RIGTHDOWN = 3, DOWN =4, DOWNLEFT = 5, LEFT = 6, LEFTUP = 7;

	static final int[] dCol = new int[]{0, 1, 1, 1, 0, -1, -1, -1 };
	static final int[] dRow = new int[]{-1, -1, 0, 1, 1, 1, 0, -1 };

	public static VertexSimple[][] boardToGraph (char[][] board) {
		int nCol = board.length, nRow = board[0].length;
		//		System.out.println("nRow: " + nRow); System.out.println("nCol: " + nCol);

		VertexSimple[][] vertex = new VertexSimple[nCol][nRow];

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				vertex[iCol][iRow] = new VertexSimple();
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


	public static void drawGraph(VertexSimple[][] graph) {
		ArrayList<Integer[]> s = new ArrayList<>();

		int nCol = graph.length, nRow = graph[0].length;
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				VertexSimple v = graph[iCol][iRow];
				if (v.edgeTo[RIGTH] != null) s.add(new Integer[]{iCol, iRow, iCol + 1, iRow});
				if (v.edgeTo[RIGTHDOWN] != null) s.add(new Integer[]{iCol, iRow, iCol + 1, iRow + 1});
				if (v.edgeTo[DOWN] != null) s.add(new Integer[]{iCol, iRow, iCol, iRow + 1});
				if (v.edgeTo[DOWNLEFT] != null) s.add(new Integer[]{iCol, iRow, iCol - 1, iRow + 1});
			}
		}
		Path.drawPath(s);
	}


	public static ArrayList<Integer> findLongestPath(VertexSimple[][] graph) {
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

	public static ArrayList<Integer> visitNeighbors(int iCol, int iRow, boolean[][] visited, VertexSimple[][] graph, ArrayList<Integer> path){
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



	public static VertexSuper[][] getGraphWithSuperNodes(char[][] board) {

		int nCol = board.length, nRow = board[0].length;
		//		System.out.println("nRow: " + nRow); System.out.println("nCol: " + nCol);

		VertexSuper[][] vs = new VertexSuper[nCol][nRow];

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				vs[iCol][iRow] = new VertexSuper(iCol, iRow);
				vs[iCol][iRow].item = board[iCol][iRow];
			}
		}

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				if(iCol + 1 < nCol)
					if (board[iCol][iRow] == board[iCol + 1][iRow]) {
						vs[iCol][iRow].edgeToSimple[RIGTH] = vs[iCol + 1][iRow]; vs[iCol + 1][iRow].edgeToSimple[LEFT] = vs[iCol][iRow];
					}
				if(iCol > 0 && iRow + 1 < nRow)
					if (board[iCol][iRow] == board[iCol - 1][iRow + 1]) {
						vs[iCol][iRow].edgeToSimple[DOWNLEFT] = vs[iCol - 1][iRow + 1];	vs[iCol - 1][iRow + 1].edgeToSimple[RIGTHUP] = vs[iCol][iRow];
					}
				if(iRow + 1 < nRow)
					if (board[iCol][iRow] == board[iCol][iRow + 1]) {
						vs[iCol][iRow].edgeToSimple[DOWN] = vs[iCol][iRow + 1];	vs[iCol][iRow + 1].edgeToSimple[UP] = vs[iCol][iRow];
					}
				if(iCol + 1 < nCol && iRow + 1 < nRow) 
					if (board[iCol][iRow] == board[iCol + 1][iRow + 1]) {
						vs[iCol][iRow].edgeToSimple[RIGTHDOWN] = vs[iCol + 1][iRow + 1]; vs[iCol + 1][iRow + 1].edgeToSimple[LEFTUP] = vs[iCol][iRow];
					}
			}
		}
		for(int iRow = 0; iRow < nRow; iRow++) 
			for(int iCol = 0; iCol < nCol; iCol++)
				vs[iCol][iRow].updateSuperEdge();


		Stack<VertexSuper> stack = new Stack<>();
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				stack.push(vs[iCol][iRow]);
			}
		}
	
		while(!stack.isEmpty()) {
			VertexSuper v = stack.pop();
			if (v.getSuperNode() == null) {
				if (v.nEdge <= 2 && v.nEdge >= 1) {
					for (int i = 0; i < v.edgeTo.size(); i++) {
						VertexSuper vAdd = v.edgeTo.get(i);
						if (vAdd.nEdge <= 2 && vAdd.nEdge >= 1) {
							//Create SuperNode
							int valueTotal = v.value + vAdd.value;
							double xSuper = (v.xPos * v.value + vAdd.xPos * vAdd.value) / valueTotal;
							double ySuper = (v.yPos * v.value + vAdd.yPos * vAdd.value) / valueTotal;
							VertexSuper vSuper = new VertexSuper(xSuper , ySuper);
							vSuper.addAbsorbedEdge(v); vSuper.addAbsorbedEdge(vAdd);
							vSuper.value = valueTotal;
							vSuper.item = v.item;
							v.setSuperNode(vSuper); vAdd.setSuperNode(vSuper);

							for (VertexSuper vOut : v.edgeTo) {
								if (vOut != vAdd) {
									vSuper.addEdge(vOut);
									vOut = vSuper;
									for (VertexSuper vOutOut : vOut.edgeTo) {
										if (vOutOut == v) {
											vOutOut = vSuper;
										}
									}
								}
							}
							for (VertexSuper vAddOut : vAdd.edgeTo) {
								if (vAddOut != v) {
									vSuper.addEdge(vAddOut);
									vAddOut = vSuper;
									for (VertexSuper vAddOutOut : vAddOut.edgeTo) {
										if (vAddOutOut == vAdd) {
											vAddOutOut = vSuper;
										}
									}
								}
							}
							stack.push(vSuper);
							break;
						}
					}
				}
			}
		}
		return vs;
	}
}
