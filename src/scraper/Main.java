package scraper;

import tests.TestGroup;
import tests.TestGroupRunner;

public class Main {

	public static void main(String[] args) {
		runTestGroups();
	}

	private static void runTestGroups() {
		TestGroup[] testGroups = getTestGroups();

		for (TestGroup testGroup : testGroups) {
			TestGroupRunner.runTestGroup(testGroup);
		}
	}

	private static TestGroup[] getTestGroups() {
		return new TestGroup[] {};
	}

}
