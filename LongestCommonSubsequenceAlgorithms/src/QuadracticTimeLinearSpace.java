/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class QuadracticTimeLinearSpace extends LcsSolver {

	public QuadracticTimeLinearSpace() {
		super();
	}

	@Override
	protected String lcs() {
		return null;
	}

	@Override
	protected int lcsLength() {
		return 0;
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
