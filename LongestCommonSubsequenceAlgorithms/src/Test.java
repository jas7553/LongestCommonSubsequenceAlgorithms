import java.util.HashSet;
import java.util.Set;

/**
 * @author Jason A Smith <jas7553>
 * 
 */
public class Test {

	private LcsSolver[] solvers;
	private RandomStringGenerator generator;

	public Test() {
		solvers = new LcsSolver[] { new NaiveRecursive(), new RecursiveMemoization(), new DynamicProgramming() };
		generator = new RandomStringGenerator(new char[] { 'A', 'C', 'G', 'T' }, 10);
	}

	public void test() {
		String x = generator.next();
		String y = generator.next();

		System.out.println("Testing with \"" + x + "\", \"" + y + "\"");

		Set<String> answers = new HashSet<String>();

		for (LcsSolver solver : solvers) {
			solver.setXY(x, y);

			String lcs = solver.lcs();
			int lcsLength = solver.lcsLength();

			System.out.println(lcs + " " + lcsLength);
			answers.add(lcs);
		}

		if (answers.size() != 1) {
			throw new RuntimeException("An answer didn't match!");
		}
	}

	public static void main(String... args) {
		Test tester = new Test();
		for (int i = 0; i < 50; i++) {
			tester.test();
			System.out.println();
		}
	}
}
