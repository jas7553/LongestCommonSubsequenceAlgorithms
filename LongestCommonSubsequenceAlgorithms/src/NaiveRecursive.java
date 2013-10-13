import java.util.Arrays;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class NaiveRecursive extends LcsSolver {

	public NaiveRecursive() {
		super();
	}

	public NaiveRecursive(String x, String y) {
		super(x, y);
	}

	@Override
	public String lcs() {
		performanceMonitor.reset();

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
	public int lcsLength() {
		return lcs().length();
	}

	public static void main(String... args) {
		LcsSolver solver = new NaiveRecursive("AGGTAB", "GXTXAYB");

		String lcs = solver.lcs();
		System.out.println("LCS: " + lcs);

		System.out.println("Recusive call count: " + solver.getPerformanceMonitor().getRecursiveCallCount());
		solver.getPerformanceMonitor().reset();

		int lcsLength = solver.lcsLength();
		System.out.println("LCS length: " + lcsLength);

		System.out.println("Recusive call count: " + solver.getPerformanceMonitor().getRecursiveCallCount());
		solver.getPerformanceMonitor().reset();
	}
}
