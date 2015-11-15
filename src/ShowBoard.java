import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShowBoard extends JPanel {

	public static char[][] board;

	static ShowBoard sb = new ShowBoard();

	public static int dI = 90; // width & height of Item
	
	public static void main(String[] args) { //Unit Testing
		int n = 8, m = 9; // n = x = columns , m = y = rows
		board = new char[n][m];
		
		

		ProbItem pItem = new ProbItem();

		for(int r = 0; r < board[0].length; r++)
			for(int c = 0; c < board.length; c++)
				board[c][r] = pItem.getRndItem();

		JFrame frame = new JFrame(n + " x " + m + " Swipe");
		frame.add(new ShowBoard());
		frame.setSize(n * dI + 16, m * dI + 38); //16 & 38 border of windoww
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		showBoard(board);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		//		int fontSize = 48;
		//		Font font =  new Font("Verdana", Font.BOLD, fontSize);
		//		g2d.setFont(font);
		//		g2d.setBackground(Color.CYAN);

		g2d.setColor(Color.GRAY);
		Color cGray = Color.GRAY;
		Color cLGray = Color.LIGHT_GRAY;
				g2d.fillRect(0, 0, g2d.getClip().getBounds().width, g2d.getClip().getBounds().height);

		for(int r = 0; r < board[0].length; r++)
			for(int c = 0; c < board.length; c++) {
				char ch = board[c][r];
				g2d.setColor((((c + r) & 1) == 0) ? cGray : cLGray);
				g2d.fillRect(c * dI, r * dI, dI - 1, dI - 1);
				//				g2d.drawString("" + ch, 8 + c * 50, 48 - 4 + r * 50);
				Image img = Item.getImage(ch);
				g2d.drawImage(img, c * dI, r * dI, (c + 1) * dI + 1, (r + 1) * dI + 1, 0, 0, 90, 90, null);
			}
		}

	public static void showBoard(char[][] board) {
		int n = board.length, m = board[0].length; // n = x = columns , m = y = rows
		;;;System.out.println(n + " " + m); //**
	}
}


//public class LinesDrawingExample extends JFrame {
//
//	public LinesDrawingExample() {
//		super("Lines Drawing Demo");
//
//		setSize(600, 600);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//	}
//
//	void drawLines(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
//
//		g2d.drawLine(120, 50, 160, 50);
//
//		g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));
//
//		g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));
//	}
//
//	public void paint(Graphics g) {
//		super.paint(g);
//		drawLines(g);
//	}
//
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new LinesDrawingExample().setVisible(true);
//			}
//		});
//	}
//}