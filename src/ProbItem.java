import java.util.Random;


public class ProbItem {

	public static void main(String[] args) { //Unit Testing

		ProbItem pi = new ProbItem();

		for(int i = 0; i < 20; i++)
			System.out.println("Random Item: " + pi.getRndItem());

		System.out.println();

		int[] frequencies = new int[] {10, 4, 3, 1, 2};
		char[] chs = new char[] {'A', 'Z', 'D', 'B', 'C'};

		int[] nFreq = new int[chs.length];
		pi = new ProbItem(frequencies, chs);
		int n = 10_000;
		for(int i = 0; i < n; i++) {
			char ch = pi.getRndItem();
			for (int iCh = 0; iCh < nFreq.length; iCh++) {
				if (chs[iCh] == ch) {nFreq[iCh]++; break;}
			}
		}
		for (int i = 0; i < nFreq.length; i++) {
			System.out.println("Char: " + chs[i] + 
					" Frequency: " + (double) nFreq[i] / n * 100 + '%' + 
					"   Expected frequncy: " + (double) frequencies[i] / pi.sumFre * 100 + '%');
		}
	}

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
		chs[0] = 'A'; fqs[0] = 2;
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

	/** Returns a random item, as if the item was drawn from a sack of items (the drawn item is put into the sack again).
	 * @return a random item
	 */
	public char getRndItem() {
		return chRet[random.nextInt(chRet.length)];
	}

}
