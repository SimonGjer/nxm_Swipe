import java.util.ArrayList;


public class Buffer {

	private static ArrayList<Integer[]> bPath = new ArrayList<>();
	private static ArrayList<Integer[]> ePath = new ArrayList<>();
	
	private static ArrayList<Double[]> sPath = new ArrayList<>();

	private static ArrayList<Character[][]> bBoard = new ArrayList<>();

	//	private boolean fBTooBig = false; 
	//	private boolean fETooBig = false; 


	public static void addBestPath(ArrayList<Integer> path) {
		if (bPath.size() > 100_000) return;
		Integer[] pathCopy = new Integer[path.size()];
		for(int i = 0; i < pathCopy.length; i++) pathCopy[i] = path.get(i);
		bPath.add(pathCopy);
	}

	public static ArrayList<Integer[]> getPaths() { return bPath; }


	public static void addEachPath(ArrayList<Integer> path) {
		if (ePath.size() > 100_000) return;
		Integer[] pathCopy = new Integer[path.size()];
		for(int i = 0; i < pathCopy.length; i++) pathCopy[i] = path.get(i);
		ePath.add(pathCopy);
	}

	public static ArrayList<Integer[]> getEachPaths() { return ePath; }

	
	
	
	public static void addBestPathSurf(ArrayList<Double> path) {
		if (sPath.size() > 100_000) return;
		Double[] pathCopy = new Double[path.size()];
		for(int i = 0; i < pathCopy.length; i++) pathCopy[i] = path.get(i);
		sPath.add(pathCopy);
	}
	public static void addEachPathSurf(ArrayList<Double> path) {
		if (sPath.size() > 100_000) return;
		Double[] pathCopy = new Double[path.size()];
		for(int i = 0; i < pathCopy.length; i++) pathCopy[i] = path.get(i);
		sPath.add(pathCopy);
	}
	public static ArrayList<Double[]> getEachPathSurf() { return sPath; }
	
	
	
	public static void resetPaths() {
		bPath.clear();
		ePath.clear();
		sPath.clear();
	}
}
