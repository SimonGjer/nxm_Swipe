import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShowBoard extends JPanel {

	public static char[][] board;

	public static int dI = 90; // width & height of a single Item
	public static int wMaxWinSize = 1300;
	public static int hMaxWinSize = 1000;

	public static int wMinWinSize = 600;
	public static int hMinWinSize = 600;

	//	private static JFrame thisFrame;

	public static Random random = new Random();

	//	public ShowBoard() {
	//		initUI();
	//	}
	//
	//	private void initUI() {
	//		final Surface surface = new Surface();
	//		add(surface);
	//		
	//		addWindowListener(new WindowAdapter() {
	//			@Override
	//			public void windowClosing(WindowEvent e) {
	//				Timer timer = surface.getTimer();
	//						timer.stop();
	//			}
	//		});
	//		
	//		setTitle("Points");
	//		setSize(350, 250);
	//		setLocationRelativeTo(null);
	//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	}

	public static JFrame frame = new JFrame();


	public static void main(String[] args) { // Unit Testing
		System.out.println("Main"); //**

		Board.newRndBoard();

		frame.setTitle(Board.nCol + " x " + Board.nRow + " Swipe"); // = new JFrame(Board.nCol + " x " + Board.nRow + " Swipe");

		System.out.println("***");

		MenuPanel menuPanel = new MenuPanel();
		frame.setJMenuBar(menuPanel.createMenuBar());
		frame.setContentPane(menuPanel.createContentPane());

		System.out.println("___");

		ButtonPanel buttonPanel = new ButtonPanel();
		buttonPanel.setOpaque(true);
		frame.add(buttonPanel, BorderLayout.WEST);

		System.out.println("HERTIL OK");

		frame.add(new ShowBoard(), BorderLayout.CENTER);
		newSize();

		System.out.println("~~~~");

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println("...");



		//		while (true) {
		//			//			Board.newRndBoard();
		//			//			Path.resetPath();
		//			//			Graph.setGraph(null);
		//
		//			//			VertexSimple[][] vertex = BruteForce.boardToGraph(Board.getBoard());
		//			//			ArrayList<Integer> longestPath = BruteForce.findLongestPath(vertex);
		//			//			Path.drawPath2(longestPath);
		//
		//			char[][] board = Board.getBoard();
		//			board[0][0] = (char) ('A' + random.nextInt(3)); //board[random.nextInt(board.length)][random.nextInt(board[0].length)] = (char) ('A' + random.nextInt(3));
		//			frame.repaint();
		//			try {
		//				Thread.sleep(20);
		//			} catch (InterruptedException e) {
		//				System.out.println("InterruptedException e = " + e);
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//		}



		//		EventQueue.invokeLater(new Runnable() {
		//			@Override
		//			public void run() {
		//				System.out.println("...");
		//				ShowBoard sb = new ShowBoard();
		//				sb.setVisible(true);
		//			}
		//		});
		//		
		//		new ShowBoard();
	}

	static final int hMenu = 23; //thisFrame.getJMenuBar().getHeight();
	static final int hWinBorder = 38;
	static final int wWinBorder = 16;
	static final int wPanel = 130;

	static int wWin, wWinOld;
	static int hWin, hWinOld;

	static double pScale = 1.0;

	public static void newSize() {
		updataScale();

		frame.setTitle(Board.nCol + " x " + Board.nRow + " Swipe");
		if (wWinOld == wWin && hWinOld == hWin) rePaint(); //The setSize will only repaint if the size of the window change
		else frame.setSize(wWin, hWin);
		wWinOld = wWin; hWinOld = hWin;
	}

	public static void updataScale() {
		pScale = 1.0;
		dI = 90;
		wWin = Board.nCol * dI + wWinBorder + wPanel + 1;
		hWin = Board.nRow * dI + hWinBorder + hMenu + 1;

		if (wWin > wMaxWinSize || hWin > hMaxWinSize)
			pScale = Math.min((double) (wMaxWinSize - wWinBorder - wPanel) / wWin, (double) (hMaxWinSize - hWinBorder - hMenu) / hWin);
		dI = (int) (90 * pScale);

		wWin = Board.nCol * dI + wWinBorder + wPanel + 1;
		hWin = Board.nRow * dI + hWinBorder + hMenu + 1;

		if (wWin < wMinWinSize) wWin = wMinWinSize;
		if (hWin < hMinWinSize) hWin = hMinWinSize;
	}


	//	private static Graphics g;
	private static Graphics2D g2d;

	@Override
	public void paint(Graphics g) {
		System.out.println("paint(Graphics g)"); //**

		Graphics2D g2d = (Graphics2D) g;
		//		this.g = g;
		this.g2d = g2d;
		board = Board.getBoard();
		//		int fontSize = 48;
		//		Font font =  new Font("Verdana", Font.BOLD, fontSize);
		//		g2d.setFont(font);
		//		g2d.setBackground(Color.CYAN);

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, g2d.getClip().getBounds().width, g2d.getClip().getBounds().height);

		for(int r = 0; r < board[0].length; r++) {
			for(int c = 0; c < board.length; c++) {
				char ch = board[c][r];
				g2d.setColor((((c + r) & 1) == 0) ? Color.GRAY : Color.LIGHT_GRAY);
				g2d.fillRect(c * dI + 1, r * dI + 1, dI - 1, dI - 1);
				Image img = Item.getImage(ch);
				g2d.drawImage(img, c * dI, r * dI, (c + 1) * dI + 1, (r + 1) * dI + 1, 0, 0, 90, 90, null);
			}
		}

		if(ButtonPanel.fBF_btn) {
			ArrayList<Integer[]> bPaths = Buffer.getPaths();
			int i = ButtonPanel.iBF_btn;
			if (i < bPaths.size()) {
				Path.setPath(bPaths.get(i));
				ShowBoard.rePaint();
				try { Thread.sleep(250); } catch (InterruptedException ei) {	System.out.println("InterruptedException ei = " + ei); ei.printStackTrace(); }
				ButtonPanel.iBF_btn++;
			} else { ButtonPanel.fBF_btn = false; }
		}

		else if(ButtonPanel.fBFStep_btn) {
			ArrayList<Integer[]> paths = Buffer.getEachPaths();
			int i = ButtonPanel.iBFStep_btn;
			if (i < paths.size()) {
				Path.setPath(paths.get(i));
				ShowBoard.rePaint();
				try { Thread.sleep(20); } catch (InterruptedException ei) {	System.out.println("InterruptedException ei = " + ei); ei.printStackTrace(); }
				ButtonPanel.iBFStep_btn++;
			} else { ButtonPanel.fBFStep_btn = false; }
		}



		Graph.drawGraph(g2d);
		Path.drawPath();
	}

	public static void rePaint() {
		;;;System.out.println("rePaint()");
		frame.repaint();
		//				frame.setVisible(false); // !!!
		//				frame.setVisible(true);
	}


	//	public static Graphics getGraphic() { return g; }
	public static Graphics2D getGraphic2D() { return g2d; }



}



