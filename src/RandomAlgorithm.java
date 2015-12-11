import java.util.*;

/**
 * Created by jensravn on 10/12/15.
 */

public class RandomAlgorithm {

    static char[][] board;
    static ArrayList<Integer> longestPath;

    public static ArrayList<Integer> random() {
        board = Board.getBoard();
        longestPath = new ArrayList<>();
        VertexSuper[][] graph = Super.buildInitialSuperGraph(board);

        List<VertexSuper> list = new ArrayList<>();

        for (int col = 0; col < Board.nCol; col++) {
            for (int row = 0; row < Board.nRow; row++) {
                list.add(graph[col][row]);
            }
        }

        Collections.shuffle(list);

        for (VertexSuper v : list) {
            randomPath(v);
        }

        return longestPath;
    }

    public static void randomPath(VertexSuper v) {

        ArrayList<Integer> path = new ArrayList<>();
        VertexSuper[][] tempGraph = Super.buildInitialSuperGraph(board);
        VertexSuper tempV = tempGraph[(int)(v.xPos)][(int)(v.yPos)];
        pickNeighbour(tempV, path);

        if (path.size() > longestPath.size()) {
            longestPath = path;
        }
    }

    private static ArrayList<Integer> pickNeighbour (VertexSuper v, ArrayList<Integer> path) {
        path.add((int)v.xPos);
        path.add((int)v.yPos);
        if (v.edgeTo.size() > 0) {
            java.util.Random r = new java.util.Random();
            int randomInt = r.nextInt(v.edgeTo.size());
            VertexSuper nextV = v.edgeTo.get(randomInt);

            Iterator<VertexSuper> iterateNeighbours = nextV.edgeTo.iterator();

            while (iterateNeighbours.hasNext()) {
                VertexSuper neighbour = iterateNeighbours.next();
                neighbour.removeEdge(nextV);
                iterateNeighbours.remove();
            }

            pickNeighbour(nextV, path);
        }
        return path;
    }
}