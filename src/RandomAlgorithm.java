import java.util.*;

/**
 * Created by jensravn on 10/12/15.
 */

public class RandomAlgorithm {

    static char[][] board = Board.getBoard();
    static LinkedList<VertexSuper> longestPath = new LinkedList<>();

    public static int[] random() {
        VertexSuper[][] vertex = Super.buildInitialSuperGraph(board);

        List<VertexSuper> list = new ArrayList<>();

        for (int col = 0; col < Board.nCol; col++) {
            for (int row = 0; row < Board.nRow; row++) {
                list.add(vertex[col][row]);
            }
        }

        Collections.shuffle(list);

        for (VertexSuper v : list) {
            randomPath(v);
        }

        int[] path = new int[longestPath.size()*2];

        for (int i = 0; i < longestPath.size(); i++) {
            path[i*2]   = (int)longestPath.peek().xPos;
            path[i*2+1] = (int)longestPath.poll().yPos;
        }

        return path;
    }

    public static void randomPath(VertexSuper v) {

        LinkedList<VertexSuper> path = new LinkedList<>();
        VertexSuper[][] vertex = Super.buildInitialSuperGraph(board);

        pickNeighbour(v, path, vertex);

        if (path.size() > longestPath.size()) {
            longestPath = path;
        }
    }

    private static LinkedList<VertexSuper> pickNeighbour (VertexSuper v, LinkedList<VertexSuper> path, VertexSuper[][] vertex) {
        path.add(v);
        if (v.edgeTo.size() > 0) {
            java.util.Random r = new java.util.Random();
            int randomInt = r.nextInt(v.edgeTo.size());
            VertexSuper nextV = v.edgeTo.get(randomInt);
            v.edgeTo.remove(randomInt);
            pickNeighbour(nextV, path, vertex);
        }
        return path;
    }
}