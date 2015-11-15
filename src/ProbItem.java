
public class ProbItem {

	public static void main(String[] args) {

	}



	int n;
	int[] fqs;
	char[] chs;
	char[] chRet;
	int sumFre = 0;

	ProbItem() {
		n = 3;
		fqs = new int[n];
		chs = new char[n];
		chs[0] = 'A'; fqs[0] = 1;
		chs[1] = 'B'; fqs[1] = 1;
		chs[2] = 'C'; fqs[2] = 1;
		
		for (int f : fqs) sumFre += f;
		chRet = new char[sumFre];
		
		
	}


	ProbItem(int[] frequency, char[] chs) {
		this.fqs = frequency; this.chs = chs;
		for (int f : fqs) sumFre += f;
	}


	public char getRndItem() {


		return ch;
	}

}
