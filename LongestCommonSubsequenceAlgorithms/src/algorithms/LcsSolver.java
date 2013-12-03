package algorithms;

import tools.PerformanceMonitor;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public abstract class LcsSolver {

	protected char[] x;
	protected char[] y;

	protected int m;
	protected int n;

	protected PerformanceMonitor performanceMonitor;

	private String timerName;

	public LcsSolver() {
		performanceMonitor = new PerformanceMonitor();
	}

	public long getElapsedTimeMillis() {
		return performanceMonitor.getElapsedTimeMillis(timerName);
	}

	public long getRecursiveCallCount() {
		return performanceMonitor.getRecursiveCallCount();
	}

	public final String lcs(String x, String y) {
		setXY(x, y);

		performanceMonitor.resetRecursiveCallCount();
		performanceMonitor.startTimer("lcs");

		String lcs = lcs();

		performanceMonitor.endTimer("lcs");
		timerName = "lcs";

		return lcs;
	}

	public final int lcsLength(String x, String y) {
		setXY(x, y);

		performanceMonitor.resetRecursiveCallCount();
		performanceMonitor.startTimer("lcsLength");

		int lcsLength = lcsLength();

		performanceMonitor.endTimer("lcsLength");
		timerName = "lcsLength";

		return lcsLength;
	}

	public final void setXY(String x, String y) {
		this.x = x.toCharArray();
		this.y = y.toCharArray();

		m = this.x.length;
		n = this.y.length;
	}

	protected abstract String lcs();

	protected abstract int lcsLength();
}