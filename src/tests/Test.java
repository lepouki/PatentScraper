package tests;

public class Test {

	public static class AssertionException extends Exception {

		public AssertionException(String description) {
			super(description);
		}

	}

	/**
	 * Gets overridden by the actual tests.
	 *
	 * @throws Exception When an exception gets thrown by the test.
	 */
	public void run() throws Exception {
	}

	protected void assertCondition(boolean condition, String description) throws AssertionException {
		if (!condition) {
			throw new AssertionException(description);
		}
	}

}
