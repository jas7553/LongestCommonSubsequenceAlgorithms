package algorithms;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class DynamicProgramming extends LcsSolver {

//	private static enum DIRECTION {
//		LEFT, UP, UP_LEFT;
//	}

	private static final int LEFT = 0;
	private static final int UP = 1;
	private static final int UP_LEFT = 2;

//	private DIRECTION[][] b;
	private int[][] b;
	private int[][] c;

	@Override
	protected String lcs() {
		buildTables();
		return lcs(x.length, y.length);
	}

	private String lcs(int i, int j) {
		StringBuilder lcs = new StringBuilder();

		while (i != 0 && j != 0) {
			switch (b[i][j]) {
			case UP_LEFT:
				lcs.insert(0, x[i - 1]);
				i = i - 1;
				j = j - 1;
				break;
			case UP:
				i = i - 1;
				break;
			case LEFT:
				j = j - 1;
				break;
			default:
				throw new RuntimeException("Should not happen");
			}
		}

		return lcs.toString();
	}

	@Override
	protected int lcsLength() {
		buildTables();
		return c[m][n];
	}

	private void clearTables() {
//		b = new DIRECTION[m + 1][n + 1];
		b = new int[m + 1][n + 1];
		c = new int[m + 1][n + 1];
	}

	private void buildTables() {
		clearTables();

		for (int i = 0; i < m + 1; i++) {
			c[i][0] = 0;
		}

		for (int j = 0; j < n + 1; j++) {
			c[0][j] = 0;
		}

		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				performanceMonitor.makeRecursiveCall();
				if (x[i - 1] == y[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1;
//					b[i][j] = DIRECTION.UP_LEFT;
					b[i][j] = UP_LEFT;
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
//					b[i][j] = DIRECTION.UP;
					b[i][j] = UP;
				} else {
					c[i][j] = c[i][j - 1];
//					b[i][j] = DIRECTION.LEFT;
					b[i][j] = LEFT;
				}
			}
		}
	}

	public void displayC() {
		System.out.print("  j ");
		for (int i = 0; i < c[0].length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		System.out.print("i     ");
		for (int i = 0; i < y.length; i++) {
			System.out.print(y[i] + " ");
		}
		System.out.println();

		for (int i = 0; i < c.length; i++) {
			System.out.print(i + " ");
			if (i == 0) {
				System.out.print(" ");
			} else {
				System.out.print(x[i - 1]);
			}
			System.out.print(" ");
			for (int j = 0; j < c[0].length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String... args) {
		LcsSolver solver = new DynamicProgramming();

		String x = "AGGTAB";
		String y = "GXTXAYB";

		String lcs = solver.lcs(x, y);
		int lcsLength = solver.lcsLength(x, y);

		System.out.println("LCS: " + lcs);
		System.out.println("LCS length: " + lcsLength);
	}
}
