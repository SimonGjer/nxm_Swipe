import java.util.ArrayList;
import java.util.Random;

public class VertexSuper {
	static ArrayList<VertexSuper> vsId = new ArrayList<>();
	static int indexId;
	int id;
	VertexSuper[] edgeToSimple = new VertexSuper[8];
	ArrayList<VertexSuper> edgeTo = new ArrayList<VertexSuper>();
	ArrayList<VertexSuper> vCollapsed = new ArrayList<VertexSuper>(); //Collapsed ~ Consumed ~ Down 
	double xPos, yPos, zPos;
	int value;
	char item;
	boolean fUsed = false;
	VertexSuper vSuper = null;
	int sRuleUsed = 0;

	public VertexSuper(double xPos, double yPos) {
		this(xPos, yPos, 1);
	}

	public VertexSuper(double xPos, double yPos, int value) {
		this.xPos = xPos; this.yPos = yPos;
		this.value = value;
		vsId.add(this);
		id = indexId++;
	}

	public void createEdge(VertexSuper[][] vs, int col, int row, int d) {
		edgeToSimple[d] = vs[col][row];
		vs[col][row].edgeToSimple[(d + 4) % 8] = this; //
	}

	public int getId() {return id;}

	public void resetVsId() {
		vsId.clear(); indexId = 0;
	}
	
	public void updateSuperEdge() {
		for(int i = 0; i < edgeToSimple.length; i++) {
			if (edgeToSimple[i] != null) edgeTo.add(edgeToSimple[i]);
		}
	}

	public void addEdge(VertexSuper vs) {
		for (VertexSuper v : edgeTo) if (v == vs) return;
		edgeTo.add(vs);
	}

	public void removeEdge(VertexSuper vs) {
		for (int i = 0; i < edgeTo.size(); i++) if (edgeTo.get(i) == vs) edgeTo.remove(i);
	}

	public boolean hasEdge(VertexSuper vs) {
		for (VertexSuper v : edgeTo) if (vs == v) return true;
		return false;
	}


	public void addAbsorbedEdge(VertexSuper vs) { vCollapsed.add(vs); }

	public void setSuperNode(VertexSuper f) { vSuper = f; }

	public VertexSuper getSuperNode() {	return vSuper; }

	public VertexSuper getCopyOfVertex() {
		VertexSuper vCopy = new VertexSuper(xPos, yPos, value);
		vCopy.zPos = zPos;

		vCopy.edgeToSimple = new VertexSuper[8];
		for (int i = 0; i < vCopy.edgeToSimple.length; i++) vCopy.edgeToSimple[i] = edgeToSimple[i];
		vCopy.edgeTo = new ArrayList<VertexSuper>(edgeTo);
		vCopy.vCollapsed = new ArrayList<VertexSuper>(vCollapsed);;
		vCopy.item = item;
		vCopy.fUsed = fUsed;
		vCopy.vSuper = vSuper;
		vCopy.sRuleUsed = sRuleUsed;

		return vCopy;
	}
}
