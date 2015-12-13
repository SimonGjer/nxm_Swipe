import java.util.ArrayList;
import java.util.Stack;


public class BruteForce implements Runnable {


	static final int UP = 0, RIGTHUP = 1, RIGTH = 2, RIGTHDOWN = 3, DOWN =4, DOWNLEFT = 5, LEFT = 6, LEFTUP = 7;

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


	//	public static void drawGraph(VertexSimple[][] graph) {
	//		ArrayList<Integer[]> s = new ArrayList<>();
	//
	//		int nCol = graph.length, nRow = graph[0].length;
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) {
	//				VertexSimple v = graph[iCol][iRow];
	//				if (v.edgeTo[RIGTH] != null) s.add(new Integer[]{iCol, iRow, iCol + 1, iRow});
	//				if (v.edgeTo[RIGTHDOWN] != null) s.add(new Integer[]{iCol, iRow, iCol + 1, iRow + 1});
	//				if (v.edgeTo[DOWN] != null) s.add(new Integer[]{iCol, iRow, iCol, iRow + 1});
	//				if (v.edgeTo[DOWNLEFT] != null) s.add(new Integer[]{iCol, iRow, iCol - 1, iRow + 1});
	//			}
	//		}
	//		Path.drawPath(s);
	//	}

	

	//	public static ArrayList<Integer> findLongestPath(VertexSimple[][] graph) {
	//		iIter = 0;
	//		Buffer.resetPaths();
	//
	//		longestPath = new ArrayList<Integer>();
	//
	//		int nCol = graph.length, nRow = graph[0].length;
	//		
	//
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) {
	//				visitNeighbors(iCol, iRow, visited, graph, new ArrayList<Integer>());
	//			}
	//		}
	//		System.out.println("Interations done: " + iIter);
	//		return longestPath;
	//	}
	public static int iStep;
	private static ArrayList<Integer> longestPath;
	private static boolean[][] fVisitedRe;
	private static int iColRe, iRowRe;
	public static boolean fDoneRe;
	private static char[][] board;
	private static char iniItem;	
	private static long tRet;
	private static boolean fRetDueToTime;
	private static int iCall;
	private static ArrayList<Integer> path;
	public static int iStepAtLongPath;

	public static void iniLongestPath() {
		longestPath = new ArrayList<Integer>();
		iStep = 0;
		iStepAtLongPath = 0;
		//		iCall = 0;
		Buffer.resetPaths();
		fDoneRe = false;
		int nCol = Board.nCol, nRow = Board.nRow;
		iColRe = 0; iRowRe = 0;
		fVisitedRe = new boolean[nCol][nRow];
		path = new ArrayList<Integer>();
		fRetDueToTime = false;
	}

	public static ArrayList<Integer> doLongestPath(long tReturn) {
		tRet = tReturn;
		//		iCall++;
		
		int nCol = Board.nCol, nRow = Board.nRow;
		board = Board.getBoard();
		if (fRetDueToTime) {
			visitNeighbors(iColRe, iRowRe);
			fRetDueToTime = false;
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






	//	public static VertexSuper[][] getGraphWithSuperNodes(char[][] board) {
	//
	//		int nCol = board.length, nRow = board[0].length;
	//		//		System.out.println("nRow: " + nRow); System.out.println("nCol: " + nCol);
	//
	//		VertexSuper[][] vs = new VertexSuper[nCol][nRow];
	//
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) {
	//				vs[iCol][iRow] = new VertexSuper(iCol, iRow);
	//				vs[iCol][iRow].item = board[iCol][iRow];
	//			}
	//		}
	//
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) {
	//				if(iCol + 1 < nCol)
	//					if (board[iCol][iRow] == board[iCol + 1][iRow]) {
	//						vs[iCol][iRow].edgeToSimple[RIGTH] = vs[iCol + 1][iRow]; vs[iCol + 1][iRow].edgeToSimple[LEFT] = vs[iCol][iRow];
	//					}
	//				if(iCol > 0 && iRow + 1 < nRow)
	//					if (board[iCol][iRow] == board[iCol - 1][iRow + 1]) {
	//						vs[iCol][iRow].edgeToSimple[DOWNLEFT] = vs[iCol - 1][iRow + 1];	vs[iCol - 1][iRow + 1].edgeToSimple[RIGTHUP] = vs[iCol][iRow];
	//					}
	//				if(iRow + 1 < nRow)
	//					if (board[iCol][iRow] == board[iCol][iRow + 1]) {
	//						vs[iCol][iRow].edgeToSimple[DOWN] = vs[iCol][iRow + 1];	vs[iCol][iRow + 1].edgeToSimple[UP] = vs[iCol][iRow];
	//					}
	//				if(iCol + 1 < nCol && iRow + 1 < nRow) 
	//					if (board[iCol][iRow] == board[iCol + 1][iRow + 1]) {
	//						vs[iCol][iRow].edgeToSimple[RIGTHDOWN] = vs[iCol + 1][iRow + 1]; vs[iCol + 1][iRow + 1].edgeToSimple[LEFTUP] = vs[iCol][iRow];
	//					}
	//			}
	//		}
	//		for(int iRow = 0; iRow < nRow; iRow++) 
	//			for(int iCol = 0; iCol < nCol; iCol++)
	//				vs[iCol][iRow].updateSuperEdge();
	//
	//		Stack<VertexSuper> stack = new Stack<>();
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) {
	//				stack.push(vs[iCol][iRow]);
	//			}
	//		}
	//
	//		while(!stack.isEmpty()) {
	//			VertexSuper v = stack.pop();
	//			if (v.getSuperNode() == null) {
	//				if (v.edgeTo.size() <= 2 && v.edgeTo.size() >= 1) {
	//					for (int i = 0; i < v.edgeTo.size(); i++) {
	//						VertexSuper vAdd = v.edgeTo.get(i);
	//						if (vAdd.edgeTo.size() <= 2 && vAdd.edgeTo.size() >= 1) {
	//							//Create SuperNode
	//							int valueTotal = v.value + vAdd.value;
	//							double xSuper = (v.xPos * v.value + vAdd.xPos * vAdd.value) / valueTotal;
	//							double ySuper = (v.yPos * v.value + vAdd.yPos * vAdd.value) / valueTotal;
	//							VertexSuper vSuper = new VertexSuper(xSuper , ySuper);
	//							vSuper.addAbsorbedEdge(v); vSuper.addAbsorbedEdge(vAdd);
	//							vSuper.value = valueTotal;
	//							vSuper.item = v.item;
	//							v.setSuperNode(vSuper); vAdd.setSuperNode(vSuper);
	//
	//							for (VertexSuper vOut : v.edgeTo) {
	//								if (vOut != vAdd) {
	//									vSuper.addEdge(vOut);
	//									//									vOut = vSuper;
	//									for (VertexSuper vOutOut : vOut.edgeTo) {
	//										if (vOutOut == v) {
	//											vOutOut = vSuper;
	//										}
	//									}
	//								}
	//							}
	//							for (VertexSuper vAddOut : vAdd.edgeTo) {
	//								if (vAddOut != v) {
	//									vSuper.addEdge(vAddOut);
	//									//									vAddOut = vSuper;
	//									for (VertexSuper vAddOutOut : vAddOut.edgeTo) {
	//										if (vAddOutOut == vAdd) {
	//											vAddOutOut = vSuper;
	//										}
	//									}
	//								}
	//							}
	//							stack.push(vSuper);
	//
	//
	//							break;
	//						}
	//					}
	//				}
	//			}
	//		}
	//		return vs;
	//	}



	//	public static VertexSuper[][] getGraphWithSuperNodesTmp(char[][] board) {
	//		counter = 0;
	//		int nCol = board.length, nRow = board[0].length;
	//
	//		VertexSuper[][] vs = buildInitialSuperGraph(board);
	//
	//		boolean fAllDone;
	//		do {
	//			fAllDone = true;
	//			for(int iRow = 0; iRow < nRow; iRow++) {
	//				for(int iCol = 0; iCol < nCol; iCol++) {
	//					VertexSuper v = vs[iCol][iRow];
	//					while(v.vSuper != null) v = v.vSuper;
	//					//					if (v.getSuperNode() == null) {
	//					if (v.edgeTo.size() <= 2 && v.edgeTo.size() >= 1) {
	//						for (int i = 0; i < v.edgeTo.size(); i++) {
	//							VertexSuper vAdd = v.edgeTo.get(i);
	//							if (vAdd.edgeTo.size() <= 2 && vAdd.edgeTo.size() >= 1) {
	//								createSuperNode(v, vAdd);
	//								fAllDone = false;
	//								break;
	//							}
	//						}
	//					}
	//				}
	//			}
	//		} while (!fAllDone);
	//		System.out.println("SuperNode Done!");
	//		return vs;
	//	}
	//
	//	private static VertexSuper[][] buildInitialSuperGraph(char[][] board) {
	//		int nCol = board.length, nRow = board[0].length;
	//
	//		VertexSuper[][] vs = new VertexSuper[nCol][nRow];
	//
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) {
	//				vs[iCol][iRow] = new VertexSuper(iCol, iRow);
	//				vs[iCol][iRow].item = board[iCol][iRow];
	//			}
	//		}
	//
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) {
	//				if(iCol + 1 < nCol)
	//					if (board[iCol][iRow] == board[iCol + 1][iRow]) {
	//						vs[iCol][iRow].edgeToSimple[RIGTH] = vs[iCol + 1][iRow]; vs[iCol + 1][iRow].edgeToSimple[LEFT] = vs[iCol][iRow];
	//					}
	//				if(iCol > 0 && iRow + 1 < nRow)
	//					if (board[iCol][iRow] == board[iCol - 1][iRow + 1]) {
	//						vs[iCol][iRow].edgeToSimple[DOWNLEFT] = vs[iCol - 1][iRow + 1];	vs[iCol - 1][iRow + 1].edgeToSimple[RIGTHUP] = vs[iCol][iRow];
	//					}
	//				if(iRow + 1 < nRow)
	//					if (board[iCol][iRow] == board[iCol][iRow + 1]) {
	//						vs[iCol][iRow].edgeToSimple[DOWN] = vs[iCol][iRow + 1];	vs[iCol][iRow + 1].edgeToSimple[UP] = vs[iCol][iRow];
	//					}
	//				if(iCol + 1 < nCol && iRow + 1 < nRow) 
	//					if (board[iCol][iRow] == board[iCol + 1][iRow + 1]) {
	//						vs[iCol][iRow].edgeToSimple[RIGTHDOWN] = vs[iCol + 1][iRow + 1]; vs[iCol + 1][iRow + 1].edgeToSimple[LEFTUP] = vs[iCol][iRow];
	//					}
	//			}
	//		}
	//		for(int iRow = 0; iRow < nRow; iRow++) 
	//			for(int iCol = 0; iCol < nCol; iCol++)
	//				vs[iCol][iRow].updateSuperEdge();
	//
	//		return vs;
	//	}

	//	private static int counter;
	//
	//	private static void createSuperNode(VertexSuper v, VertexSuper vAdd) {
	//		System.out.println("Counter: " + (counter++));
	//		int valueTotalSuper = v.value + vAdd.value;
	//		double xSuper = (v.xPos * v.value + vAdd.xPos * vAdd.value) / valueTotalSuper;
	//		double ySuper = (v.yPos * v.value + vAdd.yPos * vAdd.value) / valueTotalSuper;
	//		VertexSuper vSuper = new VertexSuper(xSuper , ySuper);
	//		vSuper.addAbsorbedEdge(v); vSuper.addAbsorbedEdge(vAdd);
	//		vSuper.value = valueTotalSuper;
	//		vSuper.item = v.item;
	//		v.setSuperNode(vSuper); vAdd.setSuperNode(vSuper);
	//
	//		VertexSuper vCopy = v.getCopyOfVertex();
	//		for (VertexSuper vOut : vCopy.edgeTo) {
	//			if (vOut != vAdd) {
	//				vSuper.addEdge(vOut);
	//				v.removeEdge(vOut); //v.removeEdge(vOut);
	//				v.addEdge(vSuper);//v.addEdge(vSuper);
	//
	//				VertexSuper vOutCopy = vOut.getCopyOfVertex();
	//
	//				for (int iOutOut = 0; iOutOut < vOutCopy.edgeTo.size(); iOutOut++) {
	//					//					if (vOut.edgeTo.get(iOutOut) == v || vOut.edgeTo.get(iOutOut) == vAdd) {
	//					vOut.removeEdge(v); vOut.removeEdge(vAdd); vOut.addEdge(vSuper);
	//					//						vOut.edgeTo.set(iOutOut, vSuper);
	//					System.out.println(iOutOut + ": " +xSuper + "," + ySuper);
	//					//					}
	//				}
	//			}
	//		}
	//		VertexSuper vAddCopy = vAdd.getCopyOfVertex();
	//
	//
	//		for (VertexSuper vAddOut : vAddCopy.edgeTo) {
	//			if (vAddOut != v) {
	//				vSuper.addEdge(vAddOut);
	//				v.removeEdge(vAddOut);
	//				v.addEdge(vSuper);
	//
	//				VertexSuper vAddOutCopy = vAddOut.getCopyOfVertex();
	//
	//				for (int iAddOutOut = 0; iAddOutOut < vAddOutCopy.edgeTo.size(); iAddOutOut++) {
	//					//					if (vAddOut.edgeTo.get(iAddOutOut) == vAdd || vAddOut.edgeTo.get(iAddOutOut) == v) {
	//					//						vAddOut.edgeTo.set(iAddOutOut, vSuper);
	//					vAddOut.removeEdge(v); vAddOut.removeEdge(vAdd); vAddOut.addEdge(vSuper);
	//					//					
	//					//					}
	//				}
	//			}
	//		}
	//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//		boardToGraph(Board.getBoard());
		//		BruteForce.findLongestPath(boardToGraph(Board.getBoard()));

	}


	//	public ArrayList<Integer> run(VertexSimple[][] graph) {
	//		return findLongestPath(graph);
	//	}
}
