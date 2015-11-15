import java.util.Random;


public class ProbItem {

	public static void main(String[] args) { //Unit Testing

		ProbItem pi = new ProbItem();

		for(int i=0; i < 20; i++)
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



	int n;
	int[] fqs;
	char[] chs;
	char[] chRet;
	int sumFre = 0;

	Random random = new Random();

	ProbItem() {
		n = 3;
		fqs = new int[n];
		chs = new char[n];
		chs[0] = 'A'; fqs[0] = 1;
		chs[1] = 'B'; fqs[1] = 1;
		chs[2] = 'C'; fqs[2] = 1;

		createChRet();
	}

	/**
	 * @param frequencies
	 * @param chs
	 */
	ProbItem(int[] frequencies, char[] chs) {
		this.fqs = frequencies; this.chs = chs;
		createChRet();
	}

	private void createChRet() {
		for (int f : fqs) sumFre += f;

		chRet = new char[sumFre];
		int iCh = 0;
		for (int i=0; i < fqs.length; i++)
			for (int j=0; j < fqs[i]; j++)
				chRet[iCh++] = chs[i];
	}

	/** Returns a random item as if the item was draw from a sac of items (the drawn item is put into the sac again). 
	 * @return an random item
	 */
	public char getRndItem() {
		return chRet[random.nextInt(chRet.length)];
	}

}