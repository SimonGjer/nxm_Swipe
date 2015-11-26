import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Surface { // extends JPanel implements ActionListener{

//	public static char[][] board;
//	public static int dI = 90; // width & height of a single Item
//
//	private final int DELAY = 150;
//	private Timer timer;
//
//	public Surface() {
//		initTimer();
//	}	
//
//	private void initTimer() {
//		timer = new Timer(DELAY, this);
//		timer.start();
//	}
//
//	public Timer getTimer() {
//		return timer;
//	}
//
//
//	private void doDrawing(Graphics g) {
//		System.out.println("doDrawing(Graphics g)");
//		Graphics2D g2d = (Graphics2D) g;
//		board = Board.getBoard();
//
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(0, 0, g2d.getClip().getBounds().width, g2d.getClip().getBounds().height);
//
//		for(int r = 0; r < board[0].length; r++) {
//			for(int c = 0; c < board.length; c++) {
//				char ch = board[c][r];
//				g2d.setColor((((c + r) & 1) == 0) ? Color.GRAY : Color.LIGHT_GRAY);
//				g2d.fillRect(c * dI + 1, r * dI + 1, dI - 1, dI - 1);
//				Image img = Item.getImage(ch);
//				g2d.drawImage(img, c * dI, r * dI, (c + 1) * dI + 1, (r + 1) * dI + 1, 0, 0, 90, 90, null);
//			}
//		}
//		Graph.drawGraph(g2d);
//		Path.drawPath();
//	}
//
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		doDrawing(g);
//	};
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		;;;System.out.println(e.getSource());
//		repaint();
//	}


}
