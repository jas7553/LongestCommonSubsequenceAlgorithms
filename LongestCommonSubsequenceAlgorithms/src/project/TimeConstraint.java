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
public class TimeConstraint {

	private static final String USAGE = "Usage: TimeConstraint [NaiveRecursive|RecursiveMemoization|DynamicProgramming|Hirschberg] iterations initialInputSize [delta]";

	private static final long TIME_LIMIT_MS = 10 * 1000;

	private char[] alphabet = new char[] { 'A', 'C', 'G', 'T' };
	private RandomStringGenerator generator = new RandomStringGenerator(alphabet);

	public int run(LcsSolver solver, int iterations, int initialInputSize, int delta) {
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

		System.out.println(solverName);
		System.out.println("Input size\tTime (ms)");
		while (time < TIME_LIMIT_MS) {
			generator.setStringSize(inputSize);

			long avgTime = 0;
			for (int i = 0; i < iterations; i++) {
				solver.lcsLength(generator.next(), generator.next());
				avgTime += solver.getElapsedTimeMillis();
				System.gc();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}
			time = (avgTime / iterations);

			System.out.println(inputSize + "\t" + time);
			writer.println(inputSize + "," + time);
			writer.flush();

			inputSize += delta;
		}

		writer.close();

		return inputSize;
	}

	public static void usage() {
		System.out.println(USAGE);
		System.exit(0);
	}

	/**
	 * NaiveRecursive 5 10 1
	 * RecursiveMemoization 5 400 10
	 * DynamicProgramming 5 10 10
	 * Hirschberg 5 20000 500
	 */
	public static void main(String[] args) {
		if (args.length < 3 || args.length > 4) {
			usage();
		}

		LcsSolver solver = null;
		switch (args[0]) {
		case "NaiveRecursive":
			solver = new NaiveRecursive(); break;
		case "RecursiveMemoization":
			solver = new RecursiveMemoization(); break;
		case "DynamicProgramming":
			solver = new DynamicProgramming(); break;
		case "Hirschberg":
			solver = new Hirschberg(); break;
		default:
			usage();
		}

		int iterations = Integer.parseInt(args[1]);
		int initialInputSize = Integer.parseInt(args[2]);

		int delta = 1;
		if (args.length == 4) {
			delta = Integer.parseInt(args[3]);
		}

		TimeConstraint tester = new TimeConstraint();
		tester.run(solver, iterations, initialInputSize, delta);
	}
}
