package project;

import java.util.HashSet;
import java.util.Set;

import algorithms.DynamicProgramming;
import algorithms.LcsSolver;
import algorithms.NaiveRecursive;
import algorithms.QuadracticTimeLinearSpace;
import algorithms.RecursiveMemoization;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class SanityTest {

	private LcsSolver[] lcsSolvers;
	private LcsSolver[] lcsLengthsolvers;
	private RandomStringGenerator generator;

	public SanityTest() {
		lcsSolvers = new LcsSolver[] { new NaiveRecursive(), new RecursiveMemoization(), new DynamicProgramming() };
		lcsLengthsolvers = new LcsSolver[] { new NaiveRecursive(), new RecursiveMemoization(), new DynamicProgramming(), new QuadracticTimeLinearSpace() };
		generator = new RandomStringGenerator(new char[] { 'A', 'C', 'G', 'T' }, 12);
	}

	public void testLcs() {
		String x = generator.next();
		String y = generator.next();

		Set<String> lcsAnswers = new HashSet<String>();

		for (LcsSolver solver : lcsSolvers) {
//			solver.setXY(x, y);
			String lcs = solver.lcs(x, y);
			lcsAnswers.add(lcs);
		}

		if (lcsAnswers.size() != 1) {
			System.err.println(lcsAnswers.toString());
			throw new RuntimeException("LCS answers didn't match!");
		}
	}

	public void testLcsLength() {
		String x = generator.next();
		String y = generator.next();

		Set<Integer> lcsLengthAnswers = new HashSet<Integer>();

		for (LcsSolver solver : lcsLengthsolvers) {
			int lcsLength = solver.lcsLength(x, y);
			lcsLengthAnswers.add(lcsLength);
		}

		if (lcsLengthAnswers.size() != 1) {
			System.err.println(x);
			System.err.println(y);
			System.err.println(lcsLengthAnswers.toString());
			throw new RuntimeException("LCS legnth answers didn't match!");
		}
	}

	public static void main(String... args) {
		SanityTest tester = new SanityTest();

		for (int i = 0; i < 50; i++) {
			tester.testLcs();
			tester.testLcsLength();
		}

		System.out.println("PASS");
	}
}
