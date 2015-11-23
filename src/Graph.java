import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;


public class Graph {


	public static VertexSuper[][] G;

	//	public static void drawGraph(VertexSuper[][] G) {
	//
	//		;;;System.out.println("G[0][0].xPos: " + G[0][0].xPos + "   G[0][0].yPos: " + G[0][0].yPos);
	//
	//		int nCol = G.length, nRow = G[0].length;
	//
	//		for(int iRow = 0; iRow < nRow; iRow++) {
	//			for(int iCol = 0; iCol < nCol; iCol++) { 
	//
	//			}
	//		}
	//	}

	public static void setGraph(VertexSuper[][] Graph) {
		G = Graph;
	}

	static boolean fTogglePaint = true;

	public static void drawGraph(Graphics2D g2d) {

		;;;System.out.println("G: " + G);
		if (G == null) return;

		if (fTogglePaint) return;

		int nCol = G.length, nRow = G[0].length;

		int dI = ShowBoard.dI;

		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {
				g2d.setColor((((iCol + iRow) & 1) == 0) ? Color.GRAY : Color.LIGHT_GRAY);
				g2d.fillRect(iCol * dI + 1, iRow * dI + 1, dI - 1, dI - 1);
			}
		}
		Image imgRing = Item.getRingImage();
		for(int iRow = 0; iRow < nRow; iRow++) {
			for(int iCol = 0; iCol < nCol; iCol++) {

				VertexSuper v = G[iCol][iRow];

				while (v.vSuper != null) v = v.vSuper;

				char ch = v.item;

				Image img = Item.getImage(ch);
				if (v.absorbedVertex.size() > 0) {
					g2d.drawImage(imgRing, (int)(v.xPos * dI), (int)(v.yPos * dI), (int)((v.xPos + 1) * dI + 1), (int)((v.yPos + 1) * dI + 1), 0, 0, 90, 90, null);
				}

				g2d.drawImage(img, (int)(v.xPos * dI), (int)(v.yPos * dI), (int)((v.xPos + 1) * dI + 1), (int)((v.yPos + 1) * dI + 1), 0, 0, 90, 90, null);
				//				g2d.drawImage(img, iCol * dI, iRow * dI, (iCol + 1) * dI + 1, (iRow + 1) * dI + 1, 0, 0, 90, 90, null);
			}
		}
	}
}


