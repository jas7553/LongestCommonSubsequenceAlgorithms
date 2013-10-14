package algorithms;

import java.util.Arrays;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class NaiveRecursive extends LcsSolver {

	public NaiveRecursive() {
		super();
	}

	@Override
	protected String lcs() {
		return lcs(x, y);
	}

	private String lcs(char[] x, char[] y) {
		return lcs(x, y, x.length, y.length);
	}

	private String lcs(char[] x, char[] y, int i, int j) {
		performanceMonitor.makeRecursiveCall();

		if (i == 0 || j == 0) {
			return "";
		}

		String lcs;

		char[] xSub = Arrays.copyOf(x, x.length - 1);
		char[] ySub = Arrays.copyOf(y, y.length - 1);

		if (x[i - 1] == y[j - 1]) {
			lcs = lcs(xSub, ySub) + x[i - 1];

		} else {
			String lcsSub1 = lcs(x, ySub);
			String lcsSub2 = lcs(xSub, y);

			lcs = lcsSub1.length() > lcsSub2.length() ? lcsSub1 : lcsSub2;
		}

		return lcs;
	}

	@Override
	protected int lcsLength() {
		return lcs().length();
	}

	public static void main(String... args) {
		LcsSolver solver = new NaiveRecursive();

		String x = "AGGTAB";
		String y = "GXTXAYB";

		String lcs = solver.lcs(x, y);
		int lcsLength = solver.lcsLength(x, y);

		System.out.println("LCS: " + lcs);
		System.out.println("LCS length: " + lcsLength);
	}
}
