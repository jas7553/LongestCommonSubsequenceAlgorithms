package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import tools.RandomStringGenerator;
import algorithms.DynamicProgramming;
import algorithms.Hirschberg;
import algorithms.LcsSolver;
import algorithms.NaiveRecursive;
import algorithms.RecursiveMemoization;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class LCS {

	private static final String USAGE = "Usage: LCS [NaiveRecursive|RecursiveMemoization|DynamicProgramming|Hirschberg] iterations minLength maxLength [delta]";

	private char[] alphabet = new char[] { 'A', 'C', 'G', 'T' };
	private RandomStringGenerator generator = new RandomStringGenerator(alphabet);

	public void run(LcsSolver solver, int iterations, int minLength, int maxLength, int delta) {
		String solverName = solver.getClass().getSimpleName();

		PrintWriter writer;

		try {
			String filename = "results" + File.separator + solverName + "_" + iterations + "_" + minLength + "_" + maxLength + "_" + delta + ".csv";
			writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(solverName);
		System.out.println("String size | Avg recursive calls | Avg elapsed time (ms)");
		System.out.println("------------+---------------------+----------------------");

		for (int i = minLength; i <= maxLength; i += delta) {
			generator.setStringSize(i);

			long totalRecursiveCalls = 0;
			long totalTimeElapsed = 0;

			for (int j = 0; j < iterations; j++) {
				solver.lcs(generator.next(), generator.next());

				totalRecursiveCalls += solver.getRecursiveCallCount();
				totalTimeElapsed += solver.getElapsedTimeMillis();
			}

			long averageRecursiveCalls = totalRecursiveCalls / iterations;
			long averageElapsedTime = totalTimeElapsed / iterations;

			System.out.printf("%11d |%20d |%22d\n", i, averageRecursiveCalls, averageElapsedTime);
			writer.write(i + "," + averageRecursiveCalls + "," + averageElapsedTime + "\n");
		}

		writer.close();
	}

	public static void usage() {
		System.out.println(USAGE);
		System.exit(0);
	}

	public static void main(String[] args) {
		if (args.length < 4 || args.length > 5) {
			usage();
		}

		LcsSolver solver = null;
		if (args[0].equals("NaiveRecursive")) {
			solver = new NaiveRecursive();
		} else if (args[0].equals("RecursiveMemoization")) {
			solver = new RecursiveMemoization();
		} else if (args[0].equals("DynamicProgramming")) {
			solver = new DynamicProgramming();
		} else if (args[0].equals("Hirschberg")) {
			solver = new Hirschberg();
		} else {
			usage();
		}

		int iterations = Integer.parseInt(args[1]);
		int minLength = Integer.parseInt(args[2]);
		int maxLength = Integer.parseInt(args[3]);

		int delta = 1;
		if (args.length == 5) {
			delta = Integer.parseInt(args[4]);
		}

		LCS tester = new LCS();
		tester.run(solver, iterations, minLength, maxLength, delta);
	}
}
