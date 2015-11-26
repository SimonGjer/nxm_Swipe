import java.util.ArrayList;



public class VertexSuper {
	//	static final int UP = 0, RIGTHUP = 1, RIGTH = 2, RIGTHDOWN = 3, DOWN =4, DOWNLEFT = 5, LEFT = 6, LEFTUP = 7;

//	static final int[] dCol = new int[]{0, 1, 1, 1, 0, -1, -1, -1 };
//	static final int[] dRow = new int[]{-1, -1, 0, 1, 1, 1, 0, -1 };

	VertexSuper[] edgeToSimple = new VertexSuper[8];
	ArrayList<VertexSuper> edgeTo = new ArrayList<VertexSuper>();
	ArrayList<VertexSuper> absorbedVertex = new ArrayList<VertexSuper>();
	int nEdge;
	double xPos, yPos;
	int value = 1;
	char item; //??
	boolean fDrawn = false;
	VertexSuper vSuper = null;

	public VertexSuper(double xPos, double yPos) {
		this.xPos = xPos; this.yPos = yPos;
		this.value = 1;
	}

	public void createEdge(VertexSuper[][] vs, int col, int row, int d) {
		edgeToSimple[d] = vs[col][row];
		vs[col][row].edgeToSimple[(d + 4) % 8] = this; //
	}

	public void updateSuperEdge() {
		for(int i = 0; i < edgeToSimple.length; i++) {
			if (edgeToSimple[i] != null) edgeTo.add(edgeToSimple[i]);
		}
		nEdge = edgeTo.size();
	}

	public void addEdge(VertexSuper vs) {
		for (VertexSuper v : edgeTo) if (v == vs) return;
		edgeTo.add(vs);
	}
	
	public void removeEdge(VertexSuper vs) {
//		for (VertexSuper v : edgeTo) if (v == vs) edgeTo.remove(index);
		for (int i = 0; i < edgeTo.size(); i++) if (edgeTo.get(i) == vs) edgeTo.remove(i);
		}
	

	public void addAbsorbedEdge(VertexSuper vs) {
		absorbedVertex.add(vs);
	}

	public void setSuperNode(VertexSuper f) {
		vSuper = f;
	}
	
	public VertexSuper getSuperNode() {
		return vSuper;
	}

}
