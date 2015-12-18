import java.util.*;

/**
 * Created by jensravn on 10/12/15.
 */

public class RandomAlgorithm {

	static char[][] board;
	static ArrayList<Integer> longestPath;
	static boolean fIniRandom;
	static List<VertexSuper> list;
	static int iList;
	static boolean fRandomDone;	

	public static ArrayList<Integer> random(long tRet) {

		if (fIniRandom) {
			fIniRandom = false;
			fRandomDone = false;
			board = Board.getBoard();
			longestPath = new ArrayList<>();
			VertexSuper[][] graph = Super.buildInitialSuperGraph(board);

			list = new ArrayList<>(); iList = 0;

			for (int col = 0; col < Board.nCol; col++) {
				for (int row = 0; row < Board.nRow; row++) {
					list.add(graph[col][row]);
				}
			}
			Collections.shuffle(list);
		}
		while (iList < list.size()) {
			randomPath(list.get(iList++));
			if (tRet < System.currentTimeMillis()) return longestPath;
		}
		reset();
		return longestPath;
	}

	public static ArrayList<Integer> randomUntilPathLength(int lPath) {

		if (fIniRandom) {
			fIniRandom = false;
			fRandomDone = false;
			board = Board.getBoard();
			longestPath = new ArrayList<>();
			VertexSuper[][] graph = Super.buildInitialSuperGraph(board);

			list = new ArrayList<>(); iList = 0;

			for (int col = 0; col < Board.nCol; col++) {
				for (int row = 0; row < Board.nRow; row++) {
					list.add(graph[col][row]);
				}
			}
			Collections.shuffle(list);
		}
		for(VertexSuper v : list) {
			randomPath(v);
			if (lPath <= longestPath.size()) { fRandomDone = true; return longestPath; }
		}
		return longestPath;
	}


	public static void randomPath(VertexSuper v) {

		ArrayList<Integer> path = new ArrayList<>();
		VertexSuper[][] tempGraph = Super.buildInitialSuperGraph(board);
		VertexSuper tempV = tempGraph[(int) v.xPos][(int) v.yPos];
		pickNeighbour(tempV, path);

		if (path.size() > longestPath.size()) longestPath = path;

	}

	private static ArrayList<Integer> pickNeighbour (VertexSuper v, ArrayList<Integer> path) {
		path.add((int) v.xPos);
		path.add((int) v.yPos);

		Iterator<VertexSuper> iterateNeighbours = v.edgeTo.iterator();

		while (iterateNeighbours.hasNext()) {
			VertexSuper neighbour = iterateNeighbours.next();
			neighbour.removeEdge(v);
		}

		if (v.edgeTo.size() > 0) {
			java.util.Random r = new java.util.Random();
			int randomInt = r.nextInt(v.edgeTo.size());
			VertexSuper nextV = v.edgeTo.get(randomInt);
			pickNeighbour(nextV, path);
		}
		return path;
	}

	public static boolean isDone() {
		return fRandomDone;
	}

	public static void reset() { 
		fIniRandom = true;
		fRandomDone = false;
		iList = 0;
	}
}