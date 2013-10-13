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

		String xSub = new String(x, 0, x.length - 1);
		String ySub = new String(y, 0, y.length - 1);

		if (x[i - 1] == y[j - 1]) {
			lcs = lcs(xSub.toCharArray(), ySub.toCharArray()) + x[i - 1];

		} else {
			String lcsSub1 = lcs(new String(x).toCharArray(), ySub.toCharArray());
			String lcsSub2 = lcs(xSub.toCharArray(), new String(y).toCharArray());

			lcs = lcsSub1.length() > lcsSub2.length() ? lcsSub1 : lcsSub2;
		}

		return lcs;
	}

	@Override
	public int lcsLength() {
		return lcs().length();
	}

	public static void main(String... args) {
		LcsSolver solver = new NaiveRecursive("A", "A");

		String lcs = solver.lcs();
		int lcsLength = solver.lcsLength();

		System.out.println(lcs);
		System.out.println(lcsLength);

		System.out.println(solver.getPerformanceMonitor().getRecursiveCallCount());
	}
}
