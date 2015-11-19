import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel implements ActionListener {

	protected JButton[] btn;

	//	protected JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;

	public ButtonPanel() {

		int nBtn = 8;
		JButton[] btn = new JButton[nBtn];

		int iBtn = 0;
		ImageIcon newBtnIcon = createImageIcon("." + "\\imgs\\right.gif");

		btn[iBtn] = new JButton("New Board", newBtnIcon);
		btn[iBtn].setVerticalTextPosition(AbstractButton.CENTER);
		btn[iBtn].setHorizontalTextPosition(AbstractButton.LEADING);
		btn[iBtn].setMnemonic(KeyEvent.VK_N);
		btn[iBtn].setActionCommand("new");
		btn[iBtn].addActionListener(this);
		btn[iBtn].setToolTipText("Creates a new random board");
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
		//		b2.setMnemonic(KeyEvent.VK_N);
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




		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridLayout(10,1));
		for(int i=0; i < iBtn; i++) 
			panelEast.add(btn[i]);
		//		panelEast.add(btn2);
		//		panelEast.add(btn3);
		//		panelEast.add(btn4);
		//		panelEast.add(btn5);
		//		panelEast.add(btn6);
		//		panelEast.add(btn7);


		add(panelEast);


	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ButtonPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//		System.out.println(e.getActionCommand());

		switch (e.getActionCommand()) {
		case "new":
			System.out.println("new...");
			Board.newRndBoard();
			Swipe.resetSwipe();
			ShowBoard.rePaint();
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
			int[][] s = Swipe.transformComp(components);
			Swipe.drawSwipe(s);
			ShowBoard.rePaint();
			break;
		case "bigComp"://Show the number of component
			boolean[][][] compBig1 = Component.getNBigestComponents(Board.getBoard(), 1);
			int[][] sBig1 = Swipe.transformComp(compBig1);
			Swipe.drawSwipe(sBig1);
			ShowBoard.rePaint();
			break;
		case "tmpSwipe":
			//			for (int i = 0; i < 100; i++) 
			Swipe.rndSwipe();
			ShowBoard.rePaint();
			break;

		}

	}	
}
