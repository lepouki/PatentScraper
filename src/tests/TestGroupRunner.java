package tests;

public class TestGroupRunner {

	public static void runTestGroup(TestGroup testGroup) {
		Test[] tests = testGroup.getTests();

		for (Test test : tests) {
			testGroup.initializeNextTest();
			tryToRunTest(test);
		}
	}

	private static void tryToRunTest(Test test) {
		try {
			test.run();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
