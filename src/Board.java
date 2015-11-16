public class Board {

    public static char[][] board;
    public static ProbItem pItem = new ProbItem();
    public static int n = 8, m = 9; // n = x = columns , m = y = rows

    public static char[][] getBoard() {
        return board;
    }

    public static char[][] newRndBoard() {
        System.out.println("newRndBoard() n x m " + n + " x " + m); //**

        char[][] b = new char[n][m];

        for (int r = 0; r < b[0].length; r++)
            for (int c = 0; c < b.length; c++)
                b[c][r] = pItem.getRndItem();

        board = b;
        System.out.println("newRndBoard() - board[0][0] = " + board[0][0]);
        return board;
    }

    public static char[][] newRndBoard(int n, int m) {
        char[][] b = new char[n][m];

        for (int r = 0; r < b[0].length; r++)
            for (int c = 0; c < b.length; c++)
                b[c][r] = pItem.getRndItem();

        board = b;
        return board;
    }

    public static void rowPlus() { // Should we call it 'addRow' ?
        m++;
        buildBoard();
        for (int c = 0; c < n; c++)
            board[c][m - 1] = pItem.getRndItem();
    }

    public static void colPlus() {
        n++;
        buildBoard();
        for (int r = 0; r < m; r++)
            board[n - 1][r] = pItem.getRndItem();
    }

    public static void rowMinus() {
        if (m > 1) {
            m--;
            buildBoard();
        }
    }

    public static void colMinus() {
        if (n > 1) {
            n--;
            buildBoard();
        }
    }

    public static void buildBoard() {
        char[][] b = new char[n][m];
        int rN = (b[0].length > board[0].length) ? board[0].length : b[0].length;
        int cN = (b.length > board.length) ? board.length : b.length;

        for (int r = 0; r < rN; r++)
            for (int c = 0; c < cN; c++)
                b[c][r] = board[c][r];

        board = b;
    }
}