import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel implements ActionListener {

	protected JButton btn1, btn2, btn3, btn4, btn5;

	public ButtonPanel() {
		ImageIcon newBtnIcon = createImageIcon("imgs\\right.gif");

		btn1 = new JButton("New Board", newBtnIcon);
		btn1.setVerticalTextPosition(AbstractButton.CENTER);
		btn1.setHorizontalTextPosition(AbstractButton.LEADING);
		btn1.setMnemonic(KeyEvent.VK_N);
		btn1.setActionCommand("new");
		btn1.addActionListener(this);
		btn1.setToolTipText("Creates a new random board");

		btn2 = new JButton("Row +1", newBtnIcon);
		btn2.setVerticalTextPosition(AbstractButton.CENTER);
		btn2.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn2.setActionCommand("rowPlus");
		btn2.addActionListener(this);
		btn2.setToolTipText("Adds a row");

		btn3 = new JButton("Row -1", newBtnIcon);
		btn3.setVerticalTextPosition(AbstractButton.CENTER);
		btn3.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn3.setActionCommand("rowMinus");
		btn3.addActionListener(this);
		btn3.setToolTipText("Removes a row");

		btn4 = new JButton("Col +1", newBtnIcon);
		btn4.setVerticalTextPosition(AbstractButton.CENTER);
		btn4.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn4.setActionCommand("colPlus");
		btn4.addActionListener(this);
		btn4.setToolTipText("Adds a column");

		btn5 = new JButton("Col -1", newBtnIcon);
		btn5.setVerticalTextPosition(AbstractButton.CENTER);
		btn5.setHorizontalTextPosition(AbstractButton.LEADING);
		//		b2.setMnemonic(KeyEvent.VK_N);
		btn5.setActionCommand("colMinus");
		btn5.addActionListener(this);
		btn5.setToolTipText("Removes a column");






		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridLayout(10,1));
		panelEast.add(btn1);
		panelEast.add(btn2);
		panelEast.add(btn3);
		panelEast.add(btn4);
		panelEast.add(btn5);

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
		System.out.println(e.getActionCommand());

		switch (e.getActionCommand()) {
		case "new":
			System.out.println("new...");
			Board.newRndBoard();

			ShowBoard.rePaint();
			break;
		case "rowPlus":
			System.out.println("Row +1...");
			Board.rowPlus();
			ShowBoard.newSize();
//			ShowBoard.setVisibleTrue(true);
			break;
		case "rowMinus":
			System.out.println("Row -1...");
			Board.rowMinus();
			ShowBoard.newSize();
//			ShowBoard.setVisibleTrue(true);
			break;
		case "colPlus":
			System.out.println("Col +1...");
			Board.colPlus();
//			ShowBoard.newBoard();
			ShowBoard.newSize();
			break;
		case "colMinus":
			System.out.println("Col -1...");
			Board.colMinus();
//			ShowBoard.newBoard();
			ShowBoard.newSize();
			break;
		}

	}	
}
