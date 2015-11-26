import java.util.ArrayList;


public class Buffer {

	private static ArrayList<Integer[]> bPath = new ArrayList<>();
	private static ArrayList<Integer[]> ePath = new ArrayList<>();
	
	private static ArrayList<Character[][]> bBoard = new ArrayList<>();


	public static void addBestPath(ArrayList<Integer> path) {
		Integer[] pathCopy = new Integer[path.size()];
		for(int i = 0; i < pathCopy.length; i++) pathCopy[i] = path.get(i);
		bPath.add(pathCopy);
	}

	public static ArrayList<Integer[]> getPaths() { return bPath; }

	
	public static void addEachPath(ArrayList<Integer> path) {
		Integer[] pathCopy = new Integer[path.size()];
		for(int i = 0; i < pathCopy.length; i++) pathCopy[i] = path.get(i);
		ePath.add(pathCopy);
	}

	public static ArrayList<Integer[]> getEachPaths() { return ePath; }
	
	public static void resetPaths() {
		bPath.clear();
		ePath.clear();
	}
	
	

}
