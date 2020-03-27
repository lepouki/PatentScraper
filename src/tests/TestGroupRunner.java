package tests;

public class TestGroupRunner {

	public static void runTestGroup(TestGroup testGroup) {
		Test[] tests = testGroup.getTests();

		for (Test test : tests) {
			testGroup.initializeNextTest();
			runTest(test);
		}
	}

	private static void runTest(Test test) {
		try {
			test.run();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
