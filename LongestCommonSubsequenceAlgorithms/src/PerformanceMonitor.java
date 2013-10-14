/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class PerformanceMonitor {

	private int recursiveCallCount = 0;

	private long startTime = 0;
	private long endTime = 0;

	public void makeRecursiveCall() {
		recursiveCallCount += 1;
	}

	public int getRecursiveCallCount() {
		return recursiveCallCount;
	}

	public void startTimer() {
		startTime = System.currentTimeMillis();
	}

	public void endTimer() {
		endTime = System.currentTimeMillis();
	}

	public long getElapsedTimeMillis() {
		return endTime - startTime;
	}

	public void resetRecursiveCallCount() {
		recursiveCallCount = 0;
	}
}