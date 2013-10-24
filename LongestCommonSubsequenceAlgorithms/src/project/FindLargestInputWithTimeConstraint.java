package project;

import java.io.IOException;

import algorithms.Hirschberg;
import algorithms.LcsSolver;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class FindLargestInputWithTimeConstraint {

	private static final long TIME_LIMIT_MS = 10 * 1000;

	private RandomStringGenerator generator = new RandomStringGenerator(new char[] { 'A', 'C', 'G', 'T' });

	public int test(LcsSolver solver, int initialInputSize, int delta) throws IOException {
		long time = 0;
		int inputSize = initialInputSize;

		System.out.println(solver.getClass().getSimpleName());
		System.out.println("Input size\tTime (ms)");
		while (time < TIME_LIMIT_MS) {
			inputSize += delta;
			generator.setStringSize(inputSize);

			solver.lcs(generator.next(), generator.next());
			time = solver.getPerformanceMonitor().getElapsedTimeMillis();

			System.out.println(inputSize + "\t" + time);
		}

		return inputSize;
	}

	public static void main(String[] args) throws IOException {
		FindLargestInputWithTimeConstraint tester = new FindLargestInputWithTimeConstraint();

//		tester.test(new NaiveRecursive(), 10, 1);
//		System.out.println();

//		tester.test(new RecursiveMemoization(), 400, 10);
//		System.out.println();

//		tester.test(new DynamicProgramming(), 3800);
//		System.out.println();

		tester.test(new Hirschberg(), 26000, 100);
//		System.out.println();
	}
}
