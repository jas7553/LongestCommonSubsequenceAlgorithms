package project;

import algorithms.LcsSolver;
import algorithms.NaiveRecursive;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class LCS {

	public static void main(String[] args) {
		LcsSolver solver = new NaiveRecursive();
		PerformanceMonitor performanceMonitor = solver.getPerformanceMonitor();

		char[] alphabet = new char[] { 'A', 'C', 'G', 'T' };
		RandomStringGenerator generator = new RandomStringGenerator(alphabet, (long) 123);

		int iterations = 100;
		int minLength = 1;
		int maxLength = 15;

		System.out.println("String size\tAvg recursive calls\tAvg elapsed time");

		for (int i = minLength; i <= maxLength; i++) {
			generator.setStringSize(i);

			int totalRecursiveCalls = 0;
			long totalTimeElapsed = 0;

			for (int j = 0; j < iterations; j++) {
				solver.lcs(generator.next(), generator.next());

				totalRecursiveCalls += performanceMonitor.getRecursiveCallCount();
				totalTimeElapsed += performanceMonitor.getElapsedTimeMillis();
			}

			int averageRecursiveCalls = totalRecursiveCalls / iterations;
			long averageElapsedTime = totalTimeElapsed / iterations;

			System.out.println(i + "\t" + averageRecursiveCalls + "\t" + averageElapsedTime);
		}
	}
}
