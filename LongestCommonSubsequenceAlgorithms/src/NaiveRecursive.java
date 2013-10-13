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
		return lcs(m, n);
	}

	private String lcs(int i, int j) {
		if (i == 0 || j == 0) {
			return "";
		}

		String lcs;

		String xSub = new String(x, 0, x.length - 1);
		String ySub = new String(y, 0, y.length - 1);

		if (x[i - 1] == y[j - 1]) {
			lcs = new NaiveRecursive(xSub, ySub).lcs() + x[i - 1];

		} else {
			String lcsSub1 = new NaiveRecursive(new String(x), ySub).lcs();
			String lcsSub2 = new NaiveRecursive(xSub, new String(y)).lcs();

			lcs = lcsSub1.length() > lcsSub2.length() ? lcsSub1 : lcsSub2;
		}

		return lcs;
	}

	@Override
	public int lcsLength() {
		return lcs().length();
	}
}
