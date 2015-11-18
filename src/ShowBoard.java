import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShowBoard extends JPanel {

	public static char[][] board;

	public static int dI = 90; // width & height of a single Item
	public static int wMaxWinSize = 1200;
	public static int hMaxWinSize = 900;

	public static int wMinWinSize = 600;
	public static int hMinWinSize = 600;

	private static JFrame thisFrame;

	//	public static final int wPanel = 106 + 1;
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
		frame.add(buttonPanel, BorderLayout.WEST);

		frame.add(new ShowBoard(), BorderLayout.CENTER);
		newSize();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//	static int wWin;
	//	static int hWin;
	static final int hMenu = 23; //thisFrame.getJMenuBar().getHeight();
	static final int hWinBorder = 38;
	static final int wWinBorder = 16;
	static final int wPanel = 110;

	static int wWin, wWinOld;
	static int hWin, hWinOld;

	static double pScale = 1.0;

	public static void newSize() {
//		board = Board.getBoard();

		updataScale(pScale);

		thisFrame.setSize(wWin, hWin);
		thisFrame.setTitle(Board.n + " x " + Board.m + " Swipe");
		if (wWinOld == wWin || hWinOld == hWin) rePaint();
		wWinOld = wWin; hWinOld = hWin;
	}

	public static void updataScale(double pScale) {
		pScale = 1.0;
		dI = 90;
		wWin = Board.n * dI + wWinBorder + wPanel + 1;
		hWin = Board.m * dI + hWinBorder + hMenu + 1;

		if (wWin > wMaxWinSize || hWin > hMaxWinSize)
			pScale = Math.min((double) (wMaxWinSize - wWinBorder - wPanel) / wWin,
					(double) (hMaxWinSize - hWinBorder - hMenu) / hWin);
		dI = (int) (90 * pScale);
		
		wWin = Board.n * dI + wWinBorder + wPanel + 1;
		hWin = Board.m * dI + hWinBorder + hMenu + 1;
		
		if (wWin < wMinWinSize) wWin = wMinWinSize;
		if (hWin < hMinWinSize) hWin = hMinWinSize;
		
		
	}


	private static Graphics g;

	@Override
	public void paint(Graphics g) {

		System.out.println("paint(Graphics g)"); //**

		board = Board.getBoard();

		System.out.println("board[0][0] = " + board[0][0]);

		this.g = g;
		Graphics2D g2d = (Graphics2D) g;

		//		int fontSize = 48;
		//		Font font =  new Font("Verdana", Font.BOLD, fontSize);
		//		g2d.setFont(font);
		//		g2d.setBackground(Color.CYAN);

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, g2d.getClip().getBounds().width, g2d.getClip().getBounds().height);

		for(int r = 0; r < board[0].length; r++)
			for(int c = 0; c < board.length; c++) {
				char ch = board[c][r];
				g2d.setColor((((c + r) & 1) == 0) ? Color.GRAY : Color.LIGHT_GRAY);
				g2d.fillRect(c * dI + 1, r * dI + 1, dI - 1, dI - 1);
				Image img = Item.getImage(ch);
				g2d.drawImage(img, c * dI, r * dI, (c + 1) * dI + 1, (r + 1) * dI + 1, 0, 0, 90, 90, null);
			}
	}


	public static void rePaint() {
		thisFrame.setVisible(false); // !!!
		thisFrame.setVisible(true);
	}
}



