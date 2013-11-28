package tools;

import java.util.HashSet;
import java.util.Set;

import algorithms.DynamicProgramming;
import algorithms.Hirschberg;
import algorithms.LcsSolver;
import algorithms.NaiveRecursive;
import algorithms.RecursiveMemoization;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class SanityTest {

	private LcsSolver[] solvers;
	private RandomStringGenerator stringGenerator;

	public SanityTest() {
		solvers = new LcsSolver[] { new NaiveRecursive(), 
									new RecursiveMemoization(),
									new DynamicProgramming(),
									new Hirschberg() };
		stringGenerator = new RandomStringGenerator(new char[] { 'A', 'C', 'G', 'T' }, 12);
	}

	public void testLcs() {
		String x = stringGenerator.next();
		String y = stringGenerator.next();

		Set<Integer> lcsAnswers = new HashSet<Integer>();

		for (LcsSolver solver : solvers) {
			String lcs = solver.lcs(x, y);
			if (!verifyCommonSubsequence(x, y, lcs)) {
				System.err.println(x);
				System.err.println(y);
				throw new RuntimeException("Solver did not generate a valid common subsequence!");
			}
			lcsAnswers.add(lcs.length());
		}

		if (lcsAnswers.size() != 1) {
			System.err.println(x);
			System.err.println(y);
			System.err.println(lcsAnswers.toString());
			throw new RuntimeException("Lengths of LCS answers don't all match!");
		}
	}

	/**
	 * Subsequence is a common subsequence of x and y if subsequence is a subset
	 * of both x and y.
	 */
	private static boolean verifyCommonSubsequence(String x, String y, String subsequence) {
		return existsIn(subsequence, x) && existsIn(subsequence, y);
	}

	/**
	 * @return True iff lcs is a subset of s.
	 */
	private static boolean existsIn(String lcs, String s) {
		int i = 0;
		for (int j = 0; j < s.length(); j++) {
			if (s.charAt(j) == lcs.charAt(i)) {
				i++;
				if (i == lcs.length()) {
					return true;
				}
			}
		}
		return false;
	}

	public void testLcsLength() {
		String x = stringGenerator.next();
		String y = stringGenerator.next();

		Set<Integer> lcsLengthAnswers = new HashSet<Integer>();

		for (LcsSolver solver : solvers) {
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

		// test the lcs() function repeatedly
		for (int i = 0; i < 100; i++) {
			tester.testLcs();
		}

		// test the lcsLength() function repeatedly
		for (int i = 0; i < 100; i++) {
			tester.testLcsLength();
		}

		System.out.println("PASS");
	}
}
