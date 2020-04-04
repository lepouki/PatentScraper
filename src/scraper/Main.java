package scraper;

import scraper.application.Application;
import tests.TestGroup;
import tests.TestGroupRunner;
import tests.groups.EventSourceTestGroup;
import tests.groups.ScraperTestGroup;

public class Main {

	private static final boolean DEBUGGING = true;

	public static void main(String[] args) {
		if (DEBUGGING) {
			runTestGroups();
		}

		new Application();
	}

	private static void runTestGroups() {
		TestGroup[] testGroups = getTestGroups();

		for (TestGroup testGroup : testGroups) {
			TestGroupRunner.runTestGroup(testGroup);
		}
	}

	private static TestGroup[] getTestGroups() {
		return new TestGroup[] {
			new EventSourceTestGroup(),
			new ScraperTestGroup()
		};
	}

}
