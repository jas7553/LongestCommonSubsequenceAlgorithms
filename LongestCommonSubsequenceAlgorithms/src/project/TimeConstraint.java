package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import tools.RandomStringGenerator;
import algorithms.Hirschberg;
import algorithms.LcsSolver;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class TimeConstraint {

	private static final String USAGE = "Usage: TimeConstraint [NaiveRecursive|RecursiveMemoization|DynamicProgramming|Hirschberg] iterations initialInputSize [delta]";

	private static final long TIME_LIMIT_MS = 10 * 1000;

	private char[] alphabet = new char[] { 'A', 'C', 'G', 'T' };
	private RandomStringGenerator generator = new RandomStringGenerator(alphabet);

	public int test(LcsSolver solver, int iterations, int initialInputSize, int delta) throws IOException {
		long time = 0;
		int inputSize = initialInputSize;

		String solverName = solver.getClass().getSimpleName();

		PrintWriter writer;

		try {
			String filename = "results" + File.separator + "time" + File.separator + solverName + "_" + initialInputSize + "_" + delta + ".csv";
			writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		System.out.println(solver.getClass().getSimpleName());
		System.out.println("Input size\tTime (ms)");
		while (time < TIME_LIMIT_MS) {
			generator.setStringSize(inputSize);

			long avgTime = 0;
			for (int i = 0; i < iterations; i++) {
				solver.lcsLength(generator.next(), generator.next());
				avgTime += solver.getElapsedTimeMillis();
				System.gc();
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
				}
			}
			time = (avgTime / iterations);

			System.out.println(inputSize + "\t" + time);
			writer.write(inputSize + "," + time + "\n");

			inputSize += delta;
		}

		writer.close();

		return inputSize;
	}

	public static void main(String[] args) throws IOException {
		TimeConstraint tester = new TimeConstraint();

//		 tester.test(new NaiveRecursive(), 5, 10, 1);
//		 System.out.println();

//		 tester.test(new RecursiveMemoization(), 5, 400, 10);
//		 System.out.println();

//		 tester.test(new DynamicProgramming(), 5, 10, 10);
//		 System.out.println();

		tester.test(new Hirschberg(), 5, 20000, 500);
		System.out.println();
	}
}
