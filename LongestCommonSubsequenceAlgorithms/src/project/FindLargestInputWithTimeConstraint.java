package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import tools.RandomStringGenerator;
import algorithms.LcsSolver;
import algorithms.NaiveRecursive;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class FindLargestInputWithTimeConstraint {

	private static final long TIME_LIMIT_MS = 10 * 1000;

	private char[] alphabet = new char[] { 'A', 'C', 'G', 'T' };
	private RandomStringGenerator generator = new RandomStringGenerator(alphabet);

	public int test(LcsSolver solver, int initialInputSize, int delta) throws IOException {
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
			inputSize += delta;
			generator.setStringSize(inputSize);

			long avgTime = 0;
			int repititions = 10;
			for (int i = 0; i < repititions; i++) {
				solver.lcs(generator.next(), generator.next());
				avgTime += solver.getPerformanceMonitor().getElapsedTimeMillis();
			}
			time = (avgTime / repititions);

			System.out.println(inputSize + "\t" + time);
			writer.write(inputSize + "," + time + "\n");
		}

		writer.close();

		return inputSize;
	}

	public static void main(String[] args) throws IOException {
		FindLargestInputWithTimeConstraint tester = new FindLargestInputWithTimeConstraint();

		 tester.test(new NaiveRecursive(), 10, 1);
		 System.out.println();

//		 tester.test(new RecursiveMemoization(), 400, 10);
//		 System.out.println();

//		 tester.test(new DynamicProgramming(), 3800, 100);
//		 System.out.println();

//		tester.test(new Hirschberg(), 25400, 20);
//		System.out.println();
	}
}
