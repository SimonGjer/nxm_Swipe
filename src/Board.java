import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;

import javafx.stage.FileChooser;

public class Board {

	private static char[][] board;
	private static ProbItem pItem = new ProbItem();
	public static int nCol = 7, nRow = 7; // n = x = columns , m = y = rows

	public static Random random = new Random();

	private static double[][] rnd; 

	public static char[][] getBoard() {
		if (board == null) return newRndBoard();
		return board;
	}

	public static char[][] newRndBoard(int nCol, int nRow) {
		Board.nCol = nCol; Board.nRow = nRow;
		return newRndBoard();
	}
	
	public static char[][] newRndBoard() {
		rnd = new double[nCol][nRow];
		for (int iRow = 0; iRow < nRow; iRow++)
			for (int iCol = 0; iCol < nCol; iCol++)
				rnd[iCol][iRow] = random.nextDouble();
		return newBoard(nCol, nRow);
	}

	private static char[][] newBoard(int nCol, int nRow) {
		Board.nCol = nCol; Board.nRow = nRow;
		char[][] b = new char[nCol][nRow];

		for (int iRow = 0; iRow < nRow; iRow++)
			for (int iCol = 0; iCol < nCol; iCol++)
				b[iCol][iRow] = pItem.getItem(rnd[iCol][iRow]);

		board = b;
		return board;
	}

	public static void rowPlus() { // Should we call it 'addRow' ?
		nRow++;
		buildBoard();
		for (int c = 0; c < nCol; c++)
			board[c][nRow - 1] = pItem.getItem(rnd[c][nRow - 1] = random.nextDouble());
		RightPanel.updateTextBoardInfo();
	}

	public static void colPlus() {
		nCol++;
		buildBoard();
		for (int r = 0; r < nRow; r++)
			board[nCol - 1][r] = pItem.getItem(rnd[nCol - 1][r] = random.nextDouble());
		RightPanel.updateTextBoardInfo();
	}

	public static void rowMinus() {
		if (nRow > 1) { nRow--;	buildBoard(); }
		RightPanel.updateTextBoardInfo();
	}

	public static void colMinus() {
		if (nCol > 1) { nCol--;	buildBoard(); }
		RightPanel.updateTextBoardInfo();
	}

	public static void buildBoard() {
		char[][] b = new char[nCol][nRow];
		double[][] tmpRnd = new double[nCol][nRow];
		int nRow = (b[0].length > board[0].length) ? board[0].length : b[0].length;
		int nCol = (b.length > board.length) ? board.length : b.length;

		for (int r = 0; r < nRow; r++)
			for (int c = 0; c < nCol; c++) {
				b[c][r] = board[c][r];
				tmpRnd[c][r] = rnd[c][r];
			}
		board = b;
		rnd = tmpRnd;
	}

	private static Scanner scanner;
	
	public static void openFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt") /*, new ExtensionFilter("All Files", "*.*") */ );
		File selectedFile = fileChooser.showOpenDialog(Main.thisWindow); // 'null' can also be used

		if (selectedFile != null) {
			int sRead = 0;
			int nColRead = 0, nRowRead = 0;
			int iRowRead = 0;
			char[][] boardRead = new char[0][0];

			try {
				scanner = new Scanner(selectedFile);

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine().trim();
					if (line.length() < 1 || line.charAt(0) == '#') continue;
					switch (sRead) {
					case 0:
						if (line.contains("Board dimensions: ")) {
							String[] nums = line.split(" ");
							if(nums.length == 4) {
								nColRead = Integer.parseInt(nums[2]); nRowRead = Integer.parseInt(nums[3]);
								boardRead = new char[nColRead][nRowRead];
								sRead++;
							}
						}
						break;
					case 1:
						String[] chars = line.split(",");
						if (chars.length == nColRead) {
							for (int iColRead = 0; iColRead < nColRead; iColRead++) {
								boardRead[iColRead][iRowRead] = chars[iColRead].charAt(0);
							}
							iRowRead++;
							if (iRowRead == nRowRead) sRead++;
						}
						break;
					}
				}
			} catch (FileNotFoundException e) {	e.printStackTrace(); }
			if(sRead == 2) {
				;System.out.println("Board succesfully opened");
				nCol = nColRead;
				nRow = nRowRead;
				board = boardRead;
				Board3D.update();
			}
		}
		RightPanel.updateTextBoardInfo();
	}

	private static PrintWriter writer;

	public static void saveFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt") /*, new ExtensionFilter("All Files", "*.*") */ );
		File selectedFile = fileChooser.showSaveDialog(Main.thisWindow); // 'null' can also be used

		if (selectedFile != null) {
			//			char[][] board = Board.getBoard();
			try {
				writer = new PrintWriter(selectedFile, "UTF-8");
				writer.println("# Saved Board for Longest Path Problem in Cute vs Evil.");
				writer.println("# Algorithm Design Project (ITU, autumn 2015)");
				writer.println("");
				writer.println("Board dimensions: " + Board.nCol + " " + Board.nRow);

				for (int iRow = 0; iRow < nRow; iRow++) {
					boolean fDelSept = false;
					for (int iCol = 0; iCol < nCol; iCol++) {
						if(fDelSept) writer.print(','); else fDelSept = true;
						writer.print(board[iCol][iRow]);
					}
					writer.println("");
				}
				writer.close();
			} catch (IOException ex) { System.err.println("IOException: " + ex );}
			finally {
				try {writer.close();} catch (Exception ex) { System.err.println("Exception: " + ex); }
			}
		}
	}
}

