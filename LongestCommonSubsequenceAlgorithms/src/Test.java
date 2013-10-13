public class Test {

	private LcsSolver[] solvers;

	public Test() {
		solvers = new LcsSolver[] { new NaiveRecursive(), new RecursiveMemoization(), new DynamicProgramming() };
	}

	public void test(String x, String y) {
		System.out.println("Testing with \"" + x + "\", \"" + y + "\"");

		for (LcsSolver solver : solvers) {
			solver.setXY(x, y);

			String lcs = solver.lcs();
			int lcsLength = solver.lcsLength();

			System.out.println(lcs + " " + lcsLength);

			solver.reset();
		}
	}

	public static void main(String... args) {
		Test tester = new Test();
		tester.test("AGGTAB", "GXTXAYB");
		tester.test("ABCBDAB", "BDCABA");
		tester.test("AAABBB", "CCAAACCBBBCC");
	}
}
