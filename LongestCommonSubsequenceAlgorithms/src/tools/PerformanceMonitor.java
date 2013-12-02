package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class PerformanceMonitor {

	private long recursiveCallCount = 0;

	private long startTime = 0;
	private long endTime = 0;

	private static final int START_TIME = 0;
	private static final int END_TIME = 1;

	private Map<String, List<Long>> timers = new HashMap<String, List<Long>>();

	public void makeRecursiveCall() {
		recursiveCallCount += 1;
	}

	public long getRecursiveCallCount() {
		return recursiveCallCount;
	}

	public void resetRecursiveCallCount() {
		recursiveCallCount = 0;
	}

	@Deprecated
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}

	@Deprecated
	public void endTimer() {
		endTime = System.currentTimeMillis();
	}

	@Deprecated
	public long getElapsedTimeMillis() {
		return endTime - startTime;
	}

	public void startTimer(String timerId) {
		List<Long> timer;

		if (timers.containsKey(timerId)) {
			timer = timers.get(timerId);
		} else {
			timer = new ArrayList<Long>(2);
			timers.put(timerId, timer);
		}

		timer.add(START_TIME, System.currentTimeMillis());
		timer.add(END_TIME, (long) -1);
	}

	public void endTimer(String timerId) {
		if (!timers.containsKey(timerId)) {
			throw new RuntimeException("Timer \"" + timerId + "\" has not been started.");
		}

		List<Long> timer = timers.get(timerId);
		timer.add(END_TIME, System.currentTimeMillis());
	}

	public long getElapsedTimeMillis(String timerId) {
		if (!timers.containsKey(timerId)) {
			throw new RuntimeException("Timer \"" + timerId + "\" has not been started.");
		}

		List<Long> timer = timers.get(timerId);

		if (timer.get(END_TIME) == -1) {
			throw new RuntimeException("Timer \"" + timerId + "\" has not been stopped.");
		}

		return timer.get(END_TIME) - timer.get(START_TIME);
	}

	public static void main(String... args) throws Exception {
		PerformanceMonitor performanceMonitor = new PerformanceMonitor();

		// TEST 1
		performanceMonitor.startTimer("timer1");
		Thread.sleep(1000);
		performanceMonitor.endTimer("timer1");
		System.out.println(performanceMonitor.getElapsedTimeMillis("timer1"));

		// TEST 2
		performanceMonitor.startTimer("timer2");
		try {
			performanceMonitor.getElapsedTimeMillis("timer2");
			throw new RuntimeException("cannot query a non-stopped timer");
		} catch (RuntimeException e) {
		}

		// TEST 3
		try {
			performanceMonitor.endTimer("timer3");
			throw new RuntimeException("cannot end a non-started timer");
		} catch (RuntimeException e) {
		}
	}
}