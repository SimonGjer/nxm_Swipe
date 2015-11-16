import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShowBoard extends JPanel {

	public static char[][] board;

	//	static ShowBoard sb = new ShowBoard();

	public static int dI = 90; // width & height of a single Item

	private static JFrame thisFrame;

	public static final int wPanelEast = 150;
	//Unit Testing
	public static void main(String[] args) {
		System.out.println("Main"); //**

		board = Board.newRndBoard();

		JFrame frame = new JFrame(Board.n + " x " + Board.m + " Swipe");
		thisFrame = frame;
		
		MenuPanel menuPanel = new MenuPanel();
		frame.setJMenuBar(menuPanel.createMenuBar());
        frame.setContentPane(menuPanel.createContentPane());
		
        ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.setOpaque(true);
		frame.add(buttonPanel, BorderLayout.EAST);
		
		frame.add(new ShowBoard(), BorderLayout.CENTER);
		newSize(); //frame.setSize(n * dI + 16 + wPanelEast, m * dI + 38); //16 & 38 border of windoww
		
		//		frame.setContentPane(bp);

		//		JPanel panel = new JPanel();
		//		panel.setLayout(new BorderLayout());
		//		panel.add(bp, BorderLayout.EAST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void newSize() {
		board = Board.getBoard();
		
		int hMenu = 23; //thisFrame.getJMenuBar().getHeight();
		System.out.println("hMenu: " + hMenu);
		thisFrame.setSize(Board.n * dI + 16 + wPanelEast, Board.m * dI + 38 + hMenu);
//		thisFrame.pack();
		
		thisFrame.setTitle(Board.n + " x " + Board.m + " Swipe");
	}

	private static Graphics g;
	//	private static Object thisPaint;

	@Override
	public void paint(Graphics g) {

		System.out.println("paint(Graphics g)");

		board = Board.getBoard();
		//		board = Board.newRndBoard();

		System.out.println("board[0][0] = " + board[0][0]);

		this.g = g;
		Graphics2D g2d = (Graphics2D) g;

		//		int fontSize = 48;
		//		Font font =  new Font("Verdana", Font.BOLD, fontSize);
		//		g2d.setFont(font);
		//		g2d.setBackground(Color.CYAN);


		//		Color cGray = Color.GRAY;
		//		Color cLGray = Color.LIGHT_GRAY;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, g2d.getClip().getBounds().width, g2d.getClip().getBounds().height);

		for(int r = 0; r < board[0].length; r++)
			for(int c = 0; c < board.length; c++) {
				char ch = board[c][r];
				g2d.setColor((((c + r) & 1) == 0) ? Color.GRAY : Color.LIGHT_GRAY);
				g2d.fillRect(c * dI, r * dI, dI - 1, dI - 1);
				//				g2d.drawString("" + ch, 8 + c * 50, 48 - 4 + r * 50);
				Image img = Item.getImage(ch);
				g2d.drawImage(img, c * dI, r * dI, (c + 1) * dI + 1, (r + 1) * dI + 1, 0, 0, 90, 90, null);
			}
	}


	public static void rePaint() {
		thisFrame.setVisible(false); // !!!
		thisFrame.setVisible(true);
	}
}



