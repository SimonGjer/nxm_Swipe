import java.util.Random;


public class ProbItem {

	static int n;
	static int[] fqs;
	static char[] chs;
	static char[] chRet;
	static int sumFre = 0;

	static Random random = new Random();

	public ProbItem() {
		n = 8;
		fqs = new int[n];
		chs = new char[n];
		chs[0] = 'A'; fqs[0] = 1; //Initial frequency
		chs[1] = 'B'; fqs[1] = 1;
		chs[2] = 'C'; fqs[2] = 1;
		chs[3] = 'D'; fqs[3] = 0;
		chs[4] = 'E'; fqs[4] = 0;
		chs[5] = 'F'; fqs[5] = 0;
		chs[6] = 'G'; fqs[6] = 0;
		chs[7] = 'H'; fqs[7] = 0;

		createChRet();
	}
	
	public static void minus(char ch) { change(ch, -1); }
		public static void plus(char ch) { change(ch, 1); }
	
	public static void change(char ch, int v) {
		if (sumFre + v < 1) return;
		int i = (ch - 'A') % n;
		if (i < 0) i += n;
		if (fqs[i] + v < 0) return;
		fqs[i] += v;
		sumFre += v;
		createChRet();
	}

	/**
	 * @param frequencies
	 * @param chs
	 */
	public ProbItem(int[] frequencies, char[] chs) {
		this.fqs = frequencies; this.chs = chs;
		createChRet();
	}

	private static void createChRet() {
		sumFre = 0;
		for (int f : fqs) sumFre += f;

		chRet = new char[sumFre];
		int iCh = 0;
		for (int i = 0; i < fqs.length; i++)
			for (int j = 0; j < fqs[i]; j++)
				chRet[iCh++] = chs[i];
	}

	public char getItem(double rnd) {
		return chRet[(int) (rnd * chRet.length)];
	}
	
	/** Returns a random item, as if the item was drawn from a sack of items (the drawn item is put into the sack again).
	 * @return a random item
	 */
	public char getRndItem() {
		return chRet[random.nextInt(chRet.length)];
	}
}
