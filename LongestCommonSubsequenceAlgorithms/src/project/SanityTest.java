package project;

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

	private LcsSolver[] lcsSolvers;
	private LcsSolver[] lcsLengthsolvers;
	private RandomStringGenerator generator;

	public SanityTest() {
		lcsSolvers = new LcsSolver[] { new NaiveRecursive(), new RecursiveMemoization(), new DynamicProgramming(), new Hirschberg() };
		lcsLengthsolvers = new LcsSolver[] { new NaiveRecursive(), new RecursiveMemoization(), new DynamicProgramming(), new Hirschberg() };
		generator = new RandomStringGenerator(new char[] { 'A', 'C', 'G', 'T' }, 12);
	}

	public void testLcs() {
		String x = generator.next();
		String y = generator.next();

		Set<Integer> lcsAnswers = new HashSet<Integer>();

		for (LcsSolver solver : lcsSolvers) {
			String lcs = solver.lcs(x, y);
			if (!verifyLcs(x, y, lcs)) {
				throw new RuntimeException("Solver did not generate valid LCS!");
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

	private static boolean verifyLcs(String x, String y, String lcs) {
		return existsIn(lcs, x) && existsIn(lcs, y);
	}

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
