import java.util.HashSet;
import java.util.Set;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class SanityTest {

	private LcsSolver[] solvers;
	private RandomStringGenerator generator;

	public SanityTest() {
		solvers = new LcsSolver[] { new NaiveRecursive(), new RecursiveMemoization(), new DynamicProgramming() };
		generator = new RandomStringGenerator(new char[] { 'A', 'C', 'G', 'T' }, 10);
	}

	public void test() {
		String x = generator.next();
		String y = generator.next();

		Set<String> lcsAnswers = new HashSet<String>();
		Set<Integer> lcsLengthAnswers = new HashSet<Integer>();

		for (LcsSolver solver : solvers) {
			solver.setXY(x, y);

			String lcs = solver.lcs();
			int lcsLength = solver.lcsLength();

			lcsAnswers.add(lcs);
			lcsLengthAnswers.add(lcsLength);
		}

		if (lcsAnswers.size() != 1) {
			throw new RuntimeException("LCS answers didn't match!");
		}

		if (lcsLengthAnswers.size() != 1) {
			throw new RuntimeException("LCS legnth answers didn't match!");
		}
	}

	public static void main(String... args) {
		SanityTest tester = new SanityTest();

		for (int i = 0; i < 50; i++) {
			tester.test();
		}

		System.out.println("PASS");
	}
}
