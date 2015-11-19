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

	protected JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;

	public ButtonPanel() {
		ImageIcon newBtnIcon = createImageIcon("imgs\\right.gif");

		btn1 = new JButton("New Board", newBtnIcon);
		btn1.setVerticalTextPosition(AbstractButton.CENTER);
		btn1.setHorizontalTextPosition(AbstractButton.LEADING);
		btn1.setMnemonic(KeyEvent.VK_N);
		btn1.setActionCommand("new");
		btn1.addActionListener(this);
		btn1.setToolTipText("Creates a new random board");

		btn2 = new JButton("Col +1", newBtnIcon);
		btn2.setVerticalTextPosition(AbstractButton.CENTER);
		btn2.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn2.setActionCommand("colPlus");
		btn2.addActionListener(this);
		btn2.setToolTipText("Adds a column");

		btn3 = new JButton("Col -1", newBtnIcon);
		btn3.setVerticalTextPosition(AbstractButton.CENTER);
		btn3.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn3.setActionCommand("colMinus");
		btn3.addActionListener(this);
		btn3.setToolTipText("Removes a column");

		btn4 = new JButton("Row +1", newBtnIcon);
		btn4.setVerticalTextPosition(AbstractButton.CENTER);
		btn4.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn4.setActionCommand("rowPlus");
		btn4.addActionListener(this);
		btn4.setToolTipText("Adds a row");

		btn5 = new JButton("Row -1", newBtnIcon);
		btn5.setVerticalTextPosition(AbstractButton.CENTER);
		btn5.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn5.setActionCommand("rowMinus");
		btn5.addActionListener(this);
		btn5.setToolTipText("Removes a row");

		btn6 = new JButton("Find nComp", newBtnIcon);
		btn6.setVerticalTextPosition(AbstractButton.CENTER);
		btn6.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn6.setActionCommand("getComp");
		btn6.addActionListener(this);
		btn6.setToolTipText("Find the number of swipe areas (components)");

		btn7 = new JButton("tmp Swipe", newBtnIcon);
		btn7.setVerticalTextPosition(AbstractButton.CENTER);
		btn7.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn7.setActionCommand("tmpSwipe");
		btn7.addActionListener(this);
		btn7.setToolTipText("Test Swipe");





		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridLayout(10,1));
		panelEast.add(btn1);
		panelEast.add(btn2);
		panelEast.add(btn3);
		panelEast.add(btn4);
		panelEast.add(btn5);
		panelEast.add(btn6);
		panelEast.add(btn7);


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
			ArrayList<boolean[][]> components = Component.getComponents(Board.getBoard());
			int[][] s = Swipe.transformComp(components);
			Swipe.drawSwipe(s);
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
