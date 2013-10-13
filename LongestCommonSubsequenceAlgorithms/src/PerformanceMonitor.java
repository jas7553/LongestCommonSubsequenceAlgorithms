/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class PerformanceMonitor {

	private int recursiveCallCount = 0;

	public void makeRecursiveCall() {
		recursiveCallCount += 1;
	}

	public int getRecursiveCallCount() {
		return recursiveCallCount;
	}

	public void reset() {
		recursiveCallCount = 0;
	}
}