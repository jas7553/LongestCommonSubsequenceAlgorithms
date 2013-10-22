package algorithms;

import java.util.Arrays;

public class Hirschberg extends LcsSolver {

	@Override
	protected String lcs() {
		return lcs(x, y);
	}

	private String lcs(char[] x, char[] y) {
		return lcs(x.length, y.length, x, y);
	}

	private String lcs(int m, int n, char[] x, char[] y) {
		// 1. If problem is trivial, solve it
		if (n == 0) {
			return "";
		}

		if (m == 1) {
			for (int j = 0; j < n; j++) {
				if (x[0] == y[j]) {
					return String.valueOf(x[0]);
				}
			}
			return "";
		}

		// 2. Otherwise, split the problem:
		int i = m / 2;

		int[] algB1 = algB(Arrays.copyOf(x, i), y);
		int[] algB2 = algB(reverse(Arrays.copyOfRange(x, i, x.length)), reverse(y));

		int M = 0;
		int k = 0;

		for (int j = 0; j < algB1.length; j++) {
			if (algB1[j] + algB2[algB1.length - 1 - j] > M) {
				M = algB1[j] + algB2[algB1.length - 1 - j];
				k = j;
			}
		}

		String C1 = lcs(Arrays.copyOf(x, i), Arrays.copyOf(y, k));
		String C2 = lcs(Arrays.copyOfRange(x, i, x.length), Arrays.copyOfRange(y, k, y.length));

		return C1 + C2;
	}

	/**
	 * Why is this not built into Java?
	 */
	private static char[] reverse(char[] array) {
		char[] reversed = new char[array.length];

		for (int i = 0; i < array.length; i++) {
			reversed[array.length - 1 - i] = array[i];
		}

		return reversed;
	}

	private int[] algB(char[] x, char[] y) {
		return algB(x.length, y.length, x, y);
	}

	private int[] algB(int m, int n, char[] x, char[] y) {
		int[][] K = new int[2][n + 1];

		for (int j = 0; j < n + 1; j++) {
			K[1][j] = 0;
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n + 1; j++) {
				K[0][j] = K[1][j];
			}
			for (int j = 1; j < n + 1; j++) {
				if (x[i] == y[j - 1]) {
					K[1][j] = K[0][j - 1] + 1;
				} else {
					K[1][j] = Math.max(K[1][j - 1], K[0][j]);
				}
			}
		}

		return K[1];
	}

	@Override
	protected int lcsLength() {
		return algB(x, y)[n];
	}

	public static void main(String[] args) {
		LcsSolver solver = new Hirschberg();

		String x = "AGGTAB";
		String y = "GXTXAYB";

		String lcs = solver.lcs(x, y);
		int lcsLength = solver.lcsLength(x, y);

		System.out.println("LCS: " + lcs);
		System.out.println("LCS length: " + lcsLength);
	}
}
