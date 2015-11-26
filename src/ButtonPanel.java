import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel implements ActionListener, MouseListener {

	protected JButton[] btn;

	//	protected JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;

	public ButtonPanel() {

		int nBtn = 14;
		JButton[] btn = new JButton[nBtn];

		int iBtn = 0;

		ImageIcon iconTest = new ImageIcon("imgs/right.gif");

		btn[iBtn] = new JButton("New Board", iconTest);
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		btn[iBtn].setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("new");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Creates a new random board");
		iBtn++;

		//Item SubPanel
		btn[iBtn] = new JButton("Item freq");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		btn[iBtn].setMnemonic(KeyEvent.VK_F);
		btn[iBtn].setActionCommand("itemPopUp");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Change the frequencies of items");
		iBtn++;

		btn[iBtn] = new JButton("Col +1");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("colPlus");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Adds a column");
		iBtn++;

		btn[iBtn] = new JButton("Col -1");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("colMinus");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Removes a column");
		iBtn++;

		btn[iBtn] = new JButton("Row +1");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("rowPlus");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Adds a row");
		iBtn++;

		btn[iBtn] = new JButton("Row -1");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("rowMinus");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Removes a row");
		iBtn++;

		btn[iBtn] = new JButton("Find nComp");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("getComp");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Find the number of swipe areas (components)");
		iBtn++;

		btn[iBtn] = new JButton("Bigest Comp");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		btn[iBtn].setMnemonic(KeyEvent.VK_B);
		btn[iBtn].setActionCommand("bigComp");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Find the biggest swipe area (component)");
		iBtn++;

		btn[iBtn] = new JButton("tmp Swipe");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("tmpSwipe");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Test Swipe");
		iBtn++;

		btn[iBtn] = new JButton("Brute Force");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("BruteForce");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Does a DFS-Brute Force on the bigest component to find the longest swipe");
		iBtn++;

		btn[iBtn] = new JButton("Path Progress");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("Path Progress");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Show the progression of the best solution from the Brute Force methods");
		iBtn++;
		
		btn[iBtn] = new JButton("Path Steps");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("Path Steps");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Show the progression of the Brute Force methods");
		iBtn++;

		btn[iBtn] = new JButton("SuperNode");
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("SuperNode");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Find Super Nodes in a graph");
		iBtn++;

		//		btn[iBtn] = new JButton("Paint");
		//		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		//		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		//		//		b2.setMnemonic(KeyEvent.VK_N);
		//		btn[iBtn].setActionCommand("paint");
		//		btn[iBtn].addActionListener(this);
		//		btn[iBtn].setToolTipText("Test paint");
		//		iBtn++;

		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridLayout(30,1));

		for(int i = 0; i < iBtn; i++) 
			panelEast.add(btn[i]);

		add(panelEast);

	}

	private static JFrame frameItem;

	public static int iBF_btn = 0;
	public static boolean fBF_btn = false; 
	
	public static int iBFStep_btn = 0;
	public static boolean fBFStep_btn = false; 
	
	public void actionPerformed(ActionEvent e) {
		//		System.out.println(e.getActionCommand());

		switch (e.getActionCommand()) {
		case "new":
			System.out.println("new...");
			Board.newRndBoard();
			Path.resetPath();
			Graph.setGraph(null);
			ShowBoard.rePaint();
			break;
		case "itemPopUp":
			System.out.println("Item PopUp...");
			if (frameItem == null) {
				JFrame frame = new JFrame("Item Change");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(350, 250);
				frame.getContentPane().add(getItemJPanel(), BorderLayout.CENTER);
				//			frame.pack();
				frame.setVisible(true);
				frameItem = frame;
			}
			break;
		case "rowPlus":
			System.out.println("Row +1...");
			Board.rowPlus();
			ShowBoard.newSize();
			break;
		case "rowMinus":
			System.out.println("Row -1...");
			Board.rowMinus();
			ShowBoard.newSize();
			break;
		case "colPlus":
			System.out.println("Col +1...");
			Board.colPlus();
			ShowBoard.newSize();
			break;
		case "colMinus":
			System.out.println("Col -1...");
			Board.colMinus();
			ShowBoard.newSize();
			break;
		case "getComp"://Show the number of component
			boolean[][][] components = Component.getComponents(Board.getBoard());
			int[][] s = Path.compToPath(components);
			Path.drawPath(s);
			ShowBoard.rePaint();
			break;
		case "bigComp"://Show the number of component
			boolean[][][] compBig1 = Component.getNBigestComponents(Board.getBoard(), 1);
			int[][] sBig1 = Path.compToPath(compBig1);
			Path.drawPath(sBig1);
			ShowBoard.rePaint();
			break;
		case "tmpSwipe":
			Path.rndPath();
			ShowBoard.rePaint();
			break;
		case "BruteForce":
			//			Thread t = new Thread(new BruteForce());
			//			t.run();
			VertexSimple[][] vertex = BruteForce.boardToGraph(Board.getBoard());
			ArrayList<Integer> longestPath = BruteForce.findLongestPath(vertex);
			Path.drawPath2(longestPath);
			ShowBoard.rePaint();
			break;
		case "Path Progress":
			iBF_btn = 0;
			fBF_btn = true;
			ShowBoard.rePaint();
			break;
		case "Path Steps":
			;;;System.out.println("BF-Steps");
			iBFStep_btn = 0;
			fBFStep_btn = true;
			ShowBoard.rePaint();
			break;
		case "SuperNode":
			VertexSuper[][] G = BruteForce.getGraphWithSuperNodesTmp(Board.getBoard());
			Graph.setGraph(G);
			ShowBoard.rePaint();
			Graph.fTogglePaint = !Graph.fTogglePaint;
			break;
			//		case "paint":
			//			ShowBoard.rePaint();
			//			break;
		}
	}

	//	class ItemPane extends JDialog implements ActionListener, PropertyChangeListener {	
	public JPanel getItemJPanel() {

		ImageIcon iconApple_24px = new ImageIcon("imgs/Apple_24px.png");
		JLabel lblApple = new JLabel(iconApple_24px);
		ImageIcon iconChestnut_24px = new ImageIcon("imgs/Chestnut_24px.png");
		JLabel lblChestnut = new JLabel(iconChestnut_24px);
		ImageIcon iconBlueBerry_24px = new ImageIcon("imgs/BlueBerry_24px.png");
		JLabel lblBlueBerry = new JLabel(iconBlueBerry_24px);
		ImageIcon iconAcorn_24px = new ImageIcon("imgs/Acorn_24px.png");
		JLabel lblAcorn = new JLabel(iconAcorn_24px);


		ImageIcon iconMinus = new ImageIcon("imgs/Minus_24px.png");
		JButton btnMinus1 = new JButton("", iconMinus);
		JButton btnMinus2 = new JButton("", iconMinus);
		JButton btnMinus3 = new JButton("", iconMinus);
		JButton btnMinus4 = new JButton("", iconMinus);

		ImageIcon iconPlus = new ImageIcon("imgs/Plus_24px.png");
		JButton btnPlus1 = new JButton("", iconPlus);
		JButton btnPlus2 = new JButton("", iconPlus);
		JButton btnPlus3 = new JButton("", iconPlus);
		JButton btnPlus4 = new JButton("", iconPlus);

		btnMinus1.setName("AppleMinus"); btnMinus2.setName("ChestnutMinus"); btnMinus3.setName("BlueBerryMinus"); btnMinus4.setName("AcornMinus");
		btnPlus1.setName("ApplePlus"); btnPlus2.setName("ChestnutPlus"); btnPlus3.setName("BlueBerryPlus"); btnPlus4.setName("AcornPlus");

		btnMinus1.addMouseListener(this); btnMinus2.addMouseListener(this); btnMinus3.addMouseListener(this); btnMinus4.addMouseListener(this);
		btnPlus1.addMouseListener(this); btnPlus2.addMouseListener(this); btnPlus3.addMouseListener(this); btnPlus4.addMouseListener(this);

		String txt = "<html><head> <style> h1 {text-align:center;} </style> <h1>" + "_   " + ProbItem.fqs[0] + "</h1> </head> <body> </html>";
		JLabel lblAppleNum = new JLabel(txt);

		//		JButton btnApplelblAppleNumNum = new JButton("" + ProbItem.fqs[0]);
		JButton btnChestnutNum = new JButton("" + ProbItem.fqs[2]);
		JButton btnBlueBerryNum = new JButton("" + ProbItem.fqs[1]);
		JButton btnAcornNum = new JButton("" + ProbItem.fqs[7]);

		JButton btnApplePro = new JButton((ProbItem.fqs[0] * 100 / ProbItem.sumFre) + "%");
		JButton btnChestnutPro = new JButton((ProbItem.fqs[2] * 100 / ProbItem.sumFre) + "%");
		JButton btnBlueBerryPro = new JButton((ProbItem.fqs[1] * 100 / ProbItem.sumFre) + "%");
		JButton btnAcornPro = new JButton((ProbItem.fqs[7] * 100 / ProbItem.sumFre) + "%");

		JPanel panelItems = new JPanel();
		panelItems.setLayout(new GridLayout(4, 5));

		panelItems.add(lblApple); panelItems.add(btnMinus1); panelItems.add(btnPlus1); panelItems.add(lblAppleNum); panelItems.add(btnApplePro);
		panelItems.add(lblChestnut); panelItems.add(btnMinus2); panelItems.add(btnPlus2); panelItems.add(btnChestnutNum); panelItems.add(btnChestnutPro);
		panelItems.add(lblBlueBerry); panelItems.add(btnMinus3); panelItems.add(btnPlus3); panelItems.add(btnBlueBerryNum); panelItems.add(btnBlueBerryPro);
		panelItems.add(lblAcorn); panelItems.add(btnMinus4); panelItems.add(btnPlus4); panelItems.add(btnAcornNum); panelItems.add(btnAcornPro);

		return panelItems;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//		;;;System.out.println("mouseClicked: " + e.getComponent().getName());
		switch (e.getComponent().getName()) {
		case "AppleMinus": ProbItem.minus('A'); break;
		case "ApplePlus": ProbItem.plus('A'); break;
		case "ChestnutMinus": ProbItem.minus('C'); break;
		case "ChestnutPlus": ProbItem.plus('C'); break;
		case "BlueBerryMinus": ProbItem.minus('B'); break;
		case "BlueBerryPlus": ProbItem.plus('B'); break;
		case "AcornMinus": ProbItem.minus('H'); break;
		case "AcornPlus": ProbItem.plus('H'); break;
		}
		System.out.println("ProbItem.fqs[0]" + ProbItem.fqs[0]);
		frameItem.getContentPane().add(getItemJPanel(), BorderLayout.CENTER);
		frameItem.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//		;;;System.out.println("mouseEntered");
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		//		;;;System.out.println("mouseExited");
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		//		;;;System.out.println("mousePressed");
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//		;;;System.out.println("mouseReleased");
		// TODO Auto-generated method stub

	}
}



