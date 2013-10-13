/**
 * @author Jason A Smith <jas7553>
 * 
 */
public abstract class LcsSolver {

	protected char[] x;
	protected char[] y;

	protected int m;
	protected int n;
	
	public LcsSolver() {
		this("", "");
	}

	public LcsSolver(String x, String y) {
		setXY(x, y);
	}

	public void setXY(String x, String y) {
		this.x = x.toCharArray();
		this.y = y.toCharArray();

		m = this.x.length;
		n = this.y.length;
	}

	public abstract String lcs();

	public abstract int lcsLength();
	
	public void reset() {
	}
}