package tests;

import scraper.core.time.Timer;

public class TestGroupRunner {

	public static void runTestGroup(TestGroup testGroup) {
		String testGroupName = getObjectClassName(testGroup);
		System.out.printf("\nRunning test group \"%s\"\n", testGroupName);

		double executionTime = monitorTestGroup(testGroup);
		System.out.printf("Test group \"%s\" exited after %fs\n", testGroupName, executionTime);
	}

	private static String getObjectClassName(Object object) {
		return object.getClass().getName();
	}

	private static double monitorTestGroup(TestGroup testGroup) {
		double executionTime = 0.0;
		Test[] testsToRun = testGroup.getTests();

		for (Test test : testsToRun) {
			testGroup.initializeStateForNextTest();
			executionTime += runTest(test);
		}

		return executionTime;
	}

	private static double runTest(Test test) {
		String testName = getObjectClassName(test);
		System.out.printf("- Running test \"%s\"\n", testName);

		double executionTime = monitorTest(test);
		System.out.printf("> Test \"%s\" exited after %fs\n", testName, executionTime);

		return executionTime;
	}

	private static double monitorTest(Test test) {
		Timer timer = new Timer();
		tryToRunTest(test);
		return timer.elapsed().toSeconds();
	}

	private static void tryToRunTest(Test test) {
		try {
			test.run();
		}
		catch (Exception exception) {
			System.err.println("! Test failed with the following exception:");
			exception.printStackTrace();
		}
	}

}
