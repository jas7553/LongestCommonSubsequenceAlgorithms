package algorithms;

import project.PerformanceMonitor;

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

	public LcsSolver() {
		this.performanceMonitor = new PerformanceMonitor();
	}

	public final PerformanceMonitor getPerformanceMonitor() {
		return performanceMonitor;
	}

	public final String lcs(String x, String y) {
		setXY(x, y);

		performanceMonitor.resetRecursiveCallCount();
		performanceMonitor.startTimer();

		String lcs = lcs();

		performanceMonitor.endTimer();

		return lcs;
	}

	public final int lcsLength(String x, String y) {
		setXY(x, y);

		performanceMonitor.resetRecursiveCallCount();
		performanceMonitor.startTimer();

		int lcsLength = lcsLength();

		performanceMonitor.endTimer();

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