import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class RecursiveMemoization extends LcsSolver {

	private static final Map<String, String> cache = new HashMap<String, String>();

	public RecursiveMemoization() {
		super();
	}

	public RecursiveMemoization(String x, String y) {
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

		String key = new StringBuilder().append(x).append("_").append(y).toString();
		if (cache.containsKey(key)) {
			return cache.get(key);
		}

		String xSub = new String(x, 0, x.length - 1);
		String ySub = new String(y, 0, y.length - 1);

		if (x[i - 1] == y[j - 1]) {
			lcs = new RecursiveMemoization(xSub, ySub).lcs() + x[i - 1];

		} else {
			String lcsSub1 = new RecursiveMemoization(new String(x), ySub).lcs();
			String lcsSub2 = new RecursiveMemoization(xSub, new String(y)).lcs();

			lcs = lcsSub1.length() > lcsSub2.length() ? lcsSub1 : lcsSub2;
		}

		cache.put(key, lcs);

		return lcs;
	}

	@Override
	public int lcsLength() {
		return lcs().length();
	}

	@Override
	public void reset() {
		super.reset();
		cache.clear();
	}
}
