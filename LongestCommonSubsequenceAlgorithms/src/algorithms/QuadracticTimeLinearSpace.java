package algorithms;

import java.util.Arrays;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class QuadracticTimeLinearSpace extends LcsSolver {

	private int[] c1;
	private int[] c2;

	public QuadracticTimeLinearSpace() {
		super();
	}

	@Override
	protected String lcs() {
		// TODO Can the quadratic time linear space algorithm even compute the LCS??
		return null;
	}

	@Override
	protected int lcsLength() {
		buildTable();

		return c2[n];
	}

	private void clearTable() {
		c1 = new int[n + 1];
		c2 = new int[n + 1];
	}

	private void buildTable() {
		clearTable();

		for (int i = 0; i < n + 1; i++) {
			c1[i] = 0;
			c2[i] = 0;
		}

		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (x[i - 1] == y[j - 1]) {
					c2[j] = c1[j - 1] + 1;
				} else if (c2[j - 1] >= c1[j]) {
					c2[j] = c2[j - 1];
				} else {
					c2[j] = c1[j];
				}
			}
			c1 = Arrays.copyOf(c2, c2.length);
		}
	}

	public void printC() {
		System.out.print("[");
		for (int i = 0; i < c2.length - 1; i++) {
			System.out.print(c2[i] + ", ");
		}
		System.out.println(c2[c2.length - 1] + "]");
	}

	public static void main(String... args) {
		LcsSolver solver = new QuadracticTimeLinearSpace();

		String x = "AGGTAB";
		String y = "GXTXAYB";

		String lcs = solver.lcs(x, y);
		int lcsLength = solver.lcsLength(x, y);

		System.out.println("LCS: " + lcs);
		System.out.println("LCS length: " + lcsLength);
	}
}
